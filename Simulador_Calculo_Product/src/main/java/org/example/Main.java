package org.example;

import java.util.concurrent.CompletableFuture;

class Producto {
        private double precioBase;
        public Producto(double precioBase){
            this.precioBase = precioBase;
        }
        public double getPrecioBase(){
            return precioBase;
        }


    }
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Producto producto = new Producto(20.0);
        CompletableFuture <Double> descuento = CompletableFuture.supplyAsync(()->{
            return producto.getPrecioBase() * 0.90;
        });
        CompletableFuture <Double> costEnvio = CompletableFuture.supplyAsync(()->{
            return 5.00;
        });
        CompletableFuture <Double> impuesto = descuento.thenCombine(costEnvio, (precioConDescuento, envio)->{
            double precioConEnvio = precioConDescuento + envio;
            return precioConEnvio * 1.12;
        });
        impuesto.thenAccept(precioFinal ->{
            System.out.printf("El precio final del producto es: %.2f%n", precioFinal);
        }).join();

    }
}