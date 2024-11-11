package org.tienda.javabean;

import org.tienda.excepciones.*;

import java.util.Scanner;

public class Tienda {
    private Inventario inventario;
    private CarritoDeCompras carrito;
    private double billetera;

    public Tienda(double billeteraInicial) {
        this.inventario = new Inventario();
        this.carrito = new CarritoDeCompras();
        this.billetera = billeteraInicial;
    }

    public void listarProductos(){
        inventario.listarProductos();
    }
    public void agregarProductoAlCarrito(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Ingrese el ID del producto: ");
            int id = scanner.nextInt();
            System.out.println("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            Producto producto = inventario.obtenerProducto(id);
            carrito.agregarProducto(producto, cantidad);
            System.out.println("Prodcuto añadido al carrito");
        } catch (ProductoNoEncontradoException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void procesarCompra() {
        try {
            carrito.procesarCompra(inventario, billetera);
        } catch (CarritoVacioException | StockInsuficienteException | ProductoNoEncontradoException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    public void agregarProductoInicial(Producto producto){
        inventario.agregarProducto(producto);
    }
}
