package me.aj.productservice.controller;


import handlers.ResponseHandler;
import lombok.RequiredArgsConstructor;
import me.aj.productservice.dto.ProductRequest;
import me.aj.productservice.dto.ProductRequestList;
import me.aj.productservice.dto.ProductResponse;
import me.aj.productservice.dto.Response;
import me.aj.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    // Add
    @PostMapping
    public ResponseEntity<Response> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        productService.createProduct(productRequest);

        return ResponseHandler.generateResponse("Product created successfully.", HttpStatus.CREATED, productRequest);

    }

    // Add
    @PostMapping("/multiple")
    public ResponseEntity<Response> createProducts(@Valid @RequestBody ProductRequestList productRequestList) {

        productService.createProducts(productRequestList.getProducts());

        return ResponseHandler.generateResponse("Product(s) created successfully.", HttpStatus.CREATED, productRequestList);

    }

    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseHandler.generateResponse("Fetched all products.", HttpStatus.OK, products);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.getProductById(id);

        return ResponseHandler.generateResponse("Fetched one product.", HttpStatus.OK, productResponse);
    }
}
