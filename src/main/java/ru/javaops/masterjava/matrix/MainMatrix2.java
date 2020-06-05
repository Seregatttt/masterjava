package ru.javaops.masterjava.matrix;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * gkislin
 * 03.07.2016
 */
public class MainMatrix2 {
    private static final int MATRIX_SIZE = 2;
    private static final int THREAD_NUMBER = 10;

    private final static ExecutorService executor = Executors.newFixedThreadPool(MainMatrix2.THREAD_NUMBER);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int[][] matrixA = new int[][]{{1,2},{3,4}};
        final int[][] matrixB = new int[][]{{4,3},{2,1}};

        final int[][] concurrentMatrixC = MatrixUtil.concurrentMultiply(matrixA, matrixB, executor);

        for (int i = 0; i < concurrentMatrixC.length; i++) {
            for (int j = 0; j < concurrentMatrixC.length; j++) {
                System.out.print(concurrentMatrixC[i][j]+" ");
            }
            System.out.println(" ");
        }

        executor.shutdown();

        final int[][] matrixC = MatrixUtil.singleThreadMultiply(matrixA, matrixB);
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC.length; j++) {
                System.out.print(matrixC[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
