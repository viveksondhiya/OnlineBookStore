package com.OnlineBookStore.payload;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {
    private String email;
    @Size(min=8,max=20 ,message = "Password must be min of 8 characters and max 20 characters")
    private String newPassword;
}
