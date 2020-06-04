package ru.javaops.masterjava.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    public static int[][] concurrentMultiply(int[][] A, int[][] B, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = A.length;
        final int[][] C = new int[matrixSize][matrixSize];
        final CompletionService<int[]> completionService = new ExecutorCompletionService<>(executor);

        for (int j = 0; j < matrixSize; j++) {
            completionService.submit(() -> {
                final int[] columnC = new int[matrixSize];
                return new int[]{1, 2};
            });
        }

        for (int i = 0; i < matrixSize; i++) {

            int[] res = completionService.take().get();

            //  for (int k = 0; k < matrixSize; k++) {
            C[i] = res;
            //  C[k][res.col] = res.columnC[k];
            //  }
        }
        return C;
    }

    // TODO optimize by https://habrahabr.ru/post/114797/
    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];


        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int sum = 0;
                for (int k = 0; k < matrixSize; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }

                matrixC[i][j] = sum;
            }
        }
        return matrixC;
    }

    public static int[][] singleThreadMultiplyOpt1(int[][] A, int[][] B) {
        final int matrixSize = A.length;
        final int[][] C = new int[matrixSize][matrixSize];
        final int aColumns = A.length;
        final int aRows = A.length;
        final int bColumns = B.length;
        final int bRows = B.length;

        int[] thatColumn = new int[bRows];
        try {
            for (int j = 0; ; j++) {
                for (int k = 0; k < aColumns; k++) {
                    thatColumn[k] = B[k][j];
                }

                for (int i = 0; i < aRows; i++) {
                    int[] thisRow = A[i];
                    int summand = 0;
                    for (int k = 0; k < aColumns; k++) {
                        summand += thisRow[k] * thatColumn[k];
                    }
                    C[i][j] = summand;
                }
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        return C;
    }

    public static int[][] singleThreadMultiplyOpt2(int[][] A, int[][] B) {
        final int matrixSize = A.length;
        final int[][] C = new int[matrixSize][matrixSize];
        final int aColumns = A.length;
        final int aRows = A.length;
        final int bColumns = B.length;
        final int bRows = B.length;

        int thatColumn[] = new int[bRows];
        for (int j = 0; j < bColumns; j++) {
            for (int k = 0; k < aColumns; k++) {
                thatColumn[k] = B[k][j];
            }

            for (int i = 0; i < aRows; i++) {
                int thisRow[] = A[i];
                int summand = 0;
                for (int k = 0; k < aColumns; k++) {
                    summand += thisRow[k] * thatColumn[k];
                }
                C[i][j] = summand;
            }
        }
        return C;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
