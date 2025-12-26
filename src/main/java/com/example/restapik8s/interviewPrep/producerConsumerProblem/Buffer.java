package com.example.restapik8s.interviewPrep.producerConsumerProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Buffer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int i) throws InterruptedException {

        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(i);
        System.out.println("Produced =" + i);
        notifyAll();
    }

    public synchronized int consumer() throws InterruptedException {
        while (buffer.size() == 0) {
            wait();
        }
        int item = buffer.poll();
        System.out.println("Consumed =" + item);
        return item;
    }

}
