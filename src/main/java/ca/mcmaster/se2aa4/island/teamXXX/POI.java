package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

/*THIS CLASS STORES INFORMATION ABOUT THE POINTS OF INTERESTS */
public class POI {
    private List<Creek> creeks = new ArrayList<>();
    private EmergencySite site;


    public void markCreek(String id, int[] location){
        this.creeks.add(new Creek(id, location));
    }

    public void markSite(String id, int[] location){
        this.site = new EmergencySite(id, location);
    }

    public String getCreekId(){
        return this.creeks.get(0).getId();
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
