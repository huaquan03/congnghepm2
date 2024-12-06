package com.hust.seller.category;

import com.hust.seller.entity.Product;
import com.hust.seller.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository ;
    public List<Product> getProductByCategoryName(String name) {
        return categoryRepository.findProductByCategoryName(name);
    }
}
