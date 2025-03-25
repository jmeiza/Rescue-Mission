package main.java.ca.mcmaster.se2aa4.island.teamXXX;

/*THIS CLASS STORES INFORMATION ABOUT THE CREEK */
public class Creek {
    private String creekId;
    private Coordinate location;

    public Creek(String creekId, int[] location){
        this.creekId = creekId;
        this.location = new Coordinate(location[0], location[1]);
    }

    public String getId(){
        return this.creekId;
    }

    public int[] getLocation(){
        return this.location.getCoordinate();
    }
}
