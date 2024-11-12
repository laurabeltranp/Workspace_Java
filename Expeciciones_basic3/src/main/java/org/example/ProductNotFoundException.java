package org.example;

public class ProductNotFoundException extends Exception {
    private int productId;
    public ProductNotFoundException(int productId) {
        super("Id de producto no encontrado: " + productId);
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

}
