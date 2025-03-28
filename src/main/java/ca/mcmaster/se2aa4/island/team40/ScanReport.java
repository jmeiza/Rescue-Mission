package ca.mcmaster.se2aa4.island.team40;

import java.util.ArrayList;
import java.util.List;

/*THIS CLASS HOLD THE RESPONSE OF A SCAN OPERATION */
public class ScanReport implements Report{
    private final int cost;
    private final String creekId;
    private final String siteId;
    private final String biome;

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
