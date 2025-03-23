package main.java.ca.mcmaster.se2aa4.island.teamXXX;

public class Compass {
    
    public String frontDirection(Direction dir){
        if (dir == Direction.EAST){
            return "E";
        }
        else if (dir == Direction.WEST){
            return "W";
        }
        else if (dir == Direction.NORTH){
            return "N";
        }
        else {
            return "S";
        }
    }
    public String leftDirection(Direction dir){
        if (dir == Direction.EAST){
            return "N";
        }
        else if (dir == Direction.WEST){
            return "S";
        }
        else if (dir == Direction.NORTH){
            return "W";
        }
        else {
            return "E";
        }
    }

    public String rightDirection(Direction dir){
        if (dir == Direction.EAST){
            return "S";
        }
        else if (dir ==  Direction.WEST){
            return "N";
        }
        else if (dir ==  Direction.NORTH){
            return "E";
        }
        else {
            return "W";
        }
    }

}
