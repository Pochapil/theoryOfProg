package ru.ssau.tk.mggr.theoryofprog.client;

import ru.ssau.tk.mggr.theoryofprog.matrix.Matrices;
import ru.ssau.tk.mggr.theoryofprog.matrix.Matrix;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    //сокет для общения
    private static BufferedInputStream in;
    // поток чтения из сокета
    private static BufferedOutputStream out;

    // поток записи в сокет
    public static void main(String[] args) {
        try {
            try {
// адрес - локальный хост, порт - 4004, такой же как у сервера
                //clientSocket = new Socket("localhost", 4004);
                clientSocket = new Socket("localhost",8080);
// этой строкой мы запрашиваем
//  у сервера доступ на соединение
                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                in = new BufferedInputStream(clientSocket.getInputStream());
                out = new BufferedOutputStream(clientSocket.getOutputStream());

                String firstMatrixName = args[0];
                String secondMatrixName = args[1];
                String resultMatrixName = args[2];
                Matrix first = null;
                Matrix second = null;

                try {
                    first = Matrices.readMatrix(new BufferedInputStream(new FileInputStream(firstMatrixName)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    second = Matrices.readMatrix(new BufferedInputStream(new FileInputStream(secondMatrixName)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Первая матрица");
                System.out.println(first);
                System.out.println("Вторая матрица");
                System.out.println(second);
                Matrices.writeMatrix(out, first);
                Matrices.writeMatrix(out, second);
                out.flush();
                System.out.println("Ждем ответа сервера...");
                try {
                    Matrix result = Matrices.readMatrix(in);
                    System.out.println("результат:");
                    System.out.println(result);
                    Matrices.writeMatrix(new BufferedOutputStream(new FileOutputStream(resultMatrixName)), result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
// в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
