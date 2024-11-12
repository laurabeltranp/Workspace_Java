package org.example;

public class Contador{
    private int cuenta=0;

    public synchronized void incrementar(){
        cuenta++;
    }
    public int getCuenta(){
        return cuenta;
    }
}
