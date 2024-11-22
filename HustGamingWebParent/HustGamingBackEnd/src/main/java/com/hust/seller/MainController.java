package com.hust.seller;

import com.hust.seller.entity.Category;
import com.hust.seller.entity.Product;
import com.hust.seller.entity.User;
import com.hust.seller.product.ProductDTO;
import com.hust.seller.repository.CategoryRepository;
import com.hust.seller.repository.ProductRepository;
import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
    public class MainController {
    private CustomUserDetailsService customUserDetailsService;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;


    public MainController(CustomUserDetailsService customUserDetailsService,CategoryRepository categoryRepository,ProductRepository productRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
    }

    @GetMapping("")
    public String Viewindex(Model model){
        User user=customUserDetailsService.getCurrentUser();
        List<Category> categories=categoryRepository.findAll();
        List<Product> products=productRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("categories",categories);
        List<Product> finalProduct = new ArrayList<>();
        for(Product product:products){
            if(product.isStatus()==true) finalProduct.add(product);
        }
        model.addAttribute("products",finalProduct);
        return "index";
    }
    @GetMapping("/login")
    public String viewLoginPage() {
            return "login";
    }




}