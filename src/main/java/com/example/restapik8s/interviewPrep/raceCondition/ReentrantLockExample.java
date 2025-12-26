package com.example.restapik8s.interviewPrep.raceCondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.scheduling.config.Task;

public class ReentrantLockExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable taskWithoutTimeout = () -> {
            lock.lock();

            System.out.println(Thread.currentThread().getName() + " acquired lock and holding for 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        };
        Runnable taskWithTimeout = () -> {

            try {
                if (lock.tryLock(3, TimeUnit.SECONDS))
                    try {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " acquired lock Succefully and waiting till timeout");
                    } finally {
                        lock.unlock();
                    }
                else
                    System.out.println(Thread.currentThread().getName() + " unable to acquire lock due to timeout");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        };

        new Thread(taskWithoutTimeout,"Blocking-Thread").start();

        Thread.sleep(1000);

        new Thread(taskWithTimeout,"TimeOut-Thread").start();
    }
}
