package com.example.restapik8s.interviewPrep.raceCondition;

public class EvenOddPrinter {

    public static void main(String[] args) {
        EvenOddDisplay evenOddDisplay = new EvenOddDisplay(20);
        new Thread(() -> {
            try {
                evenOddDisplay.printEven();
            } catch (InterruptedException e) {
                Thread.currentThread().getName();
            }
        }).start();
        new Thread(() -> {
            try {
                evenOddDisplay.printOdd();
            } catch (InterruptedException e) {
                Thread.currentThread().getName();
            }
        }, "Even Printer").start();
    }
}

class EvenOddDisplay {
    Object lock = new Object();
    private final int maxCount;

    private int counter;

    public EvenOddDisplay(int maxCount) {
        this.maxCount = maxCount;
    }

    public void printEven() throws InterruptedException {
        synchronized (lock) {
            while (counter <= maxCount) {
                if (counter % 2 != 0) {
                    lock.wait();
                }
                System.out.println("Even Number =" + counter++);
                lock.notifyAll();
            }
        }

    }

    public void printOdd() throws InterruptedException {
        synchronized (lock) {
            while (counter <= maxCount) {
                if (counter % 2 == 0)
                    lock.wait();
                System.out.println("Odd Number =" + counter++);
                lock.notifyAll();
            }

        }

    }
}
