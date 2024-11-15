package com.hust.seller.image_product;

import com.hust.seller.entity.ImageProduct;
import com.hust.seller.repository.ImageProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProductService {
    @Autowired
    ImageProductRepository imageProductRepository ;
    String getFirstImageByProductID(int productID) {
        List<ImageProduct> imageProductList = imageProductRepository.findImageWithProductID(productID) ;
        return imageProductList.get(0).getImage() ;
    }
}
