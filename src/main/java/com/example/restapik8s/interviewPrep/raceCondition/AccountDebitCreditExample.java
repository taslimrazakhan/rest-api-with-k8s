package com.example.restapik8s.interviewPrep.raceCondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDebitCreditExample {
    public static void main(String[] args) {
        Account from = new Account("SBI0001", 2000);
        Account to = new Account("HDFC0002", 500);

        System.out.println("Amount transferred =" + from.transfer(to, 500));
        System.out.println("Amount in Account " + from.getAccountId() + " is= " + from.getBalance()
                + " Amount in Account " + to.getAccountId() + " is " + to.getBalance());
    }

}

class Account {
    private final String accountId;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String accountId, double initialAmount) {
        this.accountId = accountId;
        this.balance = initialAmount;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public boolean transfer(Account to, double amount) {

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("Lock aquired by Account " + accountId + " it self");

                if (to.lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("Lock aquired on Account " + to.accountId + " by Account " + this.accountId
                            + " for transfer of Payment");
                    if (this.balance >= amount) {
                        this.balance -= amount;
                        to.balance += amount;
                    }
                    return true;
                }
            } else {
                System.out.println("Lock not aquired by Account " + accountId + " it self");
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

    public double getBalance() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("Lock aquired by account " + this.accountId);
                return balance;
            } else {
                System.out.println("Lock not aquired by account " + accountId);
                return 0;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        }

    }
}