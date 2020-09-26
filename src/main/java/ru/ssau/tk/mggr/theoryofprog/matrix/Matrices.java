package ru.ssau.tk.mggr.theoryofprog.matrix;


public class Matrices {

    static Matrix sumMatrix(Matrix first, Matrix second) {
        int rows = first.getRows();
        int columns = first.getColumns();
        if (rows == second.getRows() && columns == second.getColumns()) {
            Matrix result = new Matrix(rows, columns);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    result.setAt(i, j, first.getAt(i, j) + second.getAt(i, j));
                }
            }
            return result;
        }
        return null;
    }

    static Matrix multiplicationMatrix(Matrix first, Matrix second) {
        int rows = first.getRows();
        int columns = first.getColumns();
        if (columns == second.getRows()) {
            Matrix result = new Matrix(rows, second.getColumns());
            double value = 0;
            for (int i = 0; i < rows; i++) {
                for (int k = 0; k < second.getColumns(); k++) {
                    for (int j = 0; j < columns; j++) {
                        value = value + first.getAt(i, j) * second.getAt(j, k);
                    }
                    result.setAt(i, k, value);
                    value = 0;
                }
            }
            return result;
        }
        return null;
    }

    static Matrix multiplicationMatrixAtNumber(Matrix matrix, double number) {
        Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                result.setAt(i, j, matrix.getAt(i, j) * number);
            }
        }
        return result;
    }
}
