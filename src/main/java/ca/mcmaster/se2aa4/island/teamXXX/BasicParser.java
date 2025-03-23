package main.java.ca.mcmaster.se2aa4.island.teamXXX;


import org.json.JSONObject;

public class BasicParser implements Parser{
 
    private JSONObject response;
    private int cost;

    public BasicParser(JSONObject response){
        this.response = response;
    }

    public Report parse(){
        Report output = new BasicReport(response.getInt("cost"));
        return output;
    }
}
