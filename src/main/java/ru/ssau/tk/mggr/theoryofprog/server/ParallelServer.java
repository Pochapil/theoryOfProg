package ru.ssau.tk.mggr.theoryofprog.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ParallelServer {

    public static final int PORT = 8080;
    private static int number = 1;
    public static LinkedList<ParallelServerThread> serverList = new LinkedList<>();

    // список всех нитей
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
// Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                System.out.println("Клиент " + number + " подключен");
                try {
                    serverList.add(new ParallelServerThread(socket, number));
                    number++;
// добавить новое соединенние в список
                } catch (IOException e) {
// Если завершится неудачей, закрывается сокет,
// в противном случае, нить закроет его при завершении работы:
                    System.out.println("Клиент " + number + " завершил работу в связи с ошибкой");
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
