package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

/*THIS CLASS IS USED TO PARSE THE RESPONSES OF ACTIONS LIKE FLY, HEADING. 
 * BOTH FLY AND HEADING HAVE A RESPONSE THAT IS STRUCTURED IN THE SAME WAY
 */
public class BasicParser implements Parser{
    private Report output;

    @Override
    public Report parse(JSONObject response){
        output = new BasicReport(response.getInt("cost"));
        return output;
    }
}
