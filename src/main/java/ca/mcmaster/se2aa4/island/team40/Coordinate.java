package ca.mcmaster.se2aa4.island.team40;

/*THIS CLASS STORES LOCATIONS BASED ON COORDINATE POINTS */
public class Coordinate {
    private final int[] location = new int[2];
    private int xPoint;
    private int yPoint;

    public Coordinate(int x, int y){
        this.xPoint = x;
        this.yPoint = y;
        location[0] = this.xPoint;
        location[1] = this.yPoint;
    }

    public int[] getCoordinate(){
        return location;
    }

    public void updateCoordinate(int xOffset, int yOffset){
        this.xPoint += xOffset;
        this.yPoint += yOffset;
        location[0] = this.xPoint;
        location[1] = this.yPoint;

    }
}
