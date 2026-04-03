package com.shopwave.repository;
import com.shopwave.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repo;
    
    @Test
    void testSearch() {
        Product p = new Product();
        p.setName("Phone");
        p.setPrice(BigDecimal.valueOf(100)); // ✅ ADD THIS

        repo.save(p);

        List<Product> result = repo.findByNameContainingIgnoreCase("phone");
        assertFalse(result.isEmpty());
    }
}
