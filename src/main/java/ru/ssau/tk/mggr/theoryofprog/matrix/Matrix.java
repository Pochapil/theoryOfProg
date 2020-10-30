package ru.ssau.tk.mggr.theoryofprog.matrix;

import java.io.Serializable;

public class Matrix implements Serializable {
    private static final long serialVersionUID = -6673400259686974036L;
    private double[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix() {
        rows = 0;
        columns = 0;
        matrix = new double[rows][columns];
    }

    public Matrix(double[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    double getAt(int n, int m) {
        return matrix[n][m];
    }

    void setAt(int n, int m, double value) {
        matrix[n][m] = value;
    }

    @Override
    public String toString() {
        StringBuilder neededString = new StringBuilder();
        neededString.append(this.getRows()).append(" ").append(this.getColumns()).append("\n");
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                neededString.append(this.getAt(i, j)).append(" ");
            }
            neededString.append("\n");
        }
        return neededString.toString();
    }

}