package com.craftsmen.bookstore.catalog.web.controller;

import com.craftsmen.bookstore.catalog.web.utility.PagedResult;
import com.craftsmen.bookstore.catalog.domain.product.ProductService;
import com.craftsmen.bookstore.catalog.web.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDTO> getProducts(@RequestParam(name = "page", defaultValue = "1") int page) {

        return productService.getProducts(page);

    }

}
