package com.OnlineBookStore.repository;


import com.OnlineBookStore.entity.Book;
import com.OnlineBookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
 //   List<Book> findByUser(User user);
    @Query("SELECT b FROM Book b WHERE " +
            "b.bookName LIKE %:query%")
    List<Book> searchBooksByBookName(@Param("query") String query);

}