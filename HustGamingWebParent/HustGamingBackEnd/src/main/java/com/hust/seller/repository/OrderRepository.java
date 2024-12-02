package com.hust.seller.repository;

import com.hust.seller.entity.ImageProduct;
import com.hust.seller.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Integer> {

}
