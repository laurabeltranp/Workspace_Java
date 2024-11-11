package org.tienda;
import org.tienda.javabean.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] agrs){
        Tienda tienda = new Tienda(100.0);
        tienda.agregarProductoInicial(new Producto(1, "Pan", 30, 1.55));
        tienda.agregarProductoInicial(new Producto(2, "Leche", 40, 5));
        tienda.agregarProductoInicial(new Producto(3, "Huevos", 10, 1.99));
        tienda.agregarProductoInicial(new Producto(4, "Atun", 20, 10));

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while(!salir){
            System.out.println("\nSeleccione una opcion: ");
            System.out.println("1. Listar productos");
            System.out.println("2. Añadir productos al carrito");
            System.out.println("3. Procesar compra");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    tienda.listarProductos();
                    break;
                case 2:
                    tienda.agregarProductoAlCarrito();
                    break;
                case 3:
                    tienda.procesarCompra();
                    break;
                case 4:
                    System.out.println("Gracias por usar la tienda");
                    salir = true;
                    break;
                default:
                    System.out.println("Opncion no valida");

            }

        }
        scanner.close();

    }

}