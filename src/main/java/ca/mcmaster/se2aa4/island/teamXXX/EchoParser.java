package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

public class EchoParser implements Parser{
    private int cost;
    private String found;
    private int range;

    @Override
    public Report parse(JSONObject response){
        JSONObject extras = response.getJSONObject("extras");
        this.cost = response.getInt("cost");
        this.range = extras.getInt("range");
        this.found = extras.getString("found");

        Report output = new EchoReport(this.cost, this.range, this.found);
        
        return output;
    }
}
