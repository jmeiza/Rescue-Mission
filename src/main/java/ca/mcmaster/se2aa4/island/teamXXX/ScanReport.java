package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class ScanReport implements Report{
    private int cost;
    private String creekId;
    private String siteId;
    private String biome;

    public ScanReport(int cost, String ocean, String creekId, String siteId) {
        this.cost = cost;
        this.creekId = creekId;
        this.siteId = siteId;
        this.biome = ocean;
    }

    @Override
    public List<String> getInfo(){
        List<String> output = new ArrayList<>();
        /*Output = [cost, biome, creek, site] */

        output.add(String.valueOf(cost));
        output.add(biome);
        output.add(creekId);
        output.add(siteId);

        return output;

    }


}
