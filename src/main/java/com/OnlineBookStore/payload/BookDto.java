package com.OnlineBookStore.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long bookId;
    private String bookName;
    private String bookLanguage;
    private Long rating;
    private String authorName;
    private String description;
}
