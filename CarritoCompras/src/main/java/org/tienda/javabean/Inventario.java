package org.tienda.javabean;

import org.tienda.excepciones.ProductoNoEncontradoException;
import org.tienda.excepciones.StockInsuficienteException;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Integer, Producto> productos = new HashMap<>();

    public void agregarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
    }

    public Producto obtenerProducto(int id) throws ProductoNoEncontradoException {
        if (!productos.containsKey(id)) {
            throw new ProductoNoEncontradoException("Producto con id:" + id + " no encontrado en el inventario.");
        }
        return productos.get(id);
    }
    public void reducirStock(int id, int cantidad) throws StockInsuficienteException, ProductoNoEncontradoException {
        Producto producto = obtenerProducto(id);
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stockinsuficiente para producto: " + producto.getNombre());
        }
        producto.reducirStock(cantidad);
    }

    public void listarProductos(){
        productos.values().forEach(System.out::println);
    }
}
