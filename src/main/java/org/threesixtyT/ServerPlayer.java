package org.threesixtyT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPlayer {
    private String name;

    public String getName() {
        return name;
    }

    public ServerPlayer(String name){
        this.name = name;
    }
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("Server [" + name + "] is running on port: " + 8080 + " with PID: " + pid);

            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            int messageCounter = 0;
            while ((inputLine = in.readLine()) != null && messageCounter < 10) {
                System.out.println("Server received: " + inputLine);
                String response = inputLine + " " + (++messageCounter);
                out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerPlayer serverPlayer = new ServerPlayer("player 2");
        System.out.println("Starting: " + serverPlayer.getName());
        serverPlayer.startServer();
    }
}
