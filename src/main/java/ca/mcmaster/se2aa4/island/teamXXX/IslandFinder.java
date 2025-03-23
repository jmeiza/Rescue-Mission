package main.java.ca.mcmaster.se2aa4.island.teamXXX;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class IslandFinder {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private Operation lastOp = Operation.FLY;
    private State state = State.ISLAND_SEARCH;
    private Direction[] next = {Direction.FRONT, Direction.LEFT, Direction.RIGHT};
    private int echoCount = 0;
    private int distanceToIsland;


    public IslandFinder(Drone drone){
        this.drone = drone;
    }
    
    public JSONObject find(Report report) {
        List<String> data = report.getInfo();
        Action action = new Action();
        JSONObject response;
        
        if (this.state == State.ISLAND_SEARCH){

            if (this.lastOp == Operation.HEADING || this.lastOp == Operation.FLY){
                this.lastOp = Operation.ECHO;
                response = action.echo(this.drone.getDirection(), next[echoCount%3]);
                echoCount ++;
                return response;
            }

            else {
                /*Turn towards the Ground that*/
                if (data.get(2).equals("GROUND")){
                    this.distanceToIsland = Integer.parseInt(data.get(1));
                    this.lastOp = Operation.HEADING;
                    response = action.heading(this.drone.getDirection(),next[(echoCount-1)%3]);
                
                    this.drone.setDirection(converter(response.getJSONObject("parameters").getString("direction")));
                    this.state = State.ISLAND_FOUND;
                    return response;
                }
                this.lastOp = Operation.FLY;
                response = action.fly();
                return response;
            }
        }
        else{
            if (this.lastOp == Operation.SCAN && data.get(1).equals("FOUND")){
                logger.info("** The island has been found!!");
                return action.stop();
            }
            if (distanceToIsland > 0){
                this.lastOp = Operation.FLY;
                response = action.fly();
                distanceToIsland --;
                return response;
            }
            else{
                this.lastOp = Operation.SCAN;
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
