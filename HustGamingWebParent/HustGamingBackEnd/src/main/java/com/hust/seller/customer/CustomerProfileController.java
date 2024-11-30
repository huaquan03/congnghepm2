package com.hust.seller.customer;

import com.hust.seller.customer.CustomerService;
import com.hust.seller.entity.User;
import com.hust.seller.repository.UserRepository;
import com.hust.seller.security.CustomUserDetailsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class CustomerProfileController {
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomerService customerService;

    public CustomerProfileController(UserRepository userRepository, CustomerService customerService) {
        this.userRepository = userRepository;
        this.customUserDetailsService = new CustomUserDetailsService(userRepository);
        this.customerService = customerService; // Tiêm phụ thuộc CustomerService
    }

    @GetMapping("")
    public String getProfileCustomer(Model model) {
        User user = customUserDetailsService.getCurrentUser();
        List<String> addressDetail = user.getAddressDetail();
        model.addAttribute("address", addressDetail);
        model.addAttribute("user", user);  // Add user to the model
        return "customer/profile";  // Return the view name for the profile page
    }

    @PostMapping("/save-image")
    @Transactional
    public String saveImageCustomer(@ModelAttribute("user") User user, @RequestParam("avatar") MultipartFile image) {
        // Kiểm tra nếu có tệp ảnh được chọn
        if (image != null && !image.isEmpty()) {
            // Lấy đường dẫn gốc và thư mục lưu trữ ảnh
            String rootDir = System.getProperty("user.dir");
            String uploadDirPath = rootDir + "/static/images/users/" + user.getUserID() + "/";

            // Tạo thư mục nếu chưa tồn tại
            File dir = new File(uploadDirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Xóa ảnh cũ nếu có
            if (dir.isDirectory() && dir.listFiles() != null) {
                for (File file : dir.listFiles()) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }

            // Lưu ảnh mới
            String filePath = uploadDirPath + image.getOriginalFilename();
            try {
                // Lưu tệp ảnh vào thư mục
                image.transferTo(new File(filePath));
                // Cập nhật thông tin ảnh cho người dùng
                customerService.updateImageByUserID(user.getUserID(), "images/users/" + user.getUserID() + "/" + image.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                return "error"; // Xử lý lỗi nếu có
            }
        }
        return "redirect:/profile";  // Chuyển hướng về trang profile sau khi lưu ảnh
    }
}
