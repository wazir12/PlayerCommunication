package org.threesixtyT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.Socket;

public class ClientPlayer {



    private String name;

    public String getName() {
        return name;
    }

    public ClientPlayer(String name){
        this.name=name;
    }
    public void startClient() {
        try {
            // Read the server port from the file
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("Client [" + name + "] is running with PID: " + pid);

            // Connect to the server
            try (Socket socket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String message = "Start";
                int messageCounter = 0;
                while (messageCounter < 10) {
                    out.println(message); // Send message to server
                    String response = in.readLine(); // Read response from server
                    System.out.println("Client received: " + response);
                    message = response; // Update message for next iteration
                    messageCounter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ClientPlayer clientPlayer = new ClientPlayer("player 1");
        System.out.println("Starting: " + clientPlayer.getName());
        clientPlayer.startClient();
    }
}
