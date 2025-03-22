package ca.mcmaster.se2aa4.island.teamXXX;

// too if dependent

public class EmergencySite 
{
    boolean onIsland, echoedForward, landAhead;
    int i = 0;
    public void run() // RECIEVES DATA SOMEHOW
    {
        if(!onIsland)
        {
            if(echoedForward)
            {
                if(landAhead)
                {
                    int j = 0;
                    // fly forward range many times
                    int num = 10; // num for how far echo range is PLACEHOILDEFR
                    if(j < num)
                    {
                        // fly forward
                    }
                }
                else
                {
                    int j = 0;
                    // turn
                    j++;
                    
                    if(j == 2)
                    {
                        onIsland = true;
                        echoedForward = false;
                        landAhead = true;
                    }  
                }
            }
            else
            {
                // action = echo
                // direction = current direction
            }
        }
        else
        {
            if(i % 2 == 0)
            {
                // action = fly
            }
            else
            {
                // action = scan
            }
        }
    }

    // loop:

    // if not on island
    
            //echo forward is out of range

            // if something in front fly range amount of time

            // else both false, turn twice

    // else (on island)

    // forward

    // scan

    // turn x2



}
