package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public class EchoReport implements Report {
    private int cost;
    private int range;
    private String found;

    public EchoReport(int cost, int range, String found){
        this.cost = cost;
        this.range = range;
        this.found = found;
    }

    @Override
    public List<String> getInfo() {
        List<String> output = new ArrayList<>();
        /* Output = [cost, range, found] */
        output.add(String.valueOf(this.cost));
        output.add(String.valueOf(this.range));
        output.add(this.found);
        return output;
    }
}
