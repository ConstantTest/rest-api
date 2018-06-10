package com.oshen.service.impl;

import com.oshen.dao.model.Product;
import com.oshen.dao.repository.ProductDao;
import com.oshen.service.ProductService;
import com.oshen.service.converters.ProductConverter;
import com.oshen.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductConverter converter;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductConverter converter) {
        this.productDao = productDao;
        this.converter = converter;
    }

    @Transactional
    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        for (Product p: products) {
            productDtos.add(converter.toDto(p));
        }
        return productDtos;
    }

    public void create(ProductDto productDto) {
        Product product = converter.toEntity(productDto);
        productDao.create(product);
    }

    public void delete(long id) {
        productDao.delete(id);
    }

    public ProductDto findById(long id) {
        ProductDto dto = converter.toDto(findSafely(id));
        return dto;
    }

    private Product findSafely(long productId) {
        return productDao.findById(productId).orElseThrow(() -> new NotFoundException("No such product"));
    }
}
