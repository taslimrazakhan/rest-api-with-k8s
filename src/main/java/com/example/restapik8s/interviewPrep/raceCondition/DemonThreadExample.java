package com.example.restapik8s.interviewPrep.raceCondition;

public class DemonThreadExample {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +" thread started");
        
        Thread thread= new Thread(() -> {
            try {
                new DemonThreadExample().startWork();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        },"Custom-Deamon-Thread");
        thread.setDaemon(true);
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread().getName() +" thread ended");
    }
    public void startWork() throws InterruptedException{
        System.out.println("Doing work for 5 sec!");
        Thread.sleep(5000);
        System.out.println("Work finished!");

    }
}

