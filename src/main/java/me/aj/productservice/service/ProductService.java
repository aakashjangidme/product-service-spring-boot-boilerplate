package me.aj.productservice.service;


import exceptions.CustomDataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.aj.productservice.dto.ProductRequest;
import me.aj.productservice.dto.ProductResponse;
import me.aj.productservice.models.Product;
import me.aj.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//Autowires the bean
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).category(productRequest.getCategory()).image(productRequest.getImage()).build();
        productRepository.save(product);
        log.info("Saved product : {}", product.getId());
    }

    public void createProducts(List<ProductRequest> productRequest) {

        List<Product> products = productRequest.stream().map(productRequest1 -> Product.builder().name(productRequest1.getName()).description(productRequest1.getDescription()).price(productRequest1.getPrice()).category(productRequest1.getCategory()).image(productRequest1.getImage()).build()).collect(Collectors.toList());

        productRepository.saveAll(products);

        log.info("Saved products");
    }

    public List<ProductResponse> getAllProducts() {
        log.info("getAllProducts()");
        List<Product> products = productRepository.findAll();

//        productRepository.deleteAll();

        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).category(product.getCategory()).image(product.getImage()).build();
    }

    public ProductResponse getProductById(String id) {
        log.info("productId : {}", id);

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            log.info("product : {}", product.get());
            return ProductResponse.builder().id(product.get().getId()).name(product.get().getName()).description(product.get().getDescription()).price(product.get().getPrice()).category(product.get().getCategory()).image(product.get().getImage()).build();
        } else {
            throw new CustomDataNotFoundException("Product not found");
        }

    }
}
