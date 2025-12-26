package com.example.restapik8s.interviewPrep.singleton;

import java.io.Serializable;

public class Singleton implements Cloneable, Serializable {

    private volatile static Singleton INSTANCE;

    private Singleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("Call getInstance");
        }
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null)
                    INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not possible");
    }

}
