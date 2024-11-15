package com.hust.seller.repository;

import com.hust.seller.entity.Category;
import com.hust.seller.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    List<ImageProduct> findByProductID(int productID);
    @Modifying
    @Query("DELETE FROM ImageProduct p WHERE p.productID = :productId")
    void deleteByProductId(@Param("productId") int productId);
    @Query("select ip from ImageProduct ip where  ip.productID = :productID")
    List<ImageProduct> findImageWithProductID(@Param("productID") int productID) ;
}
