package com.example.restapik8s.interviewPrep.producerConsumerProblem;

import java.util.concurrent.Executors;

public class ProducerConsumerMain {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Thread producerThread = new Thread(new Producer(buffer));

        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
        
    }
}
