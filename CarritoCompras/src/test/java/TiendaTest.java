package org.tienda.javabean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tienda.excepciones.ProductoNoEncontradoException;
import org.tienda.excepciones.StockInsuficienteException;

import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {
    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(100.0); // Saldo inicial de $100
        tienda.agregarProductoInicial(new Producto(1, "Pan", 30, 1.55));
        tienda.agregarProductoInicial(new Producto(2, "Leche", 40, 5));
        tienda.agregarProductoInicial(new Producto(3, "Huevos", 10, 1.99));
        tienda.agregarProductoInicial(new Producto(4, "Atun", 20, 10));
    }

    @Test
    void agregarProductoAlCarrito_conStockSuficiente() {
        assertDoesNotThrow(() -> tienda.agregarProductoAlCarrito(1, 5));
    }

    @Test
    void agregarProductoAlCarrito_sinStockSuficiente() {
        StockInsuficienteException exception = assertThrows(
                StockInsuficienteException.class,
                () -> tienda.agregarProductoAlCarrito(4, 200)
        );
        assertEquals("Stock insuficiente para el producto: Atun. Stock disponible: 20", exception.getMessage());
    }

    @Test
    void agregarProductoAlCarrito_productoNoExistente() {
        ProductoNoEncontradoException exception = assertThrows(
                ProductoNoEncontradoException.class,
                () -> tienda.agregarProductoAlCarrito(99, 1)
        );
        assertEquals("Producto con ID 99 no encontrado.", exception.getMessage());
    }

    @Test
    void procesarCompra_conCarritoNoVacio() {
        assertDoesNotThrow(() -> {
            tienda.agregarProductoAlCarrito(1, 2); // Añadir producto al carrito
            tienda.procesarCompra();
        });
    }

    @Test
    void procesarCompra_conCarritoVacio() {
        Exception exception = assertThrows(
                org.tienda.excepciones.CarritoVacioException.class,
                () -> tienda.procesarCompra()
        );
        assertEquals("El carrito está vacío.", exception.getMessage());
    }
}
