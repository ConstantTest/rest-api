package com.oshen.dao.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(
                name = "com.alex.rest.core.Product.findAll",
                query = "SELECT p FROM Product p"
        ),
        @NamedQuery(
                name = "com.alex.rest.core.Product.delete",
                query = "delete FROM Product p WHERE p.id = :id"
        )
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productName", nullable = false)
    private String productName;

    public Product() {}

    public Product(String productName) {
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName);
    }
}
