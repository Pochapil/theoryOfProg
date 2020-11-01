package ru.ssau.tk.mggr.theoryofprog.matrix;


import ru.ssau.tk.mggr.theoryofprog.exceptions.MatricesAreIncompatible;

import java.io.*;

public class Matrices {

    static Matrix sumMatrix(Matrix first, Matrix second) throws MatricesAreIncompatible {
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
        } else throw new MatricesAreIncompatible();
    }

    static Matrix multiplicationMatrix(Matrix first, Matrix second) throws MatricesAreIncompatible {
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
        } else throw new MatricesAreIncompatible();
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

    public static void serialize(BufferedOutputStream buffStream, Matrix matrix) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(buffStream);
        outputStream.writeObject(matrix);
        outputStream.flush();
    }

    public static Matrix deserialize(BufferedInputStream buffStream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(buffStream);
        return (Matrix) inputStream.readObject();
    }

    public static void writeMatrix(BufferedOutputStream outputStream, Matrix matrix) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(matrix.getRows());
        out.writeInt(matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                out.writeDouble(matrix.getAt(i, j));
            }
        }
        out.flush();
    }

    public static Matrix readMatrix(BufferedInputStream inputStream) throws IOException {
        DataInputStream in = new DataInputStream(inputStream);
        int rows = in.readInt();
        int columns = in.readInt();
        Matrix matrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix.setAt(i, j, in.readDouble());
            }
        }
        return matrix;
    }

    public static void serverCalculation(BufferedInputStream in, BufferedOutputStream out) throws IOException {
        Matrix first = readMatrix(in);
        Matrix second = readMatrix(in);
        Matrix result = multiplicationMatrix(first, second);
        writeMatrix(out, result);
        out.flush();
    }

}
