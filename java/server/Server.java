package server;

import com.google.gson.Gson;
import DataTransferObject.Structure;
import CheckingValues.CheckingValues;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                Gson parser = new Gson();
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");
                Socket clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    int time = 0;
                    String message;
                    System.out.println("Введите целочисленное время:");
                    int number = CheckingValues.readIntFromConsole();
                    Structure fun = new Structure("Началась игра: 'Который логический час?'", number, true);
                    CheckingValues.write(out, parser.toJson(fun));
                    while (true){
                        message = CheckingValues.read(in);
                        fun = parser.fromJson(message, Structure.class);
                        time++;

                        System.out.println("Время: " + time + "\n" + "Сообщение: " + fun.message + "\n");

                        if (time < fun.number){
                            fun.number = number;
                            CheckingValues.write(out, parser.toJson(fun));
                        }
                        else {
                            fun.number = time;
                            CheckingValues.write(out, parser.toJson(fun));
                        }
                    }
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

