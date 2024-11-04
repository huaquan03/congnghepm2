package com.hust.seller.admin;

import com.hust.seller.entity.Category;
import com.hust.seller.entity.Product;
import com.hust.seller.security.CategoryRepository;
import com.hust.seller.security.CustomUserDetailsService;
import com.hust.seller.security.UserRepository;
import com.hust.seller.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private AdminService adminService;
    private UserService userService;
    private CustomUserDetailsService customUserDetailsService;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private PasswordEncoder passwordEncoder;

    public AdminCategoryController(AdminService adminService, UserService userService, CustomUserDetailsService customUserDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder,CategoryRepository categoryRepository) {
        this.adminService = adminService;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository=categoryRepository;
    }
    @GetMapping("")
    public String showCategory(Model model){
        List<Category> categories=categoryRepository.findAll();
        model.addAttribute("categories",categories);
        return "admin/category";
    }
    @GetMapping("/create")
    public String createCategory(Model model){
        Category category=new Category();
        model.addAttribute("category",category);
        return "admin/createcategory";
}

    @PostMapping("/create")
    public String createNewCategory(@ModelAttribute("category") Category category){
        Category category1=new Category();
        category1.setCategoryName(category.getCategoryName());
        categoryRepository.save(category1);
        return "admin/success";
    }
    @GetMapping("/edit/{id}")
    public String showEditCategory(@PathVariable("id") int categoryId, Model model){
        Category category=categoryRepository.findByCategoryId(categoryId);
        model.addAttribute("category",category);
        return "admin/editcategory";
    }
    @PostMapping("/edit/{id}")
    public String editCategory(@ModelAttribute("category") Category category,@PathVariable("id") int categoryId ){
        Category category1=categoryRepository.findByCategoryId(categoryId);
        category1.setCategoryName(category.getCategoryName());
       categoryRepository.save(category1);
        return "admin/success";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@ModelAttribute("category") Category category,@PathVariable("id") int categoryId){
        Category category1=categoryRepository.findByCategoryId(categoryId);
        categoryRepository.delete(category1);
        return "admin/success";
    }

}
