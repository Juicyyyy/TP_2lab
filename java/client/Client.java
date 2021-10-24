package client;

import com.google.gson.Gson;
import DataTransferObject.Structure;
import CheckingValues.CheckingValues;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket client;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                Gson parser = new Gson();
                client = new Socket("localhost", 4004);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                int time = 0;
                String message = CheckingValues.read(in);
                Structure fun = parser.fromJson(message, Structure.class);
                System.out.println("Началась игра: 'Который логический час?'");

                while (true){
                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");

                    fun.message = CheckingValues.readStringFromConsole();
                    CheckingValues.write(out, parser.toJson(fun));

                    message = CheckingValues.read(in);
                    fun = parser.fromJson(message, Structure.class);
                    time++;
                    System.out.println("Время: " + time + "\n" + "Ответ.Максимальное время: " + fun.number + "\n");
                }
            } finally {
                client.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
