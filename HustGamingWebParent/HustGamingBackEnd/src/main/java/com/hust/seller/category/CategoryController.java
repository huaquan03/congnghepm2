package com.hust.seller.category;

import org.springframework.ui.Model;
import com.hust.seller.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/{name}")
public class CategoryController {
    @Autowired
    CategoryService categoryService ;
    public String getProductsByCategory(@PathVariable("name") String name, Model model) {
        List<Product> productList = categoryService.getProductByCategoryName(name) ;
        if(productList ==null) return "not-found" ;
        else {
            model.addAttribute("productList",productList) ;
            return "category" ;
        }
    }
}
