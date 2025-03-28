package ca.mcmaster.se2aa4.island.team40;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;


public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private Drone drone;
    private IslandFinder islandSearch;
    private POIFinder poiSearch;
    private POI spots;

    private Operation prevOp = Operation.NONE;
    private Report report = new BasicReport(0);
    private final ReportReader reader = new ReportReader();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString(Constants.HEADING);
        Integer batteryLevel = info.getInt("budget");

        drone = new Drone(batteryLevel, direction, State.PHASE0);
        spots = new POI();
        islandSearch = new IslandFinder(drone, spots);
        poiSearch = new POIFinder(drone, spots);

    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        if (null == drone.getPhase()){
            decision = poiSearch.find(report, prevOp);
        }
        else decision = switch (drone.getPhase()) {
            case PHASE0 -> islandSearch.fixStartingPosition(report,prevOp);
            case PHASE1 -> islandSearch.find(report,prevOp);
            default -> poiSearch.find(report, prevOp);
        };
        

        String action = decision.getString(Constants.ACTION);
        prevOp = switch (action) {
            case Constants.SCAN -> Operation.SCAN;
            case Constants.FLY -> Operation.FLY;
            case Constants.HEADING -> Operation.HEADING;
            case Constants.ECHO -> Operation.ECHO;
            default -> Operation.STOP;
        };

        return decision.toString();
    }   

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        report = reader.read(response,prevOp);
    }

    @Override
    public String deliverFinalReport() 
    {   
        return spots.getCreekId() ;
    }

}
