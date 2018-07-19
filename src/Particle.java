/**
 * This class represents our Particle, and includes fitness, location, speed (velocity) and best location/fitness
 */
public class Particle {
    private double fitness;
    private double bestFitness;
    private Location location;
    private Location bestLocation;
    private Speed speed;
    private boolean isViable;

    public Particle(double fitness, Location location, Speed speed) {
        this.fitness = fitness;
        bestFitness = this.fitness;
        this.location = location;
        bestLocation = this.location;
        this.speed = speed;
        this.isViable = true;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isViable() {return isViable;}

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public Location getBestLocation() {
        return bestLocation;
    }

    public void setViable(boolean isViable) {
        this.isViable = isViable;
    }
    public void setBest() {
        bestFitness = fitness;
        bestLocation = location;
    }

    public double getFitness() {
        fitness = Rastrigin.evaluate(location);
        return fitness;
    }
}

