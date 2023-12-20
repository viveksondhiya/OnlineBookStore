package com.OnlineBookStore.serviceImpl;

import com.OnlineBookStore.Service.BookService;
import com.OnlineBookStore.entity.Book;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.exceptions.ResourceNotFoundException;
import com.OnlineBookStore.payload.BookDto;
import com.OnlineBookStore.repository.BookRepo;
import com.OnlineBookStore.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book=this.dtoToBook(bookDto);
        Book createdBook=this.bookRepo.save(book);
        return bookToDto(createdBook);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book=this.bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book","bookId",id));
        BookDto bookDto=this.bookToDto(book);
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> bookList=this.bookRepo.findAll();
        List<BookDto> bookDtos=bookList.stream()
                .map(book->this.bookToDto(book))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long book_Id) {
        Book book=this.bookRepo.findById(book_Id)
                .orElseThrow(()->new ResourceNotFoundException("Book","bookId",book_Id));
        book.setBookName(bookDto.getBookName());
        book.setBookLanguage(bookDto.getBookLanguage());
        book.setAuthorName(bookDto.getAuthorName());
        book.setDescription(bookDto.getDescription());
        Book updatedBook=this.bookRepo.save(book);
        BookDto updatedDto=this.bookToDto(updatedBook);
        return this.modelMapper.map(updatedDto,BookDto.class );
    }
        @Override
    public void deleteBook(Long id) {
        Book book=this.bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book","book",id));
        this.bookRepo.delete(book);
    }

//    @Override
//    public List<BookDto> getBookByUserId(Long userId) {
//        User user=this.userRepo.findById(userId)
//                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
//        List<Book> books=this.bookRepo.findByUser(user);
//        List<BookDto> bookDto=books.stream()
//                .map(book -> this.modelMapper.map(book,BookDto.class)).collect(Collectors.toList());
//        return bookDto;
//    }

    @Override
    public List<BookDto> searchBooksByBookName(String bookName) {
        List<Book> bookList = this.bookRepo.searchBooksByBookName(bookName);
        List<BookDto> bookDto = bookList.stream()
                .map(book -> this.bookToDto(book))
                .collect(Collectors.toList());
        return bookDto;
    }


    private BookDto bookToDto(Book book){
        BookDto bookDto=this.modelMapper.map(book, BookDto.class);
        return bookDto;
    }
    private Book dtoToBook(BookDto bookDto){
        Book book=this.modelMapper.map(bookDto,Book.class);
        return book;
    }
}