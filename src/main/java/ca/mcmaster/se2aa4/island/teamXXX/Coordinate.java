package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.Action.AllActions;
import ca.mcmaster.se2aa4.island.teamXXX.Action.LR;

public class Coordinate
{
    private final Logger logger = LogManager.getLogger();

    enum POI { CREEK, EMERGENCY_SITE, DRONE };

    final int GRID_SIZE = 52;
    private POI[][] points = new POI[GRID_SIZE][GRID_SIZE];
    private int currentPos[] = new int[2];
    Compass direction;

    public Coordinate(String heading)
    {
        currentPos[0] = 0;   
        currentPos[1] = 0;
        direction = new Compass(heading);
    }

    public void action(Action action)
    {
        if (action.value() == AllActions.FLY)
        {
            removePOI(currentPos[0], currentPos[1], POI.DRONE);
            direction.straight();
            currentPos = direction.returnPos();
            updatePOI(currentPos[0], currentPos[1], POI.DRONE);

        }
        else if (action.value() == AllActions.HEADING)
        {
            removePOI(currentPos[0], currentPos[1], POI.DRONE);
            direction.straight();

            if(action.returnSide() == LR.LEFT)
            {
               direction.left(); 
            }
            else // turns right
            {
                direction.right();
            }

            direction.straight();
            currentPos = direction.returnPos();
            updatePOI(currentPos[0], currentPos[1], POI.DRONE);
        }
    }

    public void removePOI(int x, int y, POI type)
    {
        points[x][y] = null;
    }

    public void updatePOI(int x, int y, POI type)
    {
        if (points[x][y] == null) points[x][y] = type; // sets
        else
        {
            POI previousPoint = points[x][y];
            points[x][y] = type;
        }
    }

    // note doesnt really work...
    public void printGrid()
    {
        for(int i = 0; i < GRID_SIZE; i++)
        {
            for(int j = 0; j < GRID_SIZE; j++)
            {
                logger.info("point i:" + i + "and point j:" + j + " is " + points[i][j]);
            }
            logger.info("\n");
        }
    }

}
