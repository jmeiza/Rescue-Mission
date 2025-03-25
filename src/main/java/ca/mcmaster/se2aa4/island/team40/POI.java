package ca.mcmaster.se2aa4.island.team40;

import java.util.ArrayList;
import java.util.List;

/*THIS CLASS STORES INFORMATION ABOUT THE POINTS OF INTERESTS */
public class POI {
    private List<Creek> creeks = new ArrayList<>();
    private EmergencySite site;
    private int noOfCreeks = 0;


    public void markCreek(String id, int[] location){
        this.creeks.add(new Creek(id, location));
        noOfCreeks += 1;
    }

    public void markSite(String id, int[] location){
        this.site = new EmergencySite(id, location);
    }

    public String getCreekId(){
        return this.creeks.get(noOfCreeks-1).getId();
    }

    public String getSiteId(){
        return this.site.getId();
    }

    public int[] getCreekLocation(){
        return this.creeks.get(0).getLocation();
    }

    public int[] getSiteLocation(){
        return this.site.getLocation();
    }
}
