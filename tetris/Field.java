package tetris;

import java.util.*;

public class Field {
    private final int width;
    private final int height;
    private int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        matrix = new int[height][width];
    }

    void print() {

        String[][] view = new String[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    System.out.print(".");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }

    }

    void removeFullLines() {

        ArrayList<int[]> array = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            int count = 0;

            for (int j = 0; j < height; j++) {
                count += matrix[i][j];
            }

            if (count != width) {
                array.add(matrix[i]);
            }


        }

        while (array.size() < height) {
            array.add(0, new int[width]);
        }
        matrix = array.toArray(new int[height][width]);

    }

    Integer getValue(int x, int y) {
        return null;
    }

    void setValue(int x, int y, int value) {

    }
}

