package com.OnlineBookStore.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotEmpty
    @Size(min = 3,message = "Username must be min of 3 characters")
    private String name;
    @NotEmpty
    @Email(message = "Email address is not valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    @NotEmpty
    @Size(min=8,max=20 ,message = "Password must be min of 8 c characters and max 20 characters")
    private String password;
}
