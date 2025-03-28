package ca.mcmaster.se2aa4.island.team40;

import java.util.List;

import org.json.JSONObject;

/*THIS CLASS IMPLEMENTS THE ALGORITHM THAT FINDS THE ISLAND */
public class IslandFinder{

    private Action action;

    private final Drone drone;

    private final POI spots;

    private State state = State.PHASE1_ISLAND_SEARCH;

    private final Direction[] next = {Direction.FRONT, Direction.LEFT, Direction.RIGHT};

    private int echoCounter = 0;

    private int distanceToIsland;

    private JSONObject response;


    public IslandFinder(Drone drone, POI spots){
        this.drone = drone;
        this.spots = spots;
    }

    public JSONObject fixStartingPosition(Report report, Operation lastOp){
        List<String> data = report.getInfo();
        action = new Action();

        if (lastOp == Operation.NONE){
            response = action.echo(this.drone.getDirection(), next[echoCounter%3]);
            echoCounter++;
        }
        else{
            if (Integer.parseInt(data.get(1)) < 20 && data.get(2).equals("OUT_OF_RANGE")){
                response = action.echo(this.drone.getDirection(),next[echoCounter%3]);
                echoCounter++;
            }
            else{
                response = switch ((echoCounter-1)%3) {
                    case 0 -> action.fly();
                    case 1 -> action.heading(this.drone.getDirection(),next[1]);
                    default -> action.heading(this.drone.getDirection(), next[2]);
                };
                this.drone.updatePhase(State.PHASE1);
                echoCounter = 0;
            }
        }
        return response;
    }
    
    public JSONObject find(Report report, Operation lastOp) {
        List<String> data = report.getInfo();
        action = new Action();

        /*Applying cost of previous action on the drone's battery */
        this.drone.updateBattery(Integer.parseInt(data.get(0)));
        
        if (this.state == State.PHASE1_ISLAND_SEARCH){

            if (lastOp == Operation.HEADING || lastOp == Operation.FLY || lastOp == Operation.NONE){     /*After every movement, echo in one direction */
                response = action.echo(this.drone.getDirection(), next[echoCounter%3]);
                echoCounter ++;
                return response;
            }
            else { 
                if (data.get(2).equals("GROUND")){          /*This means the island has been found */

                    this.distanceToIsland = Integer.parseInt(data.get(1));      /*Distance from where the drone is to where the island is */

                    if (next[(echoCounter-1)%3] == Direction.FRONT){    /*If we are already facing the island, just keep flying */
                        response = action.fly();
                        this.drone.flyUpdateLocation();
                    }
                    else{
                        response = action.heading(this.drone.getDirection(),next[(echoCounter-1)%3]);       /*Turning to where the island is */
                        this.drone.turnUpdateLocation(converter(response.getJSONObject(Constants.PARAMETERS).getString(Constants.DIRECTION)));  /*Updating the drone's coordinates */
                        //this.drone.setDirection(converter(response.getJSONObject(Constants.PARAMETERS).getString(Constants.DIRECTION)));
                    }
                    this.state = State.PHASE1_ISLAND_SIGHTED;       /*Changing state */
                    return response;
                }
                
                /*Keeping moving forward if the direction of the island has not been found */
                response = action.fly();
                this.drone.flyUpdateLocation();
                return response;
            }
        }
        
        else{
            if (lastOp == Operation.SCAN && data.get(1).equals("FOUND")){       /*Checks if the drone is finally above the island. If it is, move to Phase 2 */
                this.drone.updatePhase(State.PHASE2);       /*Update the phase of the drone */

                /*Records the creek if one was found */
                if (!data.get(2).equals("NULL")) {
                    this.spots.markCreek(data.get(2), this.drone.getLocation());
                }

                response = action.scan();
                return response;
            }
            if (distanceToIsland > 0){          /*Call the fly action until you get to the island */
                response = action.fly();
                this.drone.flyUpdateLocation();
                distanceToIsland --;
                return response;
            }
            else{
                response = action.scan();
                return response;
            }  
        }
    }

    private Direction converter(String str){
        return switch (str) {
            case "N" -> Direction.NORTH;
            case "E" -> Direction.EAST;
            case "W" -> Direction.WEST;
            default -> Direction.SOUTH;
        };
    }
}
