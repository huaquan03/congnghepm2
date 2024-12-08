package com.hust.seller;

import com.hust.seller.entity.*;
import com.hust.seller.product.ProductService;
import com.hust.seller.repository.CategoryRepository;
import com.hust.seller.repository.ImageProductRepository;
import com.hust.seller.repository.ProductRepository;
import com.hust.seller.repository.ShopRepository;
import com.hust.seller.security.CustomUserDetailsService;
import com.hust.seller.shop.ShopService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
    public class MainController {
    private final CustomUserDetailsService customUserDetailsService;
    private final CategoryRepository categoryRepository;
    private final ImageProductRepository imageProductRepository;
    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final ShopService shopService;


    public MainController(CustomUserDetailsService customUserDetailsService,CategoryRepository categoryRepository, ImageProductRepository imageProductRepository,ShopRepository shopRepository, ProductService productService, ShopService shopService) {
        this.customUserDetailsService = customUserDetailsService;
        this.categoryRepository=categoryRepository;
        this.imageProductRepository=imageProductRepository;
        this.shopRepository=shopRepository;
        this.productService=productService;
        this.shopService= shopService;
    }

    @GetMapping("")
    public String viewIndex(Model model, @Param("keyword") String keyword) {
        User user=customUserDetailsService.getCurrentUser();
        List<Category> categories=categoryRepository.findAll();
        List<Product> products=this.productService.findAll();
        model.addAttribute("user",user);
        model.addAttribute("categories",categories);
        List<Product> finalProduct = new ArrayList<>();
        for(Product product:products){
            if(product.isStatus()) finalProduct.add(product);
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
        Product product = this.productService.findByProductID(id);
        model.addAttribute("product", product);
        Optional<Shop> shop1 = shopRepository.findByShopID(product.getShopID());
        Shop shop = shop1.get();
        model.addAttribute("Image",shopService.getImageByShopId(product.getShopID()));
        model.addAttribute("shop", shop);
        return "product";
    }

    @GetMapping("/search")
    public String searchProduct(Model model, @Param("keyword") String keyword) {
        List<Product> products = this.productService.findAll();
        Optional<Shop> shop1;
        if (keyword != null && !keyword.isEmpty()) {
            products = this.productService.searchProduct(keyword);
            model.addAttribute("keyword", keyword);

            shop1 = shopRepository.searchFirstByName(keyword);
            model.addAttribute("shop", shop1.orElse(null));
        }

        model.addAttribute("products", products);
        return "search";
    }

}