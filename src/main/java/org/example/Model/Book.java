package org.example.Model;

import java.time.LocalDate;

public class Book {
    public String Id;
    public String Title;
    public String Author;
    public int ReleaseYear;
    public LocalDate CreatedAt;

    public Book() {
        this.CreatedAt = LocalDate.now();
    }
}
