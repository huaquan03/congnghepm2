package com.hust.seller.repository;

import com.hust.seller.entity.Shop;
import com.hust.seller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findBySellerID(int sellerID);

    Optional<Shop> findByShopID(int shopID);

    Optional<Shop> searchFirstByName(String name);

}
