package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Drone {

    private int battery;
    private Direction curDir;

    public Drone(int battery, String direction){
        this.battery = battery;
        if (direction.equalsIgnoreCase("N")) {
            this.curDir = Direction.NORTH;
        }
        else if (direction.equalsIgnoreCase("E")){
            this.curDir = Direction.EAST;
        }
        else if (direction.equalsIgnoreCase("W")){
            this.curDir = Direction.WEST;
        }
        else {this.curDir = Direction.SOUTH;}
    }

    public void setDirection(Direction dir){
        this.curDir = dir;
    }

    public Direction getDirection(){
        return this.curDir;
    }


}
