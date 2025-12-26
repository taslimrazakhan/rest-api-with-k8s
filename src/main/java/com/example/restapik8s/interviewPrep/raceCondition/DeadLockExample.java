package com.example.restapik8s.interviewPrep.raceCondition;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> {
            System.out.println("Lock1 acquired by " + Thread.currentThread().getName());
            lock1.lock();
            try {
                Thread.sleep(5000);
                System.out.println("Lock1 Acquired for 5 seconds by " + Thread.currentThread().getName());
                lock2.lock();
                System.out.println("Lock2 acquired by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (lock2.isHeldByCurrentThread())
                    lock2.unlock();
                if (lock1.isHeldByCurrentThread())
                    lock1.unlock();
            }
        };

        Runnable task2 = () -> {
            lock2.lock();
            System.out.println("Lock2 acquired by " + Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
                lock1.lock();
                System.out.println("Lock1 acquired by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (lock1.isHeldByCurrentThread())
                    lock1.unlock();
                if (lock2.isHeldByCurrentThread())
                    lock2.unlock();
            }
        };
        Thread t1 = new Thread(task1, "Thread-1");
        Thread t2 = new Thread(task2, "Thread-2");
        t1.start();
        t2.start();

        monitorDeadlock(t1, t2);
    }

    private static void monitorDeadlock(Thread... threads) throws InterruptedException {
        System.out.println("Detecting Deadlock");
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            boolean ALL_ALIVE = true;
            System.out.println("Waiting for 10 sec to Detect Deadlock");
            for (Thread t : threads) {
                if (!t.isAlive())
                    ALL_ALIVE = false;
                break;
            }
            if (ALL_ALIVE) {
                System.out.println("Deadlock Detected!");
                System.exit(1);
            }

        }, "DETECT_DEADLOCK").start();;
    }
}
