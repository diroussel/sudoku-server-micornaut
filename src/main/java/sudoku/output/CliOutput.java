package sudoku.output;

import sudoku.domain.SudokuBoard;

import java.util.StringJoiner;
import java.util.function.Consumer;

public class CliOutput implements SudokuOutput {
    @Override
    public void render(SudokuBoard board, Consumer<String> writer) {
        for (int i = 0; i < 9; i++) {
            StringJoiner joiner = new StringJoiner("\t");
            for (int j = 0; j < 9; j++) {
                joiner.add(formatCell(board.get(i, j)));
            }
            writer.accept(joiner.toString());
            writer.accept("\n");
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
