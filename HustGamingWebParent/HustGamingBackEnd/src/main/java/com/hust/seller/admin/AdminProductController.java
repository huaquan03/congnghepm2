package com.hust.seller.admin;

import com.hust.seller.entity.Product;
import com.hust.seller.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private ProductRepository productRepository;
    public AdminProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("")
    public String showProduct(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "admin/product";
    }
//    @GetMapping("edit/{id}")
//    public showEditProduct(@ModelAttribute("product")){
//
//
//
//    }


}
