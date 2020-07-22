package sudoku.domain;

import io.micronaut.core.annotation.Introspected;

import java.util.Arrays;
import java.util.List;

@Introspected
public class SudokuBoard {
    private final int[][] rows;

    public SudokuBoard() {
        this(blank_array());
    }

    public SudokuBoard(int[][] grid) {
        rows = grid;
    }

    public SudokuBoard copy() {
        int[][] newGrid = new int[9][];
        for (int i = 0; i < 9; i++) {
            int[] newRow = new int[10];
            System.arraycopy(rows[i], 0, newRow, 0, 9);
            newGrid[i] = newRow;
        }
        return new SudokuBoard(newGrid);
    }

    public int get(int i, int j) {
        return rows[i][j];
    }

    public void set(int i, int j, int value) {
        rows[i][j] = value;
    }

    private static int[][] blank_array() {
        int[][] x = new int[9][];
        for (int i = 0; i < 9; i++) {
            x[i] = new int[9];
        }
        return x;
    }

    public List<int[]> getRows() {
        return Arrays.asList(rows);
    }
}
