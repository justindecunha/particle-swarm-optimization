import java.util.Random;

/**
 * This is a vanilla PSO implementation tested on the rastrigin function. Parameters are specified at the top of this
 * file, which is simply executed as normal.
 */
public class PSO {

    final static int DIMENSION = 30;  //number of dimensions to use for rastrigin function
    final static int SWARM_SIZE = 30; // The size of the swarm to use
    final static int MAX_ITERATION = 500; // The maximum number of iterations to go through
    final static double C1 = 1.496180;  //C1 and C2 values given in the slides
    final static double C2 = 1.496180;
    final static double W = 0.729844; // W value given in the slides, shown to result in convergence


    private double globalBestFitness; // The best fitness found thus far
    private Location globalBestLocation; // The best location found thus far
    private Random rand = new Random();  // random number generator
    private Particle[] swarm;  // This particle array represents the swarm we will be using

    PSO() {
        initParticles(); // initializing the particles
        for (int iteration = 0; iteration < MAX_ITERATION; iteration++) {

            //update best values
            for (int i = 0; i < SWARM_SIZE; i++)
                if (swarm[i].getFitness() < swarm[i].getBestFitness() && swarm[i].isViable())
                    swarm[i].setBest();


            //update global best particle
            double iterationMin = swarm[0].getFitness();
            int minPos = 0;
            for (int i = 0; i < SWARM_SIZE; i++) {
                if (swarm[i].getFitness() < iterationMin) {
                    iterationMin = swarm[i].getFitness();
                    minPos = i;
                }
            }

            if (iterationMin < globalBestFitness) {
                globalBestLocation = swarm[minPos].getLocation();
                globalBestFitness = iterationMin;
            }

            for (int i = 0; i < SWARM_SIZE; i++) {
                double[] speed = new double[DIMENSION];

                for(int j = 0; j < DIMENSION; j++) {
                    double r1 = rand.nextDouble();
                    double r2 = rand.nextDouble();

                    double s = (W * swarm[i].getSpeed().getX(j)) +
                            (r1 * C1) * (swarm[i].getBestLocation().getX(j) - swarm[i].getLocation().getX(j)) +
                            (r2 * C2) * (globalBestLocation.getX(j) - swarm[i].getLocation().getX(j));
                    speed[j] = s;
                }

                swarm[i].setSpeed(new Speed(speed));

                double[] loc = new double[DIMENSION];
                swarm[i].setViable(true);
                for(int j = 0; j < DIMENSION; j++) {
                    loc[j] = swarm[i].getLocation().getX(j) + speed[j];
                    if(loc[j] > Rastrigin.UPPER_BOUND || loc[j] < Rastrigin.LOWER_BOUND) {
                        swarm[i].setViable(false);
                    }
                }

                swarm[i].setLocation(new Location(loc));

            }
        }

        System.out.println("Mean: " + Statistics.mean(swarm));
        System.out.println("Median: " + Statistics.median(swarm));
        System.out.println("Std: " + Statistics.std(swarm));

    }

    /**
     * Initializes the swarm with particles randomly located according to the x and y bounds provided in Rastrigin.java
     * and with speeds set to 0
     */
    private void initParticles() {

        swarm = new Particle[SWARM_SIZE]; // swarm is initialized using specified swarm_size parameter

        Location l;
        Speed s;

        // Initializing speed to 0 and random location based on the bounds provided in ackleys.java
        for (int i = 0; i < SWARM_SIZE; i++) {
            double[] speed = new double[DIMENSION];
            double[] location = new double[DIMENSION];
            for(int j = 0; j < DIMENSION; j++) {
                location[j] = Rastrigin.LOWER_BOUND + rand.nextDouble() * (Rastrigin.UPPER_BOUND - Rastrigin.LOWER_BOUND);
                speed[j] = 0.0;
            }

            l = new Location(location);
            s = new Speed(speed);

            swarm[i] = new Particle(Rastrigin.evaluate(l), l, s);
        }

        //Assigning temporary arbitrary values to global best
        globalBestLocation = swarm[0].getLocation();
        globalBestFitness = swarm[0].getFitness();


    }

    public static void main(String[] args) {
        new PSO();
    }
}
