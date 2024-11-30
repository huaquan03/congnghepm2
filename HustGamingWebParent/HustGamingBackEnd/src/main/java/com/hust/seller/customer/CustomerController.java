package com.hust.seller.customer;

<<<<<<< Updated upstream
import com.hust.seller.entity.Product;
import com.hust.seller.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ProductService productService;
    @GetMapping("/")
    public String customerHome(Model model) {
        // Bạn có thể thêm dữ liệu vào model nếu cần thiết
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        model.addAttribute("message", "Welcome to the Customer Home Page!");
        return "customer/home"; // Trả về template customer/home.html
    }
<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes
}
