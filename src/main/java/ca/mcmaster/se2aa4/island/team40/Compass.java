package ca.mcmaster.se2aa4.island.team40;

/*THIS CLASS HANDLES EVEYRTHIGN RELATED TO DIRECTION */
public class Compass {
    
    /*This method returns the same cardinality of whatever cardinality it is fed */
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

    /*This method returns the left cardinality of whatever cardinality it is fed */
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

    /*This method returns the rights cardinality of whatever cardinality it is fed */
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

    /*This method just swaps relative direction from left to right and vice versa */
    public Direction swap(Direction dir){
        if (dir == Direction.LEFT){
            return Direction.RIGHT;
        }
        else{
            return Direction.LEFT;
        }
    }
}
