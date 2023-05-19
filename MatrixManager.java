/**
 * MatrixManger class takes care of running the different processes properly
 * attributes to initialize matrix's with numbers from 1-10 and to keep track of processes
 * has build function to create matrix's together
 * gives row and column to calculate for processes for each matrix
 */

import java.util.Random;


public class MatrixManager {
    /*random numbers to be between min and max*/
    final private static int MIN = 1;
    final private static int MAX = 10;

    /*matrix,row,column variables attributes */
    private int[][] matA;
    private int[][] matB;
    private static int rowA;
    private static int colA;
    private static int rowB;
    private static int colB;

    /*attributes to keep track of processes */
    private int currentRowA = 0;
    private int currentRowB = 0;
    private static int sum = 0;
    private static int done =0;

    /**
     * build function gets parameters to build matrix
     * @param colA
     * @param rowA
     */
    public MatrixManager(int colA, int rowA,int colB, int rowB) {
        /*start matrix with row and column given*/
        this.rowA = rowA;
        this.colA = colA;
        this.matA = new int[colA][rowA];

        this.rowB = rowB;
        this.colB = colB;
        this.matB = new int[colB][rowB];

        /*put random numbers from 1-10 inside each cell in matrix*/
        for (int i = 0; i < colA; i++) {
            for (int j = 0; j < rowA; j++) {
                matA[i][j] = new Random().nextInt(MAX - MIN + 1) + MIN;
            }

        }

        for (int i = 0; i < colB; i++) {
            for (int j = 0; j < rowB; j++) {
                matB[i][j] = new Random().nextInt(MAX - MIN + 1) + MIN;
            }

        }

        /*print matrix contents*/
        System.out.println("matrixA is:");
        for (int i = 0; i < colA; i++) {
            for (int j = 0; j < rowA; j++) {
                System.out.print(matA[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("***********************");
        System.out.println("matrixB is:");
        for (int i = 0; i < colB; i++) {
            for (int j = 0; j < rowB; j++) {
                System.out.print(matB[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * give row for process if not all have been used yet
     * @return row to calculate
     */
    public synchronized int[] allocateRowA() {
        if (currentRowA >= matA.length)
            return null;
        else {
            int[] row = new int[matA[0].length];
            for (int j = 0; j < matA[0].length; j++) {
                row[j] = matA[currentRowA][j];
            }
            currentRowA++;
            return row;
        }
    }

    /**
     * adding sum from each process
     *
     * @param sum - get sum of current calculation from process
     */
    public synchronized void addSum(int sum) {
        System.out.println("\nCurrent multiply sum is: " + sum);
        this.sum += sum;
        done++;
        if (done < colB * rowA) {
            notifyAll();
        }
    }

    /**
     * function to get full final calculated sum
     * @return sum of all matrix multiplied
     */
    public synchronized int getTotal() {
        while (done < colB * rowA) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    /**
     * get row size
     * @return
     */
    public static int getRowA() {
        return rowA;
    }

    public static int getColB() {
        return colB;
    }

    /**
     * function to get a specific element in matrix
     * @param mat
     * @param i
     * @param j
     * @return
     */
    public synchronized int get(char mat, int i, int j) {
        switch (mat) {
            case 'A':
                if (i >= 0 && i < matA.length && j >= 0 && j < matA[0].length) {
                    return matA[i][j];
                }
                break;
            case 'B':
                if (i >= 0 && i < matB.length && j >= 0 && j < matB[0].length) {
                    return matB[i][j];
                }
                break;
            default:
                break;
        }
        return 0;
    }
}
