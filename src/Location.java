/**
 * Our location representation, simply 2 double values
 */
public class Location {
    private int length;
    private double x[];

    public Location(double x[]) {
        this.x = x;
        this.length = x.length;
    }

    public double getX(int i) {
        return x[i];
    }

    public int getLength() {
        return this.length;
    }

}

