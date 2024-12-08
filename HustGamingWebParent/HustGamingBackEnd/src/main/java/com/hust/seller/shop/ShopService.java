package com.hust.seller.shop;

import com.hust.seller.entity.Product;
import com.hust.seller.entity.Shop;
import com.hust.seller.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public List<Product> getAllProductByShopID(int id) {
        return shopRepository.findAllProductByShopId(id) ;
    }
    public Shop getShopByShopId(int id) {
        return shopRepository.findShopByShopId(id) ;
    }
    public String getImageByShopId(int id){
        return shopRepository.findImageByShopId(id);
    }
}
