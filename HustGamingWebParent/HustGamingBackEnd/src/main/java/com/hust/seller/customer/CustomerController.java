package com.hust.seller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/")
    public String customerHome(Model model) {
        // Bạn có thể thêm dữ liệu vào model nếu cần thiết
        model.addAttribute("message", "Welcome to the Customer Home Page!");
        return "customer/home"; // Trả về template customer/home.html
    }

}
