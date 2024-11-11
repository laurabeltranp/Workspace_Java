package org.tienda.javabean;
import org.tienda.excepciones.StockInsuficienteException;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precio;

    public Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
    public void reducirStock(int cantidad){
        this.stock -= cantidad;
    }
}
