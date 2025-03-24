package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import java.util.List;

import javax.swing.Action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class POIFinder {
    private final Logger logger = LogManager.getLogger();

    private Drone drone;

    private POI spots;

    private Compass compass = new Compass();

    private State phase = State.PHASE2_START;

    private State state = State.PHASE2_IN_BOUND;

    private Direction nextTurn;

    private Direction[] next = {Direction.FRONT, Direction.LEFT, Direction.RIGHT};

    private int echoCounter = 1;

    private Direction lastDirection;

    public POIFinder(Drone drone, POI spots){
        this.spots = spots;
        this.drone = drone;
        this.lastDirection = drone.getDirection();
    }

    public JSONObject find(Report report, Operation lastOp){
        List<String> data = report.getInfo();
        Action action = new Action();
        JSONObject response;

        if (phase == State.PHASE2_START){
            /*If we have found what direction the island is in, we start flying and switch over to the
             * second phase of the exploration
             */
            if (lastOp == Operation.ECHO && data.get(2).equals("GROUND")){
                
                nextTurn = compass.nextTurn(this.drone.getDirection(), lastDirection);
                phase = State.PHASE2_POI_SEARCH;
                response = action.fly();
                this.drone.flyUpdateLocation();
                return response;
             
            }
            /*If the direction in which the island is in has not been found, we keep echoing
             * in the other directions until we find the island
             */
            else {
                response = action.echo(this.drone.getDirection(), next[echoCounter%3]);
                echoCounter++;
                lastDirection = converter(response.getJSONObject("parameters").getString("direction"));
                return response;
            }
        }
        else{
            /*The drone is currently within the island zone */
            if (this.state == State.PHASE2_IN_BOUND){

                if (lastOp == Operation.SCAN){

                    if (!data.get(2).equals("NULL")){
                        logger.info("** Creek found");

                        this.spots.markCreek(data.get(2), this.drone.getLocation());

                        this.drone.flyUpdateLocation();         /*Updating the drone's coordinates */
                        return action.fly();
                    }
                    if (!data.get(3).equals("NULL")){
                        this.spots.markSite(data.get(3), this.drone.getLocation());
                        logger.info("** Emergency site found");
                        return action.stop();
                    }
                    if (data.get(1).equals("OCEAN")){
                        this.state = State.PHASE2_ON_EDGE;
                        response = action.echo(this.drone.getDirection(), next[0]);
                        return response;
                    }
                    else{
                        response = action.fly();
                        this.drone.flyUpdateLocation();     /*Updating the drone's coordinates */
                        return response;
                    }
                }
                else{
                    response = action.scan();
                    return response;
                }
                //Might need to add an echo elif statement to just sense for out of range
            }
            else if(this.state == State.PHASE2_ON_EDGE){

                if (lastOp == Operation.ECHO && data.get(2).equals("OUT_OF_RANGE")){
                    this.state = State.PHASE2_OUT_OF_BOUND;
                    response = action.heading(this.drone.getDirection(), nextTurn);

                    this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));  /*Updating the drone's coordinates */

                    this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));

                    return response;
                }
                else{
                    response = action.fly();
                    this.drone.flyUpdateLocation();
                    this.state = State.PHASE2_IN_BOUND;
                    return response;
                }
            }

            else{
                response = action.heading(this.drone.getDirection(), nextTurn);

                this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));

                this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));

                if (nextTurn == Direction.LEFT){
                    nextTurn = Direction.RIGHT;
                }
                else{
                    nextTurn = Direction.LEFT;
                }

                this.state = State.PHASE2_ON_EDGE;
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

