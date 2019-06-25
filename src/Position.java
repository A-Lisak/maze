public class Position {
    private int x, y;

     Position(int y, int x) {
        this.x = x;
        this.y = y;
    }

     int getX() {
        return x;
    }

     int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(x, y) = (" + x + ", " + y + ")";
    }
}