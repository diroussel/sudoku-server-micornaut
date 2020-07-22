package sudoku.domain;

public class BoardPair {
    public final SudokuBoard solution;
    public final SudokuBoard puzzle;

    public BoardPair(SudokuBoard solution, SudokuBoard puzzle) {
        this.solution = solution;
        this.puzzle = puzzle;
    }
}
