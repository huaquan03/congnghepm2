package com.hust.seller.image_product;

import com.hust.seller.entity.User;
import com.hust.seller.image_product.ImageProductService;
import com.hust.seller.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
public class ImageProductController {

    @Value("${app.upload.dir:${user.home}/product-images}")
    private String uploadDir;

    @Autowired
    ImageProductService imageProductService;

    @GetMapping("/{productID}")
    public ResponseEntity<Resource> getImage(@PathVariable int productID) {
        try {
            // Lấy tên file ảnh kèm productID từ service
            String imageFileName = imageProductService.getFirstImageByProductID(productID);

            // Kiểm tra nếu tên file ảnh null hoặc không hợp lệ
            if (imageFileName == null || imageFileName.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Xây dựng đường dẫn dựa trên productID và tên file ảnh
            Path imagePath = Paths.get(uploadDir, imageFileName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            // Kiểm tra file có tồn tại và có thể đọc được không
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Xác định loại file
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            // Log để debug
            System.out.println("Loading image: " + imagePath);
            System.out.println("Content Type: " + contentType);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
