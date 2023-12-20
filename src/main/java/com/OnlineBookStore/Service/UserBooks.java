package com.OnlineBookStore.Service;

import com.OnlineBookStore.payload.BookDto;
import com.OnlineBookStore.payload.UserDto;

public interface UserBooks {
    void assignBookToUser(long userId,long book_Id);

}
