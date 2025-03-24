package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class IslandFinder {

    private final Logger logger = LogManager.getLogger();

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
        Action action = new Action();
        JSONObject response;

        /*Applying cost of previous action on the drone's battery */
        this.drone.updateBattery(Integer.parseInt(data.get(0)));
        
        if (this.state == State.PHASE1_ISLAND_SEARCH){

            if (lastOp == Operation.HEADING || lastOp == Operation.FLY){
                response = action.echo(this.drone.getDirection(), next[echoCounter%3]);
                echoCounter ++;
                return response;
            }

            else {
                /*Turn towards the direction where the island was spotted*/
                if (data.get(2).equals("GROUND")){
                    this.distanceToIsland = Integer.parseInt(data.get(1));
                    response = action.heading(this.drone.getDirection(),next[(echoCounter-1)%3]);

                    this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));  /*Updating the drone's coordinates */

                    this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));

                    this.state = State.PHASE1_ISLAND_SIGHTED;
                    return response;
                }
                /*Keeping moving forward if the direction of the island has not been found */
                response = action.fly();
                this.drone.flyUpdateLocation();
                return response;
            }
        }
        else{
            if (lastOp == Operation.SCAN && data.get(1).equals("FOUND")){
                logger.info("** The island has been found!!");
                this.drone.updatePhase(State.PHASE2);

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
