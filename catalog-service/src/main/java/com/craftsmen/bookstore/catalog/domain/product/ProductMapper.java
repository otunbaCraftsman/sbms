package com.craftsmen.bookstore.catalog.domain.product;

import com.craftsmen.bookstore.catalog.web.dto.ProductDTO;

class ProductMapper {

    static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice());
    }
}
