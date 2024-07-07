package org.onestore.productservice.services;

import org.onestore.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getSingleProduct(long id) {
        // call fake store api to get the product with the given id
        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
