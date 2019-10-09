package Libraries;

public class matrix {
    private Double[][] data;

    public static matrix identityMatrix(int sideLength) {
        matrix toReturn = new matrix(sideLength);

        for (int i = 0; i < sideLength; i++)
            toReturn.setDatum(i, i, 1.0);

        return toReturn;
    }

    public static matrix squareMatPow(int n, matrix a) {
        //Check your inputs
        if (a == null) {
            return null;
        }
        if (!a.isSquare()) {
            return null;
        }

        //Base cases
        if (n == 0) {
            return identityMatrix(a.getCols());
        }
        else if (n == 1) {
            return a.copy();
        }
        //Even Case
        else if (n % 2 == 0) {
            return squareMatPow(n / 2, a).multiply(squareMatPow(n / 2, a));
        }
        //Odd Case
        else {
            return a.multiply(squareMatPow(n - 1, a));
        }
    }

    public matrix(Double[][] data) {
        this.data = data;
    }

    public matrix(int rows, int cols, Double defaultValue) {
        assert (rows > 0);
        assert (cols > 0);

        this.data = new Double[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                data[r][c] = defaultValue;
            }
        }
    }

    public matrix(int rows, int cols) {
        this(rows, cols, 0.0);
    }

    public matrix(int sideLength) {
        this(sideLength, sideLength);
    }

    public void setDatum(int row, int col, Double val) {
        if ((row >= data.length) || (col >= data[0].length))
            return;

        data[row][col] = val;
    }

    public Double getDatum(int row, int col) {
        if ((row >= data.length) || (col >= data[0].length))
            return null;

        return data[row][col];
    }

    public int getRows() {
        return data.length;
    }

    public int getCols() {
        return data[0].length;
    }

    public matrix multiply(matrix other) {
        if (this.getCols() != other.getRows())
            return null;

        matrix toReturn = new matrix(this.getRows(), other.getCols());
        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < other.getCols(); col++) {
                double acc = 0;
                for (int k = 0; k < this.getCols(); k++) {
                    acc += this.getDatum(row, k) * other.getDatum(k, col);
                }
                toReturn.setDatum(row, col, acc);
            }
        }

        return toReturn;
    }

    public matrix copy() {
        matrix copy = new matrix(data.length, data[0].length);

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                copy.setDatum(row, col, data[row][col]);
            }
        }

        return copy;
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Double[] row : data) {
            toReturn.append("|\t");
            for (Double val : row) {
                toReturn.append("\t").append(val).append("\t");
            }
            toReturn.append("\t|\n");
        }

        return toReturn.toString();
    }

    public boolean isSquare() {
        return data.length == data[0].length;
    }
}
