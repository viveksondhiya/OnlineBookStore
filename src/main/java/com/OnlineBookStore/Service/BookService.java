package com.OnlineBookStore.Service;

import com.OnlineBookStore.payload.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    BookDto getBookById(Long id);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto bookDto, Long id);

    void deleteBook(Long id);

  //  List<BookDto> getBookByUserId(Long userId);


    List<BookDto> searchBooksByBookName(String bookName);
}
