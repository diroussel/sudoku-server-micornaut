package sudoku.output;

import sudoku.domain.SudokuBoard;

import java.io.IOException;
import java.io.Writer;

public interface SudokuOutput {
    void render(SudokuBoard board, Writer writer) throws IOException;
}
