package ca.mcmaster.se2aa4.island.team40;

import org.json.JSONObject;

/*THIS CLASS IMPLEMENTS THE DIFFERENT ACTIONS THAT CAN BE TAKEN BY THE DRONE */
public class Action {
    private JSONObject decision;

    /*This method takes in the current direction of the drone, and then the relative direction to echo to */
    public JSONObject echo(Direction cur, Direction next){

        JSONObject parameters = new JSONObject();
        Compass side = new Compass();

        if (null == next){
            parameters.put(Constants.DIRECTION,side.frontDirection(cur));
        }
        else switch (next) {
            case LEFT -> parameters.put(Constants.DIRECTION,side.leftDirection(cur));
            case RIGHT -> parameters.put(Constants.DIRECTION,side.rightDirection(cur));
            default -> parameters.put(Constants.DIRECTION,side.frontDirection(cur));
        }

        decision = new JSONObject();
        decision.put(Constants.ACTION,Constants.ECHO);
        decision.put(Constants.PARAMETERS,parameters);

        return decision;
    }

    /*This method takes in the drone's current direction and the relative direction the drone shoudl turn to */
    public JSONObject heading(Direction cur, Direction next) {

        JSONObject parameters = new JSONObject();
        Compass side = new Compass();

        if (next == Direction.LEFT){
            parameters.put(Constants.DIRECTION,side.leftDirection(cur));
        }
        else {
            parameters.put(Constants.DIRECTION,side.rightDirection(cur));
        }
        
        decision = new JSONObject();
        decision.put(Constants.ACTION,Constants.HEADING);
        decision.put(Constants.PARAMETERS,parameters);

        return decision;
    }


    public JSONObject fly(){
        decision = new JSONObject();
        decision.put(Constants.ACTION,Constants.FLY);
        return decision;
    }


    public JSONObject scan(){
        decision = new JSONObject();
        decision.put(Constants.ACTION,Constants.SCAN);
        return decision;
    }


    public JSONObject stop(){
        decision = new JSONObject();
        decision.put(Constants.ACTION,"stop");
        return decision;
    }
}
