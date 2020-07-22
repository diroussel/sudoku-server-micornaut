package sudoku.web;

import sudoku.domain.BoardPair;
import sudoku.domain.SudokuAlgo;

import javax.inject.Singleton;
import java.util.Random;

@Singleton
public class SudokuService {

    SudokuAlgo algo = new SudokuAlgo();

    public BoardPair generateBoards(long seed) {
        Random rand = new Random(seed);
        return algo.generate(rand);
    }
}
