package com.OnlineBookStore.controller;

import com.OnlineBookStore.Service.BookService;
import com.OnlineBookStore.payload.BookDto;
import com.OnlineBookStore.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long userId){
        return ResponseEntity.ok(this.bookService.getBookById(userId));
    }

    @PostMapping("/createBook")
    public ResponseEntity<BookDto>createBook(@RequestBody BookDto bookDto){
        BookDto createBook=this.bookService.createBook(bookDto);
        return  new ResponseEntity<>(createBook, HttpStatus.CREATED);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> updateUser(@RequestBody BookDto bookDto,@PathVariable("bookId") Long bId){
        BookDto updatedBook=this.bookService.updateBook(bookDto,bId);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long bookId){
        this.bookService.deleteBook(bookId);
        return new ResponseEntity("Book Deleted Successfully",HttpStatus.OK);
    }
    @GetMapping("/searchBook")
    public List<BookDto> searchBooksByBookName(@RequestParam("query") String query) {

        List<BookDto> foundBooks = bookService.searchBooksByBookName(query);
        return foundBooks;
    }


}
