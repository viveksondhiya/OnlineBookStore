package com.OnlineBookStore.Service;

import com.OnlineBookStore.payload.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse authenticateUser(String email, String password);
}
