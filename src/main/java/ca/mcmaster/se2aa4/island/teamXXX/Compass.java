package ca.mcmaster.se2aa4.island.teamXXX;

public class Compass 
{
    enum Directions { NORTH, EAST, SOUTH, WEST};
  
    Directions direction;
    int vertical_direction, horizontal_direction;

    public Compass(String s)
    {
        if(s.equalsIgnoreCase("N"))
        {
            direction = Directions.NORTH;
            vertical_direction = -1;
            horizontal_direction = 0;
        }
        else if(s.equalsIgnoreCase("E"))
        {
            direction = Directions.EAST;
            vertical_direction = 0;
            horizontal_direction = 1;
        }
        else if(s.equalsIgnoreCase("S"))
        {
            direction = Directions.SOUTH;
            vertical_direction = 1;
            horizontal_direction = 0;
        }
        else if(s.equalsIgnoreCase("W"))
        {
            direction = Directions.WEST;
            vertical_direction = 0;
            horizontal_direction = -1;
        }
    }
  
    private int position[] = new int[2]; // 0 is x value, 1 is y value

    public void straight()
    {
        this.position[0] += horizontal_direction;
        this.position[1] += vertical_direction;
    }

    public void left() // turns the compass left
    {
        if(direction == Directions.NORTH) // NORTH
        {
            vertical_direction = 0;
            horizontal_direction = -1;
            direction = Directions.WEST;
        }
        else if(direction == Directions.EAST) // EAST
        {
            vertical_direction = -1;
            horizontal_direction = 0;
            direction = Directions.NORTH;   
        }
        else if(direction == Directions.SOUTH) // SOUTH
        {
            vertical_direction = 0;
            horizontal_direction = 1;
            direction = Directions.EAST;
        }
        else // WEST
        {   
            vertical_direction = 1;
            horizontal_direction = 0;
            direction = Directions.SOUTH;
        }
    }

    public void right() // turns the compass right
    {
        if(direction == Directions.NORTH) // NORTH
        {
            vertical_direction = 0;
            horizontal_direction = 1;
            direction = Directions.EAST;
        }
        else if(direction == Directions.EAST) // EAST
        {
            vertical_direction = 1;
            horizontal_direction = 0;
            direction = Directions.SOUTH;
        }
        else if(direction == Directions.SOUTH) // SOUTH
        {
            vertical_direction = 0;
            horizontal_direction = -1;
            direction = Directions.WEST;
        }
        else // WEST
        {
            vertical_direction = -1;
            horizontal_direction = 0;
            direction = Directions.NORTH;
        }
    }

    public int[] returnPos()
    {
        return this.position;
    }

    public Directions returnDirec()
    {
        return this.direction;
    }

}
