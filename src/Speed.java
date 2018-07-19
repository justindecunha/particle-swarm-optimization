/**
 * Velocity representation, simply 2 doubles once again
 */
public class Speed {
    private int length;
    private double x[];

    public Speed(double x[]) {
        this.x = x;
        this.length = x.length;
    }

    public double getX(int i) {
        return x[i];
    }

    public int getLength() {
        return this.length;
    }

    public void setX(int i, double x) {
        this.x[i] = x;
    }
}
