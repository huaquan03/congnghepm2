package com.hust.seller.admin;

import com.hust.seller.entity.Role;
import com.hust.seller.entity.User;
import com.hust.seller.security.CustomUserDetailsService;
import com.hust.seller.user.UserDTO;
import com.hust.seller.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private UserService userService;
    private CustomUserDetailsService customUserDetailsService;

    public AdminController(AdminService adminService, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.adminService = adminService;
        this.customUserDetailsService=customUserDetailsService;
        this.userService=userService;
    }
    public String getfullname(HttpServletRequest httpServletRequest){
        String username=customUserDetailsService.getCurrentUser(httpServletRequest);
        Optional<User> user=userService.findByUsername(username);
        User user1=user.get();
        String name=user1.getFullName();
        return name;
    }
    @GetMapping("/")
    public String adminHome(Model model,HttpServletRequest httpServletRequest) {
        AdminController adminController=new AdminController(adminService,customUserDetailsService,userService);
        String fullname=adminController.getfullname(httpServletRequest);
        model.addAttribute("nameuser",fullname);
        return "admin/home";
    }
    @GetMapping("/profile")
    public String showProfile(Model model, HttpServletRequest httpServletRequest){
        List<User> admins=adminService.findUserByRole("ROLE_ADMIN");
        AdminController adminController=new AdminController(adminService,customUserDetailsService,userService);
        String fullname=adminController.getfullname(httpServletRequest);
        model.addAttribute("admin",admins);
        model.addAttribute("nameuser",fullname);
        return "admin/profile";
    }
    @GetMapping("/sellers")
    public String showUsers() {
        return "admin/seller";
    }





}
