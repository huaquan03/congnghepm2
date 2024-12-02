package com.hust.seller.product;

import com.hust.seller.entity.ImageProduct;
import com.hust.seller.entity.Product;
import com.hust.seller.repository.ImageProductRepository;
import com.hust.seller.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ImageProductRepository imageProductRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ImageProductRepository imageProductRepository) {
        this.imageProductRepository = imageProductRepository;
    }
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public List<Product> searchProduct(String keyword) {
        return this.productRepository.searchProduct(keyword);
    }
}
