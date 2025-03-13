package ca.mcmaster.se2aa4.island.teamXXX;

public class Actions 
{
    String key;
    String value;
    boolean coastFound, creekFound, emergencySiteFound = false;
    int actCount = 0;

    Coast coast = new Coast();
    Creek creek = new Creek();
    EmergencySite emergencySite = new EmergencySite();

    public void search()
    {
        // example just for now
        if (actCount == 50)
        {
            key = "action";
            value = "stop";
        }
        else if(actCount % 2 == 0)
        {
            key = "action";
            value = "fly";
        }
        else
        {
            key = "action";
            value = "scan";
        }
        actCount++;
        /*
        if(!coastFound)
    {
        // coast.run algorithm...
        key = "action";
        value = "fly";
        actCount++;
    }  
    else if(!creekFound)
    {
        // creek.run something
    }
    else if(!emergencySiteFound)
    {
        // emergency site . run something here
    }
    else
    {
        key = "action";
        value = "stop";
    }    
    */

    }

    public String getKey()
    {
        return key;
    }    

    public String getValue()
    {
        return value;
    }
}
