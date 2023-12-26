package com.OnlineBookStore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private boolean isAuthenticated;
    private String message;
    private Long userId;
    public AuthenticationResponse(boolean isAuthenticated, String message) {
        this.isAuthenticated = isAuthenticated;
        this.message = message;
    }
}
