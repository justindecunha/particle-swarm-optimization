/**
 * Stores the bounds and the fitness functions for each of the implemented functions
 */
public class Rastrigin {
    //Particle initialization bounds for each function
    public static final double LOWER_BOUND = -5.12;
    public static final double UPPER_BOUND = 5.12;

    public static double evaluate(Location location) {
        double sum = 0;
        for (int i = 0; i < location.getLength(); i++) {
           sum += location.getX(i) * location.getX(i) - 10 * Math.cos(2*Math.PI*location.getX(i));
        }

        return sum + 10 * location.getLength();
    }
}