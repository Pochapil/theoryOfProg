package ru.ssau.tk.mggr.theoryofprog.server;

import ru.ssau.tk.mggr.theoryofprog.matrix.Matrices;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SequentialServer {
    public static final int PORT = 4004;
    private static int number = 1;
    private static BufferedInputStream in;
    private static BufferedOutputStream out;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Клиент " + number + " подключен");
                try {
                    in = new BufferedInputStream(socket.getInputStream());
                    out = new BufferedOutputStream(socket.getOutputStream());
                    Matrices.serverCalculation(in, out);
                    System.out.println("Результат " + number + " отправлен клиенту");
                    number++;
                } catch (IOException e) {
                    System.out.println("Клиент " + number + " завершил работу в связи с ошибкой");
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

}
