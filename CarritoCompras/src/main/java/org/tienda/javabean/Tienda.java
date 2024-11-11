package org.tienda.javabean;

import org.tienda.excepciones.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    // Método para listar productos en la tienda
    public void listarProductos() {
        inventario.listarProductos();
    }

    // Método para añadir un producto al carrito y registrar la acción en el log
    public void agregarProductoAlCarrito(int id, int cantidad) {
        try {
            Producto producto = inventario.obtenerProducto(id);
            if(producto.getStock()<cantidad){
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre()+ " " + "Stock disponible: " + " " +producto.getStock());

            }
            carrito.agregarProducto(producto, cantidad);
            registrarAccion("AGREGAR", id, cantidad);
            System.out.println("Producto añadido al carrito");
        } catch (ProductoNoEncontradoException e){
            registrarError("Error", "ProductoNoEncontradoException", e.getMessage());
            System.out.println("Error" + e.getMessage());

        }catch (StockInsuficienteException e){
            registrarError("Error", "StockInsuficienteException", e.getMessage());
            System.out.println("Error" + e.getMessage());
        }

    }

    // Método para procesar la compra y registrar la acción en el log
    public void procesarCompra() {
        try {
            carrito.procesarCompra(inventario, billetera);
            registrarAccion("PROCESAR", 0, 0); // id y cantidad no son relevantes aquí
            System.out.println("Compra procesada exitosamente.");
        } catch (CarritoVacioException | StockInsuficienteException | ProductoNoEncontradoException e) {
            registrarError("Error", e.getClass().getSimpleName(), e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para registrar una acción en el archivo cart_management.log
    private void registrarAccion(String accion, int productId, int cantidad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart_management.log", true))) {
            writer.write("[" + accion + "][" + productId + "][" + cantidad + "]");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo cart_management.log: " + e.getMessage());
        }
    }

    // Método para registrar errores en el archivo exceptions.log
    private void registrarError(String error, String exception, String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("exceptions.log", true))) {
            writer.write("[" + error + "][" + exception + "][" + mensaje + "]");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo exceptions.log: " + e.getMessage());
        }
    }

    // Método para agregar productos iniciales al inventario
    public void agregarProductoInicial(Producto producto) {
        inventario.agregarProducto(producto);
    }
}
