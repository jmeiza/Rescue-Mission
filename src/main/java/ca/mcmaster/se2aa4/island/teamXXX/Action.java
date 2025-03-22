package ca.mcmaster.se2aa4.island.teamXXX;

// NOTEFOR ME
// DO I NEED TO EVEN GIVE CARDINAL DIRECTION AS A PAREMETER OR CAN I JUST GIVE IT STRAIGHT LEFT RIGHT
// AND FIND THE DIRECTION IN THE COMPASS CLASS ???

import ca.mcmaster.se2aa4.island.teamXXX.Compass.Directions;

public class Action 
{
    enum AllActions { ECHO, SCAN, FLY, HEADING, STOP };
    enum LR {LEFT, RIGHT};

    AllActions act;
    Directions direction;
    LR side;


    public Action(String val)
    {
        if(val.equalsIgnoreCase("fly")) this.act = AllActions.FLY;
        else if(val.equalsIgnoreCase("scan")) this.act = AllActions.SCAN;
        else if(val.equalsIgnoreCase("stop")) this.act = AllActions.STOP;
    }

    public Action(String val, String parameter, String side)
    {
        if(val.equalsIgnoreCase("echo")) 
        {
            this.act = AllActions.ECHO;
            setDirection(parameter);
            setSide(side);

        }
        else if(val.equalsIgnoreCase("heading")) 
        {
            this.act = AllActions.HEADING;
            setDirection(parameter);
            setSide(side);
        }
    }

    public void setDirection(String parameter)
    {
        if(parameter.equalsIgnoreCase("north")) this.direction = Directions.NORTH;
        else if(parameter.equalsIgnoreCase("east")) this.direction = Directions.EAST;
        else if(parameter.equalsIgnoreCase("south")) this.direction = Directions.SOUTH;
        else this.direction = Directions.WEST;
    }

    public void setSide(String side)
    {
        if(side.equalsIgnoreCase("left")) this.side = LR.LEFT;
        else this.side = LR.RIGHT;
    }

    public AllActions value()
    {
        return this.act;
    }

    public Directions returnDirection()
    {
        return this.direction;
    }

    public LR returnSide()
    {
        return this.side;
    }

}
