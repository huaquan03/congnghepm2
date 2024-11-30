package com.hust.seller.customer;

<<<<<<< Updated upstream
import com.hust.seller.entity.User;
import com.hust.seller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import com.hust.seller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
<<<<<<< Updated upstream
    private UserRepository userRepository;

    public Integer getCurrentCustomerID() {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUserID();
        }
        return null;
    }

    public User getCurrentUser() {
        String username = getCurrentUsername();
        if (username != null) {
            return userRepository.findByUsername(username).orElse(null);
        }
        return null;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            return authentication.getName();
        }
        return null;
=======
    private UserRepository userRepository ;
    public void updateImageByUserID(int id,String image) {
        userRepository.updateImageByUserID(id,image);
>>>>>>> Stashed changes
    }
}
