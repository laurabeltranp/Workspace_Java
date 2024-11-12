package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Thread[] hilos = new Thread[5];
        for(int i = 0; i<5; i++){
            hilos[i] = new Thread(() ->{
                for (int j = 0; j <100; j++){
                    contador.incrementar();
                }
            });
            hilos[i].start();
        }
        for (int i = 0; i< 5; i++){
            try{
                hilos [i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("El valor final de la cuenta es: " +contador.getCuenta());
    }

}
