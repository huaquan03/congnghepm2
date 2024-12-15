package com.hust.seller;

import com.hust.seller.entity.*;
import com.hust.seller.product.ProductService;
import com.hust.seller.repository.*;
import com.hust.seller.security.CustomUserDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class MainController {
    private final CustomUserDetailsService customUserDetailsService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ImageProductRepository imageProductRepository;
    private final ShopRepository shopRepository;
    private final ReviewRepository reviewRepository;
    private final ProductService productService;


    public MainController(CustomUserDetailsService customUserDetailsService, CategoryRepository categoryRepository, ProductRepository productRepository, ImageProductRepository imageProductRepository, ShopRepository shopRepository, ReviewRepository reviewRepository, ProductService productService) {
        this.customUserDetailsService = customUserDetailsService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.imageProductRepository = imageProductRepository;
        this.shopRepository = shopRepository;
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @GetMapping("")
    public String viewIndex(Model model, @Param("keyword") String keyword) {
        User user = customUserDetailsService.getCurrentUser();
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = this.productService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        List<Product> finalProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.isStatus()) finalProduct.add(product);
        }
        model.addAttribute("products", finalProduct);
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
        model.addAttribute("shop", shop);
        List<Review> reviews = reviewRepository.findByProductID(id);
        model.addAttribute("reviews", reviews);
        float averageRating = 0;
        for (Review review : reviews) {
            averageRating = review.getRating();
        }
        if (reviews.isEmpty()) {
            averageRating = 5;
        } else {
            averageRating = averageRating / reviews.size();
        }
        model.addAttribute("averageRating", averageRating);

        return "product";
    }

    @GetMapping("categories/{id}")
    public String viewProductwithCategory(@PathVariable("id") int id, Model model) {
        User user = customUserDetailsService.getCurrentUser();
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = productRepository.findByCategoryID(id);
        Category categoryname = categoryRepository.findByCategoryId(id);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        List<Product> finalProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.isStatus()) finalProduct.add(product);
        }
        model.addAttribute("products", finalProduct);
        model.addAttribute("categoryname", categoryname.getCategoryName());
        boolean none = finalProduct.isEmpty();
        model.addAttribute("none", none);
        return "index";
    }

    @GetMapping("/search")
    public String searchProduct(Model model,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                @RequestParam(name = "page", defaultValue = "1") Integer page) {

        Page<Product> products = this.productService.getAllProducts(page);
        if (keyword != null && !keyword.isEmpty()) {
            products = this.productService.searchAndSortProducts(keyword, sortBy, minPrice, maxPrice, page);
            model.addAttribute("keyword", keyword);
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("sortBy", sortBy);
        }

        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        return "search";
    }
}