package com.craftsmen.bookstore.catalog.domain.product;

import com.craftsmen.bookstore.catalog.ApplicationProperties;
import com.craftsmen.bookstore.catalog.web.dto.ProductDTO;
import com.craftsmen.bookstore.catalog.web.utility.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final ApplicationProperties properties;

    ProductService(ProductRepository repository, ApplicationProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    public PagedResult<ProductDTO> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<ProductDTO> productsPage = repository.findAll(pageable).map(ProductMapper::toProductDTO);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }

    public Optional<ProductDTO> getProductByCode(String code) {
        return repository.findByCode(code).map(ProductMapper::toProductDTO);
    }

}
