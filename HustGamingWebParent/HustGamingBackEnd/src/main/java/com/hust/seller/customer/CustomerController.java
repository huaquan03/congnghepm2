package com.hust.seller.customer;

import com.hust.seller.entity.Cart;
import com.hust.seller.entity.CartItems;
import com.hust.seller.entity.User;
import com.hust.seller.repository.CartItemRepository;
import com.hust.seller.repository.CartRepository;
import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
}
