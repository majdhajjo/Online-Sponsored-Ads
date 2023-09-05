package org.criteoexam.repository;

import org.criteoexam.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT product_serial_number FROM product WHERE title = :name", nativeQuery = true)
    String getSerialNumber(@Param("name") String productName);
}
