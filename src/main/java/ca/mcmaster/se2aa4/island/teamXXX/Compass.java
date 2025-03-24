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


    public Direction nextTurn(Direction curDir, Direction nextDir){
        if (converter(leftDirection(curDir)) == nextDir){
            return Direction.LEFT;
        }
        else if (converter(rightDirection(curDir)) == nextDir){
            return Direction.RIGHT;
        }
        else{
            return Direction.FRONT;
        }
    }


    private Direction converter(String str){
        if (str.equals("N")){
            return Direction.NORTH;
        }
        else if (str.equals("E")){
            return Direction.EAST;
        }
        else if (str.equals("W")){
            return Direction.WEST;
        }
        else{
            return Direction.SOUTH;
        }
    }
}
