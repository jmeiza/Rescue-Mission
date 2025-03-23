package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import java.util.List;

import java.io.StringReader;

public class Reader {
    private JSONObject response;
    private Operation action;

    public Reader(JSONObject response, Operation action) {
        this.response = response;
        this.action = action;
    }
    
    
}
