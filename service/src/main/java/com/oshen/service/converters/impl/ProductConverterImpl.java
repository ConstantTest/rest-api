package com.oshen.service.converters.impl;

import com.oshen.dao.model.Product;
import com.oshen.service.converters.ProductConverter;
import com.oshen.service.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverterImpl implements ProductConverter {

    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getProductName());
    }

    public ProductDto toDto(Product entity) {
        return new ProductDto(entity.getProductName());
    }
}
