package org.threesixtyT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents the server-side player in the communication system.
 * The server player listens for incoming connections and handles communication with the client player.
 */
public class ServerPlayer {

    private static final Logger logger = LoggerFactory.getLogger(ServerPlayer.class);

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
            logger.info("Server [{}] is running on port: {} with PID: {}", name, 8080, pid);

            // Register shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serverSocket.close();
                    logger.info("Server [{}] shut down gracefully", name);
                } catch (Exception e) {
                    logger.error("Error during server shutdown", e);
                }
            }));


            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);


        } catch (Exception e) {
            logger.error("Error in Server [{}]", name, e);
            e.printStackTrace();

        }
    }

    private void handleClient(Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;
            int messageCounter = 0;
            while ((inputLine = in.readLine()) != null && messageCounter < 10) {
                logger.info("Server received: {}", inputLine);
                String response = inputLine + " " + (++messageCounter);
                out.println(response);
            }
        } catch (Exception e) {
            logger.error("Error handling client", e);
        }
    }

    public static void main(String[] args) {
        ServerPlayer serverPlayer = new ServerPlayer("player 2");
        serverPlayer.startServer();
    }
}
