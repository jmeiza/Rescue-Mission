package ca.mcmaster.se2aa4.island.team40;

// some algorithm for finding the coast

public class Coast 
{
    Actions updateAction;
    private int count = 0;
    private boolean echoForward, islandLocated = false;
    private int distanceToIsland; // some int that is given by acknowledgeResults from parser

    public Coast(Actions obj)
    {
        updateAction = obj;
    }

    public void mainLoop()
    {
        // check forward to see if were facing the island
        if(!echoForward)
        {
            updateAction.setKey("action");
            updateAction.setValue("fly");
        }
        else if(!islandLocated)
        {
            findIsland();
        }
        else // the island is found by the echo
        {
            flyToIsland();
        }

    }

    public void findIsland()
    {
        // search for island algo by taking 3 steps forward then scanning left and right

        if(count % 5 < 4) // fly forward 3 spaces
            {
                // key action
                // value fly
            }

            else if(count % 5 == 4)
            {
                // key = "action"
                // value = "echo"
                // echos left
            }
            else
            {
                // key = "action"
                // value = "echo"
                // echos right
            }
    }


    // if count < count + dist to island
                // key action
                // value fly
    public void flyToIsland()
    {
        updateAction.setKey("action");
        updateAction.setValue("fly");
    }

}
