package com.OnlineBookStore.Service;

import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.payload.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    UserDto updateUser(UserDto userDto, Long uId);

    void deleteUser(Long userId);
}
