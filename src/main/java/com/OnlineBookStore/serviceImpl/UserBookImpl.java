package com.OnlineBookStore.serviceImpl;

import com.OnlineBookStore.Service.UserBooks;
import com.OnlineBookStore.Service.UserService;
import com.OnlineBookStore.entity.Book;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.exceptions.ResourceNotFoundException;
import com.OnlineBookStore.payload.BookDto;
import com.OnlineBookStore.payload.UserDto;
import com.OnlineBookStore.repository.BookRepo;
import com.OnlineBookStore.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserBookImpl implements UserBooks {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void assignBookToUser(long userId, long bookId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book","bookId",bookId));

        user.getUserBooks().add(book);
        book.getBookUsers().add(user);

        userRepo.save(user);
        bookRepo.save(book);

    }
}