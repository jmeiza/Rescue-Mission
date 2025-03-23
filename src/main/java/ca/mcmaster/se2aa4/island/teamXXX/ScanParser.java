package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

public class ScanParser implements Parser{
    private JSONObject response;

    public ScanParser(JSONObject response){
        this.response = response;
    }

    @Override
    public Report parse() {
        JSONObject extras = this.response.getJSONObject("extras");
        String biome = "OCEAN";
        
        /*Parsing the biomes*/
        JSONArray biomes = extras.getJSONArray("biomes");
        for (int i = 0; i<biomes.length(); i++) {
            if (!biomes.getString(i).equals("OCEAN")){
                biome = "FOUND";
                break;
            }
        }
        /*Parsing the creek */
        String creekId;
        JSONArray creek = extras.getJSONArray("creeks");
        if (creek.length() > 0){
            creekId = creek.getString(0);          /*Retrieving the creek id */
        }
        else {
            creekId = "NULL";       
        }

        /*Parsing the emergency site*/
        String siteId;
        JSONArray site = extras.getJSONArray("sites");
        if (site.length() > 0){
            siteId = site.getString(0);     /*Retrieving the site id */
        }
        else{ 
            siteId = "NULL";        
        }
        Report output = new ScanReport(this.response.getInt("cost"), biome, creekId, siteId);

        return output;
    }
}
