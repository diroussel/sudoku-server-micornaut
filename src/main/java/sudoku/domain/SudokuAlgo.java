package sudoku.domain;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuAlgo {

    public static void generate(SudokuBoard grid) {
        int k, n = 1;
        for (int i = 0; i < 9; i++) {
            k = n;
            for (int j = 0; j < 9; j++) {
                if (k > 9) {
                    k = 1;
                }
                grid.set(i, j, k);
                k++;
            }
            n = k + 3;
            if (k == 10)
                n = 4;
            if (n > 9)
                n = (n % 9) + 1;
        }
    }

    public static void random_gen(SudokuBoard grid, Random rand, int check) {
        int k1, k2, max = 2, min = 0;
        for (int i = 0; i < 3; i++) {
            //There are three groups.So we are using for loop three times.
            k1 = rand.nextInt(max - min + 1) + min;
            //This while is just to ensure k1 is not equal to k2.
            do {
                k2 = rand.nextInt(max - min + 1) + min;
            } while (k1 == k2);
            max += 3;
            min += 3;
            //check is global variable.
            //We are calling random_gen two time from the main func.
            //Once it will be called for columns and once for rows.
            if (check == 1)
                //calling a function to interchange the selected rows.
                permutation_row(grid, k1, k2);
            else if (check == 0)
                permutation_col(grid, k1, k2);
        }
    }

    //For row
    public static void permutation_row(SudokuBoard grid, int k1, int k2) {
        for (int j = 0; j < 9; j++) {
            //k1 and k2 are two rows that we are selecting to interchange.
            int temp = grid.get(k1, j);
            grid.set(k1, j, grid.get(k2, j));
            grid.set(k2, j, temp);
        }
    }

    public static void permutation_col(SudokuBoard grid, int k1, int k2) {
        for (int j = 0; j < 9; j++) {
            int temp = grid.get(j, k1);
            grid.set(j, k1, grid.get(j, k2));
            grid.set(j, k2, temp);
        }
    }

    public static void row_change(SudokuBoard grid, int k1, int k2) {
        for (int n = 1; n <= 3; n++) {
            for (int i = 0; i < 9; i++) {
                int temp = grid.get(k1, i);
                grid.set(k1, i, grid.get(k2, i));
                grid.set(k2, i, temp);
            }
            k1++;
            k2++;
        }
    }

    public static void col_change(SudokuBoard grid, int k1, int k2) {
        for (int n = 1; n <= 3; n++) {
            for (int i = 0; i < 9; i++) {
                int temp = grid.get(i, k1);
                grid.set(i, k1, grid.get(i, k2));
                grid.set(i, k2, temp);
            }
            k1++;
            k2++;
        }
    }

    public static void strike_out(SudokuBoard grid, int k1, int k2) {
        int row_from;
        int row_to;
        int col_from;
        int col_to;
        int i, j, b, c;
        int rem1, rem2;
        int flag;
        int temp = grid.get(k1, k2);
        int count = 9;
        for (i = 1; i <= 9; i++) {
            flag = 1;
            for (j = 0; j < 9; j++) {
                if (j != k2) {
                    if (i != grid.get(k1, j)) {
                        continue;
                    } else {
                        flag = 0;
                        break;
                    }
                }
            }
            if (flag == 1) {
                for (c = 0; c < 9; c++) {
                    if (c != k1) {
                        if (i != grid.get(c, k2)) {
                            continue;
                        } else {
                            flag = 0;
                            break;
                        }
                    }
                }
            }
            if (flag == 1) {
                rem1 = k1 % 3;
                rem2 = k2 % 3;
                row_from = k1 - rem1;
                row_to = k1 + (2 - rem1);
                col_from = k2 - rem2;
                col_to = k2 + (2 - rem2);
                for (c = row_from; c <= row_to; c++) {
                    for (b = col_from; b <= col_to; b++) {
                        if (c != k1 && b != k2) {
                            if (i != grid.get(c, b))
                                continue;
                            else {
                                flag = 0;
                                break;
                            }
                        }
                    }
                }
            }
            if (flag == 0)
                count--;
        }
        if (count == 1) {
            grid.set(k1, k2, 0);
            count--;
        }
    }

    public static BoardPair generate(Random rand) {
        SudokuBoard solution = new SudokuBoard();
        int counter = 1, k1, k2;
        generate(solution);
        random_gen(solution, rand, 1);
        random_gen(solution, rand, 0);

        int[] n = {0, 3, 6};
        for (int i = 0; i < 2; i++) {
            k1 = n[rand.nextInt(n.length)];
            do {
                k2 = n[rand.nextInt(n.length)];
            } while (k1 == k2);
            if (counter == 1)
                row_change(solution, k1, k2);
            else col_change(solution, k1, k2);
            counter++;
        }
        SudokuBoard puzzle = solution.copy();

        //Striking out
        int[] ks = shuffleArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, rand);
        int[] js = shuffleArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, rand);
        for (int k : ks) {
            for (int j : js) {
                strike_out(puzzle, k, j);
            }
        }
        return new BoardPair(solution, puzzle);
    }

    // Implementing Durstenfeld shuffle.
    static int[] shuffleArray(int[] ar, Random rand) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}