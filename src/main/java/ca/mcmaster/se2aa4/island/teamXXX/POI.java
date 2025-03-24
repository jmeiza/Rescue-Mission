package main.java.ca.mcmaster.se2aa4.island.teamXXX;


public class POI {
    private Creek creek;
    private EmergencySite site;


    public void markCreek(String id, int[] location){
        this.creek = new Creek(id, location);
    }

    public void markSite(String id, int[] location){
        this.site = new EmergencySite(id, location);
    }

    public String getCreekId(){
        return this.creek.getId();
    }

    public int[] getCreekLocation(){
        return this.creek.getLocation();
    }

    public int[] getSiteLocation(){
        return this.site.getLocation();
    }
}
