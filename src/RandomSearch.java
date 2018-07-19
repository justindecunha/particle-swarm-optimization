import java.util.Random;

/**
 * This class is a random search implementation for the rastrigin function, used for benchmarking against
 * the vanilla PSO algorithm. Simply takes x random samples within the specified bounds, and returns the best
 * result found.
 *
 * Samples should equal particles * iterations of PSO to be a fair comparision
 */
public class RandomSearch {

    /**
     * @param dimension number of dimensions that represent a location. A series of coordinates
     * @param samples   Number of samples the random search should take within the search space
     */
    RandomSearch(int dimension, int samples) {
        Random r = new Random();
        double[] randomSampleLocation = new double[dimension];

        double bestLocationFitness = Double.POSITIVE_INFINITY;
        for(int i = 0; i < samples; i++) {
            // initialize random position
            for (int j = 0; j < dimension; j++)
                randomSampleLocation[j] = Rastrigin.LOWER_BOUND + r.nextDouble() * (Rastrigin.UPPER_BOUND - Rastrigin.LOWER_BOUND);

            Location l = new Location(randomSampleLocation);
            if(Rastrigin.evaluate(l) < bestLocationFitness)
                bestLocationFitness = Rastrigin.evaluate(l);
        }
        System.out.println(bestLocationFitness);
    }

    public static void main(String[] args) { new RandomSearch(30, 30 * 500); }
}
