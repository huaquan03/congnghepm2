package com.hust.seller.category;

import com.hust.seller.entity.User;
import com.hust.seller.repository.UserRepository;
import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.ui.Model;
import com.hust.seller.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories/{name}")
public class CategoryController {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    CategoryService categoryService ;
    @GetMapping("")
    public String getProductsByCategory(@PathVariable("name") String name, Model model) {
        User user = new CustomUserDetailsService(userRepository).getCurrentUser() ;
        List<Product> productList = categoryService.getProductByCategoryName(name) ;
        model.addAttribute("user",user) ;
        if(productList.isEmpty()){
            return "not-found" ;
        }
        else {
            model.addAttribute("productList",productList) ;
            return "category" ;
        }
    }
}
