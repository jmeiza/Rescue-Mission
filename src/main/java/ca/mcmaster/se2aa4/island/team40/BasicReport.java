package ca.mcmaster.se2aa4.island.team40;

import java.util.ArrayList;
import java.util.List;

/*THIS CLASS STORES THE REPSONSE OF ACTIONS LIKE HEADING AND FLY
 * BOTH OF THEM HAVE A RESPONSE THAT IS STRUCTURED THE SAME WAY
 */
public class BasicReport implements Report {
    private final int cost;

    public BasicReport(int cost){
        this.cost = cost;
    }

    @Override
    public List<String> getInfo(){
        List<String> output = new ArrayList<>();

        output.add(String.valueOf(this.cost));
        output.add("NULL");

        return output;
    }
}
