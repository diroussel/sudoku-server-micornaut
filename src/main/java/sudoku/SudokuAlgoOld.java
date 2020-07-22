package sudoku;

import java.io.IOException;
import java.util.Random;
import java.util.StringJoiner;

public class SudokuAlgoOld {

    private static int[][] blank_array() {
        int[][] x = new int[10][];
        for (int i = 0; i < 9; i++) {
            x[i] = new int[10];
        }
        return x;
    }

    public static void generate(int[][] grid) {
        int k = 1, n = 1;
        for (int i = 0; i < 9; i++) {
            k = n;
            for (int j = 0; j < 9; j++) {
                if (k <= 9) {
                    grid[i][j] = k;
                    k++;
                } else {
                    k = 1;
                    grid[i][j] = k;
                    k++;
                }
            }
            n = k + 3;
            if (k == 10)
                n = 4;
            if (n > 9)
                n = (n % 9) + 1;
        }
    }

    public static void random_gen(int[][] grid, Random rand, int check) {
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
    public static void permutation_row(int[][] grid, int k1, int k2) {
        int temp;//k1 and k2 are two rows that we are selecting to interchange.
        for (int j = 0; j < 9; j++) {
            temp = grid[k1][j];
            grid[k1][j] = grid[k2][j];
            grid[k2][j] = temp;
        }
    }

    public static void permutation_col(int[][] grid, int k1, int k2) {
        int temp;
        for (int j = 0; j < 9; j++) {
            temp = grid[j][k1];
            grid[j][k1] = grid[j][k2];
            grid[j][k2] = temp;
        }
    }

    public static void row_change(int[][] grid, int k1, int k2) {
        int temp;
        for (int n = 1; n <= 3; n++) {
            for (int i = 0; i < 9; i++) {
                temp = grid[k1][i];
                grid[k1][i] = grid[k2][i];
                grid[k2][i] = temp;
            }
            k1++;
            k2++;
        }
    }

    public static void col_change(int[][] grid, int k1, int k2) {
        int temp;
        for (int n = 1; n <= 3; n++) {
            for (int i = 0; i < 9; i++) {
                temp = grid[i][k1];
                grid[i][k1] = grid[i][k2];
                grid[i][k2] = temp;
            }
            k1++;
            k2++;
        }
    }

    public static void strike_out(int[][] grid, int k1, int k2) {
        int row_from;
        int row_to;
        int col_from;
        int col_to;
        int i, j, b, c;
        int rem1, rem2;
        int flag;
        int temp = grid[k1][k2];
        int count = 9;
        for (i = 1; i <= 9; i++) {
            flag = 1;
            for (j = 0; j < 9; j++) {
                if (j != k2) {
                    if (i != grid[k1][j]) {
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
                        if (i != grid[c][k2]) {
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
                            if (i != grid[c][b])
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
            grid[k1][k2] = 0;
            count--;
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] a = blank_array();
        int counter = 1, k1, k2;
        Random rand = new Random();
        generate(a);
        random_gen(a, rand, 1);
        random_gen(a, rand, 0);
        System.out.println();
        printGrid(a);

        int[] n = {0, 3, 6};
        for (int i = 0; i < 2; i++) {
            k1 = n[rand.nextInt(n.length)];
            do {
                k2 = n[rand.nextInt(n.length)];
            } while (k1 == k2);
            if (counter == 1)
                row_change(a, k1, k2);
            else col_change(a, k1, k2);
            counter++;
        }

        //Striking out
        for (k1 = 0; k1 < 9; k1++) {
            for (k2 = 0; k2 < 9; k2++)
                strike_out(a, k1, k2);
        }
        System.out.println();
        printGrid(a);
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            StringJoiner joiner = new StringJoiner("\t");
            for (int j = 0; j < 9; j++) {
                joiner.add(formatCell(grid[i][j]));
            }
            System.out.println(joiner.toString());
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