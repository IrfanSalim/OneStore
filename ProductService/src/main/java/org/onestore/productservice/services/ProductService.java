package org.onestore.productservice.services;

import org.onestore.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(long id);

    List<Product> getAllProducts();

}
