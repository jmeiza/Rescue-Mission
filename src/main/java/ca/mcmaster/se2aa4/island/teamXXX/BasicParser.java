package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class BasicParser implements Parser{
 
    @Override
    public Report parse(JSONObject response){
        Report output = new BasicReport(response.getInt("cost"));
        return output;
    }
}
