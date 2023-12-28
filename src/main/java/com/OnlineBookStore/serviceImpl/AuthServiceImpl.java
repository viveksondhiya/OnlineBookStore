//package com.OnlineBookStore.serviceImpl;
//
//import com.OnlineBookStore.Service.AuthService;
//import com.OnlineBookStore.entity.User;
//import com.OnlineBookStore.payload.AuthenticationResponse;
//import com.OnlineBookStore.repository.UserRepo;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//    @Autowired
//    private UserRepo userRepo;
//
//    public AuthenticationResponse authenticateUser(String email, String password) {
//        User user = userRepo.findByEmail(email);
//
//        if (user != null && user.getPassword().equals(password)) {
//            return new AuthenticationResponse(true, "User exists");
//        } else {
//            return new AuthenticationResponse(false, "User not found or incorrect password");
//        }
//    }
//}
package com.OnlineBookStore.serviceImpl;

import com.OnlineBookStore.Service.AuthService;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.payload.AuthenticationResponse;
import com.OnlineBookStore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public AuthenticationResponse authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            Long userId = user.getId();
            return new AuthenticationResponse(true, "User exists", userId);
        } else {
            return new AuthenticationResponse(false, "User not found or incorrect password");
        }
    }
    private Long getUserIdByEmail(String email) {
        // Your logic to retrieve user ID by email
        long uid=userRepo.findByEmail(email).getId();
        return uid;  // Replace with your actual logic
    }




    //password change
}

