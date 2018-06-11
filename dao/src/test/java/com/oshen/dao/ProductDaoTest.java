package com.oshen.dao;

import com.oshen.dao.model.Product;
import com.oshen.dao.repository.ProductDao;
import io.dropwizard.testing.junit.DAOTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {
//    @Rule
//    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
//            .addEntityClass(Product.class)
//            .build();
//
//    private ProductDao productDao;
//
//    @Before
//    public void setUp() throws Exception {
//        productDao = new ProductDao(daoTestRule.getSessionFactory());
//    }
//
//    @Test
//    public void createPerson() {
//        final Product jet = daoTestRule.inTransaction(() -> productDao.create(new Product("Jet")));
//        assertThat(jet.getId()).isGreaterThan(0);
////        assertThat(jet.getProductName()).isEqualTo("Jet");
//    }
}
