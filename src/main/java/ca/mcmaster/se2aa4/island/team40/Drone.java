package ca.mcmaster.se2aa4.island.team40;

import org.json.JSONObject;

/*THIS CLASS HOLDS ALL NECESSARY INFORMATION ABOUT THE DRONE */
public class Drone {

    private State curPhase;
    private int battery;
    private Direction curDir;
    private Coordinate location;
    private DroneManager manager = new DroneManager();      /*This objects manages some operations for the drone */

    public Drone(int battery, String direction, State state){
        this.battery = battery;
        this.curPhase = state;
        this.curDir = manager.startDirection(direction);
        this.location = new Coordinate(0,0);
    }

    public Direction getDirection(){
        return this.curDir;
    }

    public void setDirection(Direction newDir){
        this.curDir = newDir;
    }

    public State getPhase(){
        return this.curPhase;
    }

    public void updatePhase(State state){
        this.curPhase = state;
    }

    public int[] getLocation(){
        return location.getCoordinate();
    }

    public void updateBattery(int cost){
        this.battery -= cost;
    }

    public int getBattery(){
        return this.battery;
    }

    public void flyUpdateLocation(){
        manager.flyUpdateLocation(this.curDir, this.location);
    }

    public void turnUpdateLocation(Direction nextDir){
        manager.turnUpdateLocation(this.curDir, nextDir, this.location);
        this.curDir = nextDir;
    }


}
