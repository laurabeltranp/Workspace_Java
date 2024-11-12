package org.example;

public class ProductDao {
    public Product getProduct(int id) throws DatabaseException {
        if (id < 0) {
            throw new DatabaseException("Error en la base de dtos buscando por producto id: " + id);

        }
        return id ==1 ? new Product(1, "Cocacola") : null;
    }
    public Product buscarProducto (int id) throws ProductNotFoundException {
        try {
            Product product = getProduct(id);
            if (product == null) {
                throw new ProductNotFoundException(id);
            }
            return product;
        } catch (DatabaseException e){
            System.out.println("Excepcion de la bbdd: " + e.getMessage());
            throw new ProductNotFoundException(id);
        }
    }
}
