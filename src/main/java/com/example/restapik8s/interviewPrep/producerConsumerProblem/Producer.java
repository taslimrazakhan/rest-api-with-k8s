package com.example.restapik8s.interviewPrep.producerConsumerProblem;

public class Producer implements Runnable {

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for (int i = 0; i <= 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
