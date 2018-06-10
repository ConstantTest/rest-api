package com.oshen.controller.resources;

import com.oshen.dao.model.Product;
import com.oshen.service.ProductService;
import com.oshen.service.dto.ProductDto;
import io.dropwizard.hibernate.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Controller
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    // Retrieve
    @GET
    @UnitOfWork
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public ProductDto getProductDto(@PathParam("id") Long id) {
        return findSafely(id);
    }

    // Create/update
    @POST
    @UnitOfWork
    public void createProductDto(ProductDto dto) {
        productService.create(dto);
    }

    private ProductDto findSafely(long productId) {
        return productService.findById(productId);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        productService.delete(id);
    }
}
