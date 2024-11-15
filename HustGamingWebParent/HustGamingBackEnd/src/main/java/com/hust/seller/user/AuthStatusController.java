package com.hust.seller.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthStatusController {

    @GetMapping("/check-login-status")
    public Map<String, Object> checkLoginStatus(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        // Kiểm tra nếu người dùng đã đăng nhập
        if (authentication != null && authentication.isAuthenticated()) {
            response.put("isLoggedIn", true);
            response.put("username", authentication.getName());  // Trả về tên người dùng nếu cần
        } else {
            response.put("isLoggedIn", false);
        }

        return response;
    }
}

