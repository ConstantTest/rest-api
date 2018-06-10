package com.oshen.service;

import com.oshen.dao.model.Product;
import com.oshen.service.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findAll();
    void create(ProductDto productDto);
    void delete(long id);
    ProductDto findById(long id);
}
