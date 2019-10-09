package Misc;

import Libraries.matrix;

public class matrixTest {
    public static void main(String[] args) {
        matrix I2 = matrix.identityMatrix(2);
        System.out.println(I2);

        Double[][] lSrc = {{0.0, 1.0}, {1.0, 1.0}};
        matrix L = new matrix(lSrc);
        System.out.println(L.toString());

        System.out.println(matrix.squareMatPow(0, L).toString());
        System.out.println(matrix.squareMatPow(1, L).toString());
        System.out.println(matrix.squareMatPow(2, L).toString());
        System.out.println(matrix.squareMatPow(3, L).toString());
        System.out.println(matrix.squareMatPow(4, L).toString());
        System.out.println(matrix.squareMatPow(5, L).toString());
    }
}
