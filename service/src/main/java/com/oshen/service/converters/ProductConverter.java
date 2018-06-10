package com.oshen.service.converters;

import com.oshen.dao.model.Product;
import com.oshen.service.dto.ProductDto;

public interface ProductConverter {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
