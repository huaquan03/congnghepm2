package com.hust.seller.admin;

import com.hust.seller.entity.Category;
import com.hust.seller.entity.ImageProduct;
import com.hust.seller.entity.Product;
import com.hust.seller.product.ProductService;
import com.hust.seller.repository.CartRepository;
import com.hust.seller.repository.CategoryRepository;
import com.hust.seller.repository.ImageProductRepository;
import com.hust.seller.repository.ProductRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductRepository productRepository;
    private final ImageProductRepository imageProductRepository;
    private final CategoryRepository categoryRepository;


    public AdminProductController(ProductRepository productRepository, ImageProductRepository imageProductRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.imageProductRepository = imageProductRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String showProduct(Model model, @Param("keyword") String keyword) {
        List<Product> products = this.productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable("id") int id, Model model) {
        Product product = this.productRepository.findByProductID(id);
        List<ImageProduct> productImages = imageProductRepository.findByProductID(id);
        model.addAttribute("productImages", productImages);
        model.addAttribute("product", product);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/editproduct";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, @ModelAttribute("product") Product product) {
        product.setCategoryID(productRepository.findByProductID(id).getCategoryID());
        productRepository.save(product);
        return "admin/success";
    }


}
