package com.hust.seller.cart;

import com.hust.seller.dto.ProductCartDTO;
import com.hust.seller.entity.Cart;
import com.hust.seller.entity.CartItems;
import com.hust.seller.repository.CartItemsRepository;
import com.hust.seller.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;

    public List<CartItems> getAllCartItemsWithCartID(int cartID) {
        return cartItemsRepository.findAllByCartID(cartID);
    }

    public Cart getCartWithCustomerID(int customerID) {
        return cartRepository.findByCustomerID(customerID);
    }

    public ProductCartDTO getProductWithProductID(int productID) {
        List<ProductCartDTO> productCartDTOList = cartItemsRepository.findCartItemsByProductID(productID);
        return productCartDTOList.get(0) ;
    }
}
