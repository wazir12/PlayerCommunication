package org.threesixtyT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.Socket;

/**
 * Represents the client-side player in the communication system.
 * The client player connects to the server player and handles communication.
 */
public class ClientPlayer {

    private static final Logger logger = LoggerFactory.getLogger(ClientPlayer.class);

    private String name;

    public String getName() {
        return name;
    }

    public ClientPlayer(String name){
        this.name=name;
    }
    public void startClient() {
        try {

            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            logger.info("Client [{}] is running with PID: {}", name, pid);

            // Connect to the server
            try (Socket socket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String message = "Start";
                int messageCounter = 0;
                while (messageCounter < 10) {
                    out.println(message); // Send message to server
                    String response = in.readLine(); // Read response from server
                    logger.info("Client received: {}", response);
                    message = response; // Update message for next iteration
                    messageCounter++;
                }
            }
        } catch (Exception e) {
            logger.error("Error in Client [{}]", name, e);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ClientPlayer clientPlayer = new ClientPlayer("player 1");
        clientPlayer.startClient();
    }
}
