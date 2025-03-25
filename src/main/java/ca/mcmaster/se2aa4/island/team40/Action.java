package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

/*THIS CLASS IMPLEMENTS THE DIFFERENT ACTIONS THAT CAN BE TAKEN BY THE DRONE */
public class Action {
    private JSONObject decision;

    /*This method takes in the current direction of the drone, and then the relative direction to echo to */
    public JSONObject echo(Direction cur, Direction next){

        JSONObject parameters = new JSONObject();
        Compass side = new Compass();

        if (next == Direction.LEFT){
            parameters.put("direction",side.leftDirection(cur));
        }
        else if (next == Direction.RIGHT){
            parameters.put("direction",side.rightDirection(cur));
        }
        else{
            parameters.put("direction",side.frontDirection(cur));
        }

        decision = new JSONObject();
        decision.put("action","echo");
        decision.put("parameters",parameters);

        return decision;
    }

    /*This method takes in the drone's current direction and the relative direction the drone shoudl turn to */
    public JSONObject heading(Direction cur, Direction next) {

        JSONObject parameters = new JSONObject();
        Compass side = new Compass();

        if (next == Direction.LEFT){
            parameters.put("direction",side.leftDirection(cur));
        }
        else {
            parameters.put("direction",side.rightDirection(cur));
        }
        
        decision = new JSONObject();
        decision.put("action","heading");
        decision.put("parameters",parameters);

        return decision;
    }


    public JSONObject fly(){
        decision = new JSONObject();
        decision.put("action","fly");
        return decision;
    }


    public JSONObject scan(){
        decision = new JSONObject();
        decision.put("action","scan");
        return decision;
    }


    public JSONObject stop(){
        decision = new JSONObject();
        decision.put("action","stop");
        return decision;
    }
}
