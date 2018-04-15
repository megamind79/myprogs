package MyHelperClasses;

public class Point {
    /**
     * Class to create a point
     */
    private int X, Y;

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public boolean equals (Object point) {
        return X==((Point)point).X&&Y==((Point)point).Y;
    }

    @Override
    public int hashCode () {
        String string = X + " " + Y;
        return string.hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", X, Y);
    }

    /**
     * get value of X
     * @return value of X
     */
    public int x() {
        return X;
    }

    /**
     * get va;ue of Y
     * @return value of Y
     */
    public int y() {
        return Y;
    }
}
