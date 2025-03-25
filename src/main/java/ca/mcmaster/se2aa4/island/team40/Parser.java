package ca.mcmaster.se2aa4.island.team40;

import org.json.JSONObject;

public interface Parser {
    
    public Report parse(JSONObject response);
}
