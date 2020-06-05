package ru.javaops.masterjava.matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * gkislin
 * 03.07.2016
 */
public class MainMatrix {
    private static final int MATRIX_SIZE = 1000;
    private static final int THREAD_NUMBER = 10;

    private final static ExecutorService executor = Executors.newFixedThreadPool(MainMatrix.THREAD_NUMBER);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int[][] matrixA = MatrixUtil.create(MATRIX_SIZE);
        final int[][] matrixB = MatrixUtil.create(MATRIX_SIZE);

        double singleThreadSum = 0.;
        double concurrentThreadSum = 0.;
        double singleThreadSumOpt1 = 0.;
        double singleThreadSumOpt2 = 0.;
        int count = 1;
        while (count < 6) {
            System.out.println("Pass " + count);
            long start = System.currentTimeMillis();
            final int[][] matrixC = MatrixUtil.singleThreadMultiply(matrixA, matrixB);
            double duration = (System.currentTimeMillis() - start) / 1000.;
            out("Single thread time, sec: %.3f", duration);
            singleThreadSum += duration;

            start = System.currentTimeMillis();
            final int[][] singleThreadMultiplyOpt1 = MatrixUtil.singleThreadMultiplyOpt1(matrixA, matrixB);
            duration = (System.currentTimeMillis() - start) / 1000.;
            out("singleThreadMultiplyOpt1 thread time, sec: %.3f", duration);
            singleThreadSumOpt1 += duration;

            if (!MatrixUtil.compare(matrixC, singleThreadMultiplyOpt1)) {
                System.err.println("singleThreadMultiplyOpt1 Comparison failed");
                break;
            }

            start = System.currentTimeMillis();
            final int[][] singleThreadMultiplyOpt2 = MatrixUtil.singleThreadMultiplyOpt2(matrixA, matrixB);
            duration = (System.currentTimeMillis() - start) / 1000.;
            out("singleThreadMultiplyOpt2 thread time, sec: %.3f", duration);
            singleThreadSumOpt2 += duration;

            if (!MatrixUtil.compare(matrixC, singleThreadMultiplyOpt2)) {
                System.err.println("singleThreadMultiplyOpt2 Comparison failed");
                break;
            }

            start = System.currentTimeMillis();
            final int[][] concurrentMatrixC = MatrixUtil.concurrentMultiply(matrixA, matrixB, executor);
            duration = (System.currentTimeMillis() - start) / 1000.;
            out("Concurrent thread time, sec: %.3f", duration);
            concurrentThreadSum += duration;

            if (!MatrixUtil.compare(matrixC, concurrentMatrixC)) {
                System.err.println("Comparison failed");
                break;
            }
            count++;
        }
        executor.shutdown();
        out("Average single thread time, sec: %.3f", singleThreadSum / 5.);
        out("Average single singleThreadSumOpt1 time, sec: %.3f", singleThreadSumOpt1 / 5.);
        out("Average single singleThreadSumOpt2 time, sec: %.3f", singleThreadSumOpt2 / 5.);
        out("Average concurrent thread time, sec: %.3f", concurrentThreadSum / 5.);
    }

    private static void out(String format, double ms) {
        System.out.println(String.format(format, ms));
    }
}
