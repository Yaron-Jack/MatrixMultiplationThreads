/**
 * Tester class
 */
public class Main {


    public static void main(String[] args) {
        /*define amount of processes*/
        int amountProcesses = 5;

        /*define matrix sizes to test*/
        int colA = 4;
        int rowA = 3;
        int colB = rowA;
        int rowB = 4;

        /*intialize matrixes*/
        MatrixManager matA = new MatrixManager(colA,rowA,colB,rowB);

        /*initialize the processes*/
        MatrixWorker[] matrixWorkers = new MatrixWorker[amountProcesses];
        for (int i = 0; i < amountProcesses; i++) {
            matrixWorkers[i] = new  MatrixWorker(matA);
        }
        for (int i = amountProcesses-1; i >= 0 ; i--) {
            matrixWorkers[i].start();
        }

        /*final sum*/
        System.out.println("\nTotal is: " + matA.getTotal());
    }

}
