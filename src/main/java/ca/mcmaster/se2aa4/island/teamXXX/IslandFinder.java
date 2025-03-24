package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/*THIS CLASS IMPLEMENTS THE ALGORITHM THAT FINDS THE ISLAND */
public class IslandFinder {

    private final Logger logger = LogManager.getLogger();

    private Action action;

    private Drone drone;

    private POI spots;

    private State state = State.PHASE1_ISLAND_SEARCH;

    private Direction[] next = {Direction.FRONT, Direction.LEFT, Direction.RIGHT};

    private int echoCounter = 0;

    private int distanceToIsland;


    public IslandFinder(Drone drone, POI spots){
        this.drone = drone;
        this.spots = spots;
    }
    
    public JSONObject find(Report report, Operation lastOp) {
        List<String> data = report.getInfo();
        action = new Action();
        JSONObject response;

        /*Applying cost of previous action on the drone's battery */
        this.drone.updateBattery(Integer.parseInt(data.get(0)));
        
        if (this.state == State.PHASE1_ISLAND_SEARCH){

            if (lastOp == Operation.HEADING || lastOp == Operation.FLY){        /*After every done movement, echo in one direction */
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
                        this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));  /*Updating the drone's coordinates */
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
                logger.info("** The island has been found!!");
                this.drone.updatePhase(State.PHASE2);       /*Update the phase of the drone */

                /*Records the creek if one was found */
                if (!data.get(2).equals("NULL")) {
                    this.spots.markCreek(data.get(2), this.drone.getLocation());
                }
                response = action.echo(this.drone.getDirection(),Direction.RIGHT);
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
        if (str.equals("N")){
            return Direction.NORTH;
        }
        else if (str.equals("E")){
            return Direction.EAST;
        }
        else if (str.equals("W")){
            return Direction.WEST;
        }
        else{
            return Direction.SOUTH;
        }
    }
}
