package org.tienda.excepciones;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
