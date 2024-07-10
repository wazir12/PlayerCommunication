package org.threesixtyT;

import java.lang.management.ManagementFactory;

public class SingleProcessMain {
    public static void main(String[] args) {

        // Get the JVM's process ID
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        String pid = jvmName.split("@")[0];

        System.out.println("Current JVM Process ID: " + pid);
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        Thread thread1=new Thread(player1);
        thread1.start();
        Thread thread2=new Thread(player2);
        thread2.start();

        System.out.println("Player 1 (threadID) :" + thread1.getId());
        System.out.println("Player 2 (threadID) :" + thread2.getId());


        player1.sendMessage("Start");
    }
}
