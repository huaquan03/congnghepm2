package com.hust.seller.repository;

import com.hust.seller.entity.Product;
import com.hust.seller.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByShopID(int shopID);
    Product findByProductID(int productID);

}
