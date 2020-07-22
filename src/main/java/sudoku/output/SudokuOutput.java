package sudoku.output;

import sudoku.domain.SudokuBoard;

import java.util.function.Consumer;

public interface SudokuOutput {
    void render(SudokuBoard board, Consumer<String> writer);
}
