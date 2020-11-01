package ru.ssau.tk.mggr.theoryofprog.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.mggr.theoryofprog.exceptions.MatricesAreIncompatible;

import java.io.*;

import static org.testng.Assert.*;

public class MatricesTest {

    @Test
    public void testWriteMatrix() {
        Matrix first = new Matrix(new double[][]{{2.0, 3.0}, {3.0, 4.0}});
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("firstMatrix.bin"))) {
            Matrices.writeMatrix(out, first);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadMatrix() {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("firstMatrix.bin"))) {
            Matrix first = Matrices.readMatrix(in);
            System.out.println(first);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteTwoMatrix() {
        Matrix first = new Matrix(new double[][]{{2.0, 3.0}, {3.0, 4.0}, {10.0, 11.0}});
        Matrix second = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("twoMatrix.bin"))) {
            Matrices.writeMatrix(out, first);
            Matrices.writeMatrix(out, second);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("twoMatrix.bin"))) {
            Matrix third = Matrices.readMatrix(in);
            Matrix fourth = Matrices.readMatrix(in);
            System.out.println(third);
            System.out.println(fourth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiplicationMatrix() {
        Matrix first = new Matrix(new double[][]{{2.0, 3.0}, {3.0, 4.0}, {10.0, 11.0}});
        Matrix second = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});
        Matrix third = Matrices.multiplicationMatrix(first, second);
        System.out.println(third);

        Assert.assertThrows(MatricesAreIncompatible.class, () -> {
            Matrix a = new Matrix(new double[][]{{2.0, 3.0, 4.0}, {3.0, 4.0, 4.0}, {10.0, 11.0, 4.0}});
            Matrix b = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});
            Matrix c = Matrices.multiplicationMatrix(a, b);
        });
    }
}