package main.java.ca.mcmaster.se2aa4.island.teamXXX;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class IslandFinder {

    private final Logger logger = LogManager.getLogger();

    private Drone drone;

    private State state = State.PHASE1_ISLAND_SEARCH;

    private Direction[] next = {Direction.FRONT, Direction.LEFT, Direction.RIGHT};

    private int echoCounter = 0;

    private int distanceToIsland;


    public IslandFinder(Drone drone){
        this.drone = drone;
    }
    
    public JSONObject find(Report report, Operation lastOp) {
        List<String> data = report.getInfo();
        Action action = new Action();
        JSONObject response;
        
        if (this.state == State.PHASE1_ISLAND_SEARCH){

            if (lastOp == Operation.HEADING || lastOp == Operation.FLY){
                response = action.echo(this.drone.getDirection(), next[echoCounter%3]);
                echoCounter ++;
                return response;
            }

            else {
                /*Turn towards the Ground that*/
                if (data.get(2).equals("GROUND")){
                    this.distanceToIsland = Integer.parseInt(data.get(1));
                    response = action.heading(this.drone.getDirection(),next[(echoCounter-1)%3]);
                
                    this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));
                    this.state = State.PHASE1_ISLAND_SIGHTED;
                    return response;
                }
                response = action.fly();
                return response;
            }
        }
        else{
            if (lastOp == Operation.SCAN && data.get(1).equals("FOUND")){
                logger.info("** The island has been found!!");
                this.drone.updatePhase(State.PHASE2);
                response = action.scan();
                return response;
            }
            if (distanceToIsland > 0){
                response = action.fly();
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
