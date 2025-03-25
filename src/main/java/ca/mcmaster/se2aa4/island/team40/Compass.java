package ca.mcmaster.se2aa4.island.team40;

/*THIS CLASS HANDLES EVEYRTHING RELATED TO DIRECTION */
public class Compass {
    
    /*This method returns the same cardinality of whatever cardinality it is fed */
    public String frontDirection(Direction dir){
        if (null == dir){
            return "S";
        }
        else return switch (dir) {
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            default -> "S";
        };
    }

    /*This method returns the left cardinality of whatever cardinality it is fed */
    public String leftDirection(Direction dir){
        if (null == dir){
            return "E";
        }
        else return switch (dir) {
            case EAST -> "N";
            case WEST -> "S";
            case NORTH -> "W";
            default -> "E";
        };
    }

    /*This method returns the rights cardinality of whatever cardinality it is fed */
    public String rightDirection(Direction dir){
        if (null == dir){
            return "W";
        }
        else return switch (dir) {
            case EAST -> "S";
            case WEST -> "N";
            case NORTH -> "E";
            default -> "W";
        };
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
