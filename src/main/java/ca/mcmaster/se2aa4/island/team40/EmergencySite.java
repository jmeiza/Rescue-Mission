package ca.mcmaster.se2aa4.island.team40;

/*THIS CLASS HOLDS INFORMATION ABOUT THE EMRGENCY SITE WAS FOUND */
public class EmergencySite {
    private String siteId;
    private Coordinate location;

    public EmergencySite(String id, int[] location){
        this.siteId = id;
        this.location = new Coordinate(location[0], location[1]);
    }

    public String getId(){
        return this.siteId;
    }

    public int[] getLocation(){
        return this.location.getCoordinate();
    }
}
