package ca.mcmaster.se2aa4.island.team40;

public class Calculator {
    
    public double calculate(int[] creek, int[] site) {
        double xdistance = Math.pow(creek[0] - site[0],2);
        double ydistance = Math.pow(creek[1] - site[1],2);

        double result = Math.sqrt(xdistance+ydistance);
        return result;
    }
}
