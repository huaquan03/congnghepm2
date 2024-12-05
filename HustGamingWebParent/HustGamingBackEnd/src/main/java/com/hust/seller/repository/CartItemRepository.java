package com.hust.seller.repository;

import com.hust.seller.entity.Cart;
import com.hust.seller.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository  extends JpaRepository<CartItems, Integer> {
    List<CartItems> findByCartID(int cartID);
    Optional<CartItems> findByProductID(int productID);
}
