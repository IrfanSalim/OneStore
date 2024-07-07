package org.onestore.productservice.services;

import org.onestore.productservice.dtos.FakeStoreProductDto;
import org.onestore.productservice.models.Category;
import org.onestore.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long id) {
        // call fake store api to get the product with the given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        // covert fake store product dto to product
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        // call fake store api to get all products
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        // convert fake store product dtos to products
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
