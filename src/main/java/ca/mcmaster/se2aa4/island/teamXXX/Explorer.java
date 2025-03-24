package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.Reader;
import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.BasicReport;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Drone;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.IslandFinder;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Report;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.Operation;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.POIFinder;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.ReportReader;
import main.java.ca.mcmaster.se2aa4.island.teamXXX.State;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private Drone drone;
    private IslandFinder islandFinder;
    private POIFinder poiFinder;

    private Operation lastOp = Operation.FLY;
    private Report report = new BasicReport(0);
    private ReportReader reader = new ReportReader();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        drone = new Drone(batteryLevel, direction, State.PHASE1);
        islandFinder = new IslandFinder(drone);
        poiFinder = new POIFinder(drone);

    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        if (drone.getPhase() == State.PHASE1){
            decision = islandFinder.find(report,lastOp);
        }
        else{
            decision = poiFinder.find(report, lastOp);
        }
        

        String action = decision.getString("action");
        if(action.equals("scan")){lastOp = Operation.SCAN;}
        else if (action.equals("fly")){ lastOp = Operation.FLY;}
        else if (action.equals("heading")){lastOp = Operation.HEADING;}
        else if (action.equals("echo")){lastOp = Operation.ECHO;}
        else {lastOp = Operation.STOP;}

        return decision.toString();
    }   

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        report = reader.read(response,lastOp);
    }

    @Override
    public String deliverFinalReport() 
    {
        System.out.println("Island found");
        return "no creek found";
    }

}
