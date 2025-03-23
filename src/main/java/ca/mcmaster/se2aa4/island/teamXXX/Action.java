package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;


public class Action {
    private JSONObject decision;

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


    public JSONObject heading(Direction cur, Direction next) {
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
