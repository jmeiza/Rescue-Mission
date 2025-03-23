package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.BasicParser;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.BasicReport;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Drone;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.EchoParser;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.IslandFinder;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Report;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.ScanParser;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Operation;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Parser;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private Report report = new BasicReport(2);
    private Operation lastOp;
    private Parser parser;
    private IslandFinder islandFinder;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        drone = new Drone(batteryLevel, direction);
        islandFinder = new IslandFinder(drone);

        // logger.info("The drone is facing {}", direction);
        // logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = islandFinder.find(report);

        String action = decision.getString("action");
        if(action.equals("scan")){lastOp = Operation.SCAN;}
        else if (action.equals("fly")){ lastOp = Operation.FLY;}
        else if (action.equals("heading")){lastOp = Operation.HEADING;}
        else if (action.equals("echo")){lastOp = Operation.ECHO;}
        else {lastOp = Operation.STOP;}

        return decision.toString();

        // JSONObject decision = new JSONObject();
        // decision.put("action", "stop"); // we stop the exploration immediately
        // logger.info("** Decision: {}",decision.toString());
        // return decision.toString();
    }   

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        if (lastOp == Operation.ECHO){
            parser = new EchoParser(response);
        }
        else if (lastOp == Operation.SCAN){
            parser = new ScanParser(response);
        }
        else {
            parser = new BasicParser(response);
        }
        
        report = parser.parse();
    //     logger.info("** Response received:\n"+response.toString(2));
    //     Integer cost = response.getInt("cost");
    //     logger.info("The cost of the action was {}", cost);
    //     String status = response.getString("status");
    //     logger.info("The status of the drone is {}", status);
    //     JSONObject extraInfo = response.getJSONObject("extras");
    //     logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() 
    {
        System.out.println("Island found");
        return "no creek found";
    }

}
