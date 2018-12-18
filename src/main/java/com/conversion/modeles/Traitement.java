package com.conversion.modeles;

public class Traitement {

    public synchronized void waitin(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void notification(){
        notify();
    }
}
