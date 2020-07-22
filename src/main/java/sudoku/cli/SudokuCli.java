package sudoku.cli;

import sudoku.domain.BoardPair;
import sudoku.domain.SudokuAlgo;
import sudoku.domain.SudokuBoard;
import sudoku.output.CliOutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Optional;
import java.util.Random;
import java.util.StringJoiner;

public class SudokuCli {

    public static void main(String[] args) throws IOException {

        Random rand = readArg("seed", args)
                .map(Integer::parseInt)
                .map(Random::new)
                .orElseGet(Random::new);

        BoardPair p = SudokuAlgo.generate(rand);

        CliOutput output = new CliOutput();
        PrintWriter printWriter = new PrintWriter(System.out);

        printWriter.println("Solution: ");
        output.render(p.solution, printWriter::println);
        printWriter.println("Puzzle: ");
        output.render(p.puzzle, printWriter::println);
        printWriter.flush();
    }


    static Optional<String> readArg(String argName, String[] args) {
        String fullName = "--" + argName;
        if (args.length < 2) {
            return Optional.empty();
        }
        for (int i = 0; i < args.length -1; i++) {
            if (fullName.equals(args[i])) {
                return Optional.of(args[i+1]);
            }
        }
        return Optional.empty();
    }
}
