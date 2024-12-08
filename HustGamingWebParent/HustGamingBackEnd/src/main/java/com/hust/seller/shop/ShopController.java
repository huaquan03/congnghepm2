package com.hust.seller.shop;

import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.hust.seller.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/shop")
@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private  CustomUserDetailsService customUserDetailsService;
    @GetMapping("/{id}")
    public String getShop(@PathVariable("id") int id, Model model) {
        List<Product> productList = shopService.getAllProductByShopID(id) ;
        model.addAttribute("shop",shopService.getShopByShopId(id)) ;
        model.addAttribute("user", customUserDetailsService.getCurrentUser()) ;
        model.addAttribute("Image",shopService.getImageByShopId(id));
        model.addAttribute("productCount",productList.size()) ;
        model.addAttribute("productList",productList) ;
        return "shop" ;
    }
}
