package com.hust.seller;

import com.hust.seller.entity.*;
import com.hust.seller.product.ProductDTO;
import com.hust.seller.repository.CategoryRepository;
import com.hust.seller.repository.ImageProductRepository;
import com.hust.seller.repository.ProductRepository;
import com.hust.seller.repository.ShopRepository;
import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
    public class MainController {
    private CustomUserDetailsService customUserDetailsService;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ImageProductRepository imageProductRepository;
    private ShopRepository shopRepository;


    public MainController(CustomUserDetailsService customUserDetailsService,CategoryRepository categoryRepository,ProductRepository productRepository,ImageProductRepository imageProductRepository,ShopRepository shopRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
        this.imageProductRepository=imageProductRepository;
        this.shopRepository=shopRepository;
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
    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        User user = customUserDetailsService.getCurrentUser();
        model.addAttribute("user", user);
        List<ImageProduct> imageProductList = imageProductRepository.findByProductID(id);
        model.addAttribute("images", imageProductList);
        Product product = productRepository.findByProductID(id);
        model.addAttribute("product", product);
        Optional<Shop> shop1 = shopRepository.findByShopID(product.getShopID());
        Shop shop = shop1.get();
        model.addAttribute("shop", shop);
        return "product";
    }

}