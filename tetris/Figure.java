package tetris;

public class Figure {
    private int x;
    private int y;
    private final int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    void left() {
        x = x - 1;
        if (!this.isCurrentPositionAvailable()) {
            x = x + 1;
        }
    }

    void right() {
        x = x + 1;
        if (!this.isCurrentPositionAvailable()) {
            x = x - 1;
        }
    }

    void down() {
        y = y + 1;
    }

    void up() {
        y = y - 1;
    }

    void downMaximum() {

    }

    void rotate() {

    }

    boolean isCurrentPositionAvailable() {
        return true;
    }

    void landed() {

    }
}

