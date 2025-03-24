package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

/*THIS CLASS IS RESPONSIBLE FOR IMPLEMENTING THE MECHANISM FOR PARSING 
 * THE REPSONSE OF THE SCAN OPERATION
 */
public class ScanParser implements Parser{
    
    @Override
    public Report parse(JSONObject response) {
        JSONObject extras = response.getJSONObject("extras");
        String biome = "OCEAN";         /*Default biome is OCEAN since we start out in the ocean */
        
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
        Report output = new ScanReport(response.getInt("cost"), biome, creekId, siteId);

        return output;
    }
}
