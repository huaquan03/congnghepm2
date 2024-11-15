package com.hust.seller.repository;

import com.hust.seller.dto.ProductCartDTO;
import com.hust.seller.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

    List<CartItems> findAllByCartID(int cartID);

    @Query("SELECT  new com.hust.seller.dto.ProductCartDTO(p.productID, p.productName, p.description, p.price, c.quantityID) " +
            "FROM Product p JOIN CartItems c ON p.productID = c.productID " +
            "WHERE c.productID = :productID " )
    List<ProductCartDTO> findCartItemsByProductID(@Param("productID") int productID);
}
