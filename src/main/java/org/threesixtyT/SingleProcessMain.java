package org.threesixtyT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public class SingleProcessMain {
    private static final Logger logger = LoggerFactory.getLogger(SingleProcessMain.class);

    public static void main(String[] args) {

        // Get the JVM's process ID
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        Thread t1 = new Thread(player1);
        Thread t2 = new Thread(player2);

        t1.start();
        t2.start();

        player1.sendMessage("Start");

        // Register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                t1.interrupt();
                t2.interrupt();
                t1.join();
                t2.join();
                logger.info("Players shut down gracefully");
            } catch (InterruptedException e) {
                logger.error("Error during shutdown", e);
            }
        }));
    }
}
