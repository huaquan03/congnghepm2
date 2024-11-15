package com.hust.seller.cart;

import com.hust.seller.dto.ProductCartDTO;
import com.hust.seller.entity.Cart;
import com.hust.seller.entity.CartItems;
import com.hust.seller.entity.Product;
import com.hust.seller.cart.CartService;
import com.hust.seller.customer.CustomerService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("customer/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String getAllCartItems(Model model) {
        int customerID = customerService.getCurrentCustomerID();
        Cart cart = cartService.getCartWithCustomerID(customerID);

        if (cart != null) {
            int cartID = cart.getCartID();
            List<CartItems> cartItemsList = cartService.getAllCartItemsWithCartID(cartID);
            List<ProductCartDTO> showListCartItems = new ArrayList<>();
            for (CartItems cartItem : cartItemsList) {
                ProductCartDTO product = cartService.getProductWithProductID(cartItem.getProductID());
                showListCartItems.add(product);
            }
            model.addAttribute("cartItems", showListCartItems);
            return "customer/cart";
        } else {
            return "customer/cart-not-found";
        }
    }
}
