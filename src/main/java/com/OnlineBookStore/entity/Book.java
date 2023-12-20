package com.OnlineBookStore.entity;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Book_table")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    @Column(name = "book_name",nullable = false,length = 50)
    private String bookName;
    private String bookLanguage;
    private Long rating;
    private String authorName;
    private String description;

    @ManyToMany(mappedBy = "userBooks")
    private List<User> bookUsers = new ArrayList<>();

}