package org.dukazo.service;

import org.dukazo.entity.Product;
import org.dukazo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (product.getOffers() != null) {
            product.getOffers().forEach(offer -> offer.setProduct(product));
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
