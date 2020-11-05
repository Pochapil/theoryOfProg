package ru.ssau.tk.mggr.theoryofprog.server;

import ru.ssau.tk.mggr.theoryofprog.matrix.Matrices;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ParallelServerThread extends Thread {

    private Socket socket;
    private int number;
    private BufferedInputStream in;
    private BufferedOutputStream out;

    public ParallelServerThread(Socket socket, int number) throws IOException {
        this.socket = socket;
        this.number = number;
        in = new BufferedInputStream(socket.getInputStream());
        out = new BufferedOutputStream(socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        try {
            Matrices.serverCalculation(in, out);
            System.out.println("Результат " + number + " отправлен клиенту");
        } catch (IOException e) {
            System.out.println("Клиент " + number + " завершил работу в связи с ошибкой");
        }
    }
}

