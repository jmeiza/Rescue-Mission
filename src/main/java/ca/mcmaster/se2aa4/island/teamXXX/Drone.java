package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanAttributeInfo;

import org.json.JSONObject;

public class Drone {

    private State curPhase;
    private int battery;
    private Direction curDir;
    private Coordinate location;
    private DroneManager manager = new DroneManager();

    public Drone(int battery, String direction, State state){
        this.battery = battery;
        this.curPhase = state;
        this.curDir = manager.startDirection(direction);
        this.location = new Coordinate(0,0);
    }

    public void setDirection(Direction dir){
        this.curDir = dir;
    }

    public Direction getDirection(){
        return this.curDir;
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
    }


}
