package com.OnlineBookStore.serviceImpl;

import com.OnlineBookStore.Service.UserService;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.exceptions.ResourceNotFoundException;
import com.OnlineBookStore.payload.UserDto;
import com.OnlineBookStore.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        User user=this.dtoToUser(userDto);
//        User saveUser=this.userRepo.save(user);
//        return this.userToDto(saveUser);
//    }

//    @Override
//    public String createUser(UserDto userDto) {
//        if (emailExists(userDto.getEmail())) {
//            return "User with this email already exists";
//        }
//
//        User user = dtoToUser(userDto);
//        User savedUser = userRepo.save(user);
//        return "User Created Successfully";
//    }
@Override
public String createUser(UserDto userDto) {
    if (emailExists(userDto.getEmail())) {
        return "User with this email already exists";
    }

    User user = dtoToUser(userDto);
    User savedUser = userRepo.save(user);

    // Assuming your User entity has a method getId() to retrieve the user ID
    Long userId = savedUser.getId();

    return String.format("User created successfully with ID: %d", userId);
}
    private boolean emailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
       // user.setPassword(userDto.getPassword());
        User updatedUser=this.userRepo.save(user);
        UserDto userDtoNew=this.userToDto(updatedUser);
        UserDto ruserDto=new UserDto();
        ruserDto.setEmail(userDtoNew.getEmail());
        ruserDto.setName(userDtoNew.getName());
        return ruserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>userList=this.userRepo.findAll();
        List<UserDto> userDtos=userList.stream()
                .map(user->this.userToDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }
    private User dtoToUser(UserDto userDto){
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }
    private UserDto userToDto(User user){
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

}