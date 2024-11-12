package org.example;

public class ProductController {
    private ProductDao productDao;
    public ProductController (){
        this.productDao = new ProductDao();

    }
    public void getProductDetails(int id){
        try{
            Product product = productDao.buscarProducto(id);
            System.out.println("Producto encontrado: " + product.getName());

        } catch (ProductNotFoundException e){
            System.out.println("Producto no encontrado: " + e.getMessage());
        }
    }
}
