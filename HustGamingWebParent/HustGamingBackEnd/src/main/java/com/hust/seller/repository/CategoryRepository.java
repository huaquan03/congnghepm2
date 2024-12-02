package com.hust.seller.repository;

import com.hust.seller.entity.Category;
import com.hust.seller.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Category findByCategoryId(int categoryId);

    @Query("SELECT p FROM Product p JOIN Category c ON p.categoryID = c.categoryId WHERE  c.categoryName = :name")
    List<Product> findProductByCategoryName(@Param("name") String name);

}
