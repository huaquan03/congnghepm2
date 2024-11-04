package com.hust.seller.product;

import com.hust.seller.entity.ImageProduct;
import com.hust.seller.security.ImageProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import static com.hust.seller.security.ImageProductRepository.*;
@Service
public class ProductService {
    ImageProductRepository imageProductRepository;

    public ProductService(ImageProductRepository imageProductRepository) {
        this.imageProductRepository = imageProductRepository;
    }
    @Transactional
    public void deleteAllProductImages(int productId) {
        imageProductRepository.deleteByProductId(productId);
    }
    public void saveProductImage(int productId, String image) {
        // Tạo đối tượng ProductImage
        ImageProduct imageProduct = new ImageProduct();
      imageProduct.setProductID(productId);  // Liên kết với ID sản phẩm
       imageProduct.setImage(image);    // Lưu đường dẫn của ảnh

        // Lưu ProductImage vào database
        imageProductRepository.save(imageProduct);
    }

}
