package org.tienda.javabean;

import org.tienda.excepciones.CarritoVacioException;
import org.tienda.excepciones.ProductoNoEncontradoException;
import org.tienda.excepciones.StockInsuficienteException;
import org.tienda.excepciones.SaldoInsuficienteException;
import java.util.HashMap;
import java.util.Map;

public class CarritoDeCompras {
    private Map<Producto, Integer> items = new HashMap<>();


    public void agregarProducto(Producto producto, int cantidad) {
        items.put(producto, items.getOrDefault(producto, 0) + cantidad);
    }

    public void procesarCompra(Inventario inventario, double billetera) throws CarritoVacioException, StockInsuficienteException, ProductoNoEncontradoException {
        if (items.isEmpty()) {
            throw new CarritoVacioException("El carrito está vacio.");
        }
        double totalCompra = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            totalCompra += producto.getPrecio() * cantidad;
            inventario.reducirStock(producto.getId(), cantidad);
        }
        if (billetera < totalCompra) {
            throw new IllegalArgumentException("Saldo insuficiente");

        }
        billetera -= totalCompra;
        items.clear();
        System.out.println("Compra exitosa. Dinero restante: " + billetera);
    }

    public double calcularTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrecio() * entry.getValue())
                .sum();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }
}
