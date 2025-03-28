package ca.mcmaster.se2aa4.island.team40;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/* This class implements the algorithm used to find the creek and the emrgency site on the island */
public class POIFinder{
    private final Logger logger = LogManager.getLogger();
    
    private Action action;

    private JSONObject response;

    private final Drone drone;

    private final POI spots;          /*Represents the object used to store the location of the sites of interest */

    private final Compass compass = new Compass();

    private State phase = State.PHASE2_START;           

    private State state = State.PHASE2_IN_BOUND;

    private State poiFound = State.NONE_FOUND;

    private Direction nextTurn = Direction.RIGHT;         /*This field holds what direction the drone should turn next with respect to it's current direction */

    private int echoInterval = 0;

    public POIFinder(Drone drone, POI spots){
        this.drone = drone;
        this.spots = spots;
    }

    public JSONObject find(Report report, Operation lastOp){
        List<String> data = report.getInfo();
        action = new Action();

        /*Applying the cost of the last action to the battery of the drone */
        this.drone.updateBattery(Integer.parseInt(data.get(0)));
        
        /*Checks if both the creek and the emergency site have been found */
        if (poiFound == State.BOTH_FOUND){
            logger.info("** Drone battery: {}",this.drone.getBattery());
            return action.stop();
        }

        /*If the drone's battery is less than 25, it's better to just return to base
         * than try any other actions */
        if (this.drone.getBattery() < 20){
            logger.info("** Drone battery: {}",this.drone.getBattery());
            logger.info("** Drone battery died before exploration finished");
            return action.stop();
        }

        /*PART 1 OF PHASE2: Figuring out which direction the first turn should be*/

        if (phase == State.PHASE2_START){
            /* After locating the direction for the first turn, proceed to phase 2*/
            if (lastOp == Operation.ECHO && data.get(2).equals("GROUND")){

                phase = State.PHASE2_POI_SEARCH;        /*Switch Phases*/
                response = action.fly();
                this.drone.flyUpdateLocation();
                return response;
             
            }
            /*Echoing in to the left and to the right to find out what direction the first turn should be */
            else {
                nextTurn = compass.swap(nextTurn);
                response = action.echo(this.drone.getDirection(), nextTurn);
                return response;
            }
        }

        else{
            /*Echo in facing direction after every ten movements */
            if (echoInterval == 10){
                response = action.echo(this.drone.getDirection(), Direction.FRONT);
                echoInterval = 0;
                return response;
            }

            if (lastOp == Operation.ECHO && Integer.parseInt(data.get(1)) <= 5 && data.get(2).equals("OUT_OF_RANGE")){
                this.state = State.PHASE2_ON_EDGE;
            }
        
            if (null == this.state){       /*The drone is now outside the range of the island */
                
                response = action.heading(this.drone.getDirection(), nextTurn);     /*Turn the drone one more time */
                echoInterval += 1;
                
                this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));
                //this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));
                
                /*ALternating between turning left and turning right */
                nextTurn = compass.swap(nextTurn);
                
                this.state = State.PHASE2_ON_EDGE;
                return response;
            }

            else switch (this.state) {
                case PHASE2_IN_BOUND -> {
                    if (lastOp == Operation.SCAN){
                        
                        if (!data.get(2).equals("NULL")){           /*A creek has been found */
                            logger.info("** Creek found");
                            
                            this.spots.markCreek(data.get(2), this.drone.getLocation());
                            this.drone.flyUpdateLocation();         /*Updating the drone's coordinates */
                            
                            /*Updating the number of POI found */
                            if (poiFound == State.NONE_FOUND || poiFound == State.CREEK_FOUND){
                                poiFound = State.CREEK_FOUND;
                            }
                            else{
                                poiFound = State.BOTH_FOUND;
                            }
                            echoInterval += 1;
                            return action.fly();
                        }
                        
                        if (!data.get(3).equals("NULL")){           /*An emergency site has been found */
                            this.spots.markSite(data.get(3), this.drone.getLocation());
                            logger.info("** Emergency site found");
                            
                            /*Updating the number of POI found */
                            if (poiFound == State.NONE_FOUND){
                                poiFound = State.SITE_FOUND;
                            }
                            else{
                                poiFound = State.BOTH_FOUND;
                            }
                            echoInterval += 1;
                            return action.fly();
                        }
                        /*The drone is on the edge of land and ocean */
                        if (data.get(1).equals("OCEAN")){
                            this.state = State.PHASE2_ON_EDGE;      /*Change state */
                            response = action.echo(this.drone.getDirection(), Direction.FRONT);
                            return response;
                        }
                        
                        else{
                            response = action.fly();
                            echoInterval += 1;
                            this.drone.flyUpdateLocation();     /*Updating the drone's coordinates */
                            return response;
                        }
                    }
                    else{
                        response = action.scan();
                        return response;
                    }
                }
                case PHASE2_ON_EDGE -> {
                    if (lastOp == Operation.ECHO && data.get(2).equals("OUT_OF_RANGE")){        /*The drone is flying over the ocean and needs to turn */
                        this.state = State.PHASE2_OUT_OF_BOUND;
                        
                        response = action.heading(this.drone.getDirection(), nextTurn);
                        echoInterval += 1;
                        
                        this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));  /*Updating the drone's coordinates */
                        //this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));
                        
                        return response;
                    }
                    else{
                        /*The drone was just flying over water that is on the island so it can just keep flying in that direction */
                        response = action.fly();
                        echoInterval += 1;
                        this.drone.flyUpdateLocation();
                        this.state = State.PHASE2_IN_BOUND;     /*The drone is still in-bound */
                        return response;
                    }
                }
                default -> {
                    /*The drone is now outside the range of the island */
                    
                    response = action.heading(this.drone.getDirection(), nextTurn);     /*Turn the drone one more time */
                    echoInterval += 1;
                    
                    this.drone.turnUpdateLocation(converter(response.getJSONObject("parameters").getString("direction")));
                    //this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));

                    /*ALternating between turning left and turning right */
                    nextTurn = compass.swap(nextTurn);
                    
                    this.state = State.PHASE2_ON_EDGE;
                    return response;
                }
            }
            //Might need to add an echo elif statement to just sense for out of range
            
        }
    }
    
    /*This method simply converts a cardinality from a string to a Direction object */        
    private Direction converter(String str){
        return switch (str) {
            case "N" -> Direction.NORTH;
            case "E" -> Direction.EAST;
            case "W" -> Direction.WEST;
            default -> Direction.SOUTH;
        };
    }
}

