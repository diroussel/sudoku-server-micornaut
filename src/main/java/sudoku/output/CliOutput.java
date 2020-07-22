package sudoku.output;

import sudoku.domain.SudokuBoard;

import java.io.IOException;
import java.io.Writer;
import java.util.StringJoiner;

public class CliOutput implements SudokuOutput {
    @Override
    public void render(SudokuBoard board, Writer writer) throws IOException {
        for (int i = 0; i < 9; i++) {
            StringJoiner joiner = new StringJoiner("\t");
            for (int j = 0; j < 9; j++) {
                joiner.add(formatCell(board.get(i, j)));
            }
            writer.write(joiner.toString());
            writer.write('\n');
        }
    }

    private static String formatCell(int value) {
        if (value == 0) {
            return "_";
        } else {
            return Integer.toString(value);
        }
    }
}
