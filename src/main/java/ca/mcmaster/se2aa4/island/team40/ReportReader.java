package ca.mcmaster.se2aa4.island.team40;

import org.json.JSONObject;

/*THIS CLASS IS REPSONSIBLE FOR CALLING A SPECIFIC FORM OF PARSER BASED ON THE LAST 
 * OPERATION TO BE PERFORMED
 */
public class ReportReader {
    private Parser parser;

    public Report read(JSONObject response, Operation op){
        if (null == op){
            parser = new BasicParser();
            return parser.parse(response);
        }
        else switch (op) {
            case SCAN -> {
                parser = new ScanParser();
                return parser.parse(response);
            }
            case ECHO -> {
                parser = new EchoParser();
                return parser.parse(response);
            }
            default -> {
                parser = new BasicParser();
                return parser.parse(response);
            }
        }
    } 
}
