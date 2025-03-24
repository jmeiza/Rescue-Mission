package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class BasicReport implements Report {
    private int cost;

    public BasicReport(int cost){
        this.cost = cost;
    }

    public List<String> getInfo(){
        List<String> output = new ArrayList<>();

        output.add(String.valueOf(this.cost));
        output.add("NULL");

        return output;
    }
}
