package com.hust.seller.repository;

import com.hust.seller.entity.Product;
import com.hust.seller.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByShopID(int shopID);
    Product findByProductID(int productID);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    List<Product> searchProduct(String keyword);
}
