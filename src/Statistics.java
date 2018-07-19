/**
 * This class is used to calculate basic statistics to print out about the swarm
 */
public class Statistics {

    /**
     * Gets the mean
     * @param p the particle array to calculate with
     * @return the mean
     */
    public static double mean(Particle[] p) {
        double sum = 0;
        for (int i = 0; i < p.length; i++) {
            sum += p[i].getFitness();
        }
        return sum / p.length;
    }

    /**
     * Returns the median
     * @param p the particle array to calculate with
     * @return the median
     */
    public static double median(Particle[] p) {
        int middle = p.length/2;
        if (p.length%2 == 1) {
            return p[middle].getFitness();
        } else {
            return (p[middle-1].getFitness() + p[middle].getFitness()) / 2.0;
        }
    }

    public static double std(Particle[] p) {
        double mean = mean(p);
        double temp = 0;
        for(Particle z: p)
            temp += (z.getFitness()-mean)*(z.getFitness()-mean);
        return Math.sqrt(temp/(p.length-1));
    }
}
