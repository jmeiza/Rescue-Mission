package ca.mcmaster.se2aa4.island.team40;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

/*THIS CLASS IS USED TO PARSE THE RESPONSE OF AN ECHO OPERATION */
public class EchoParser implements Parser{
    private int cost;
    private String found;
    private int range;
    private Report output;

    @Override
    public Report parse(JSONObject response){
        JSONObject extras = response.getJSONObject("extras");
        this.cost = response.getInt("cost");
        this.range = extras.getInt("range");
        this.found = extras.getString("found");

        output = new EchoReport(this.cost, this.range, this.found);
        
        return output;
    }
}
