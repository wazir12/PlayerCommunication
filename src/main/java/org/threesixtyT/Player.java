package org.threesixtyT;

import java.lang.management.ManagementFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Runnable{
    private static final int MAX_MESSAGES = 10;
    private final String name;
    private final BlockingQueue<String> incomingMessages = new LinkedBlockingQueue<>();
    private final AtomicInteger messageCounter = new AtomicInteger(0);
    private Player otherPlayer;

    public Player(String name) {
        this.name = name;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void sendMessage(String message) {
        System.out.println(this.name +" sends : "+message);
        otherPlayer.receiveMessage(message);
    }

    public void receiveMessage(String message) {
        incomingMessages.add(message);
    }

    @Override
    public void run() {
        while (messageCounter.get() < MAX_MESSAGES) {
            try {
                String message = incomingMessages.take();
                String reply = message + " " + messageCounter.incrementAndGet();
                if (messageCounter.get() <= MAX_MESSAGES) {
                    sendMessage(reply);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " finished communication.");
    }

    public int getMessageCounter() {
        return messageCounter.get();
    }



}
