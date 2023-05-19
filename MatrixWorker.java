/**
 * MatrixWorker class creates processes
 * attributes of matrix's
 * has build function to get matrix's
 * multiplies row and column and gives the sum each time
 */
public class MatrixWorker extends Thread {
    /*Matrix attributes*/
    private MatrixManager matrixMult;

    /**
     * build function
     *
     * @param matrixMult
     */
    public MatrixWorker(MatrixManager matrixMult) {
        this.matrixMult = matrixMult;
    }

    /**
     * main function to run the processes
     * calculate the multiplication between row and column of matrix's
     * The nested loops are to cover all the columns and rows properly for the matrix multiplication
     * making sure each cell is calculated the times needed by matrix's rows
     */
    public void run() {
        int[] a = matrixMult.allocateRowA();
        while (a != null) {
            for (int j = 0; j < matrixMult.getColB(); j++) {
                int sum = 0;
                for (int i = 0; i < matrixMult.getRowA(); i++) {
                    sum += a[i] * matrixMult.get('B', i, j);/*for each element in row multiply with the fitting element in column of other matrix */
                }
                matrixMult.addSum(sum);
            }
            a = matrixMult.allocateRowA();
        }
    }
}



