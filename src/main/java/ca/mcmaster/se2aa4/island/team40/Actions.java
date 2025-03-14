package ca.mcmaster.se2aa4.island.team40;

public class Actions 
{
    String key;
    String value;
    boolean coastFound, creekFound, emergencySiteFound = false;

    Coast coast = new Coast(this);

    Creek creek = new Creek();
    EmergencySite emergencySite = new EmergencySite();

    public void search()
    {
        
        if(!coastFound)
    {
       coast.mainLoop();
    }  

    /*
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

    public void setKey(String newKey)
    {
        this.key = newKey;
    }

    public void setValue(String newValue)
    {
        this.key = newValue;
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
