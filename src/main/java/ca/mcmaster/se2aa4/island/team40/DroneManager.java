package ca.mcmaster.se2aa4.island.team40;

/*THIS CLASS IS USED TO UPDATE FIELDS OF A DRONE */
public class DroneManager {
    
    /*This method is used to assign the starting direction of the drone */
    public Direction startDirection(String direction){
        if (direction.equalsIgnoreCase("N")) {
            return Direction.NORTH;
        }
        else if (direction.equalsIgnoreCase("E")){
            return Direction.EAST;
        }
        else if (direction.equalsIgnoreCase("W")){
            return Direction.WEST;
        }
        else {
            return Direction.SOUTH;
        }

    }
    /*This method is used to update the coordinates of the drone after it flies */
    public void flyUpdateLocation(Direction dir, Coordinate location){
        if (dir == Direction.NORTH){
            location.updateCoordinate(0, -1);
        }
        else if(dir == Direction.EAST){
            location.updateCoordinate(1, 0);
        }
        else if(dir == Direction.WEST){
            location.updateCoordinate(-1, 0);
        }
        else{
            location.updateCoordinate(0, 1);
        }
    }
    /*This method is used to update the coordinates of the drone after it makes a turn */
    public void turnUpdateLocation(Direction curDir, Direction nextDir, Coordinate location){
        if (curDir == Direction.NORTH){
            if (nextDir == Direction.EAST){
                location.updateCoordinate(1, -1);
            }
            else{
                location.updateCoordinate(-1,-1);
            }
        }
        else if(curDir == Direction.EAST){
            if (nextDir == Direction.NORTH){
                location.updateCoordinate(1, -1);
            }
            else{
                location.updateCoordinate(1, 1);
            }
        }
        else if(curDir == Direction.SOUTH){
            if (nextDir == Direction.EAST){
                location.updateCoordinate(1, 1);
            }
            else{
                location.updateCoordinate(-1, 1);
            }
        }
        else{
            if (nextDir == Direction.NORTH){
                location.updateCoordinate(-1, -1);
            }
            else {
                location.updateCoordinate(1, 1);
            }
        }
    }


}
