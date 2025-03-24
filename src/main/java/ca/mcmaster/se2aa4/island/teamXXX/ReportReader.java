package main.java.ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class ReportReader {
    private Parser parser;

    public Report read(JSONObject response, Operation op){
        if (op == Operation.SCAN){
            parser = new ScanParser();
            return parser.parse(response);
        }
        else if (op == Operation.ECHO){
            parser = new EchoParser();
            return parser.parse(response);
        }
        else {
            parser = new BasicParser();
            return parser.parse(response);
        }
    } 
}
