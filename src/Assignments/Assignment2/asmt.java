package Assignments.Assignment2;
import Libraries.listNode;

public class asmt {
    final static private Integer[][] L = {{0, 1}, {1, 1}};
    final static private Integer[][] I = {{1, 0}, {0, 1}};

    public static void main(String[] args) {
        listNode list = null;

        for(int i = 0; i < 10; i++) {
            System.out.println("fibPow(" + i + "): " + fibPow(i));
        }
    }

    private static Integer fibPow(Integer n) {
        Integer[][] sum = squareMatPow(n, L);

        return sum[1][0];
    }

    private static Integer[][] squareMatPow(int n, Integer[][] a) {
        if (n == 0) {
            return I;
        }
        else if (n == 1) {
            return a;
        }
        else if (n % 2 == 0) {
            return squareMatMult(squareMatPow(n / 2, a), squareMatPow(n / 2, a));
        } else {
            return squareMatMult(a, squareMatPow(n-1, a));
        }
    }

    private static Integer[][] squareMatMult(Integer[][] a, Integer[][] b) {
        int n = a.length;
        Integer[][] c = zeroMat(n, n);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int k = 0; k < n; k++) {
                    c[row][col] += a[row][k] * b[k][col];
                }
            }
        }

        return c;
    }

    private static Integer[][] zeroMat(int rows, int cols) {
        Integer[][] toReturn = new Integer[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                toReturn[row][col] = 0;
        }

        return toReturn;
    }

    static void printMat(Integer[][] a) {
        System.out.print('\n');
        for (Integer[] integers : a) {
            System.out.print("\t|\t");
            for (Integer integer : integers) {
                System.out.print(integer + "\t");
            }
            System.out.println("|");
        }
        System.out.print('\n');
    }
}
