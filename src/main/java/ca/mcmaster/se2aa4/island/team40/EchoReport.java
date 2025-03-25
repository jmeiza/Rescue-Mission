package ca.mcmaster.se2aa4.island.team40;

import java.util.ArrayList;
import java.util.List;

/*THIS CLASS IS USED TO HOLD THE RESPONSE OF AN ECHO OPERATION */
public class EchoReport implements Report {
    private final int cost;
    private final int range;
    private final String found;
    List<String> output;        /*[COST, RANGE, FOUND] */

    public EchoReport(int cost, int range, String found){
        this.cost = cost;
        this.range = range;
        this.found = found;
    }

    @Override
    public List<String> getInfo() {
        output = new ArrayList<>();
   
        output.add(String.valueOf(this.cost));
        output.add(String.valueOf(this.range));
        output.add(this.found);
        return output;
    }
}
