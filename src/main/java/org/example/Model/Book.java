package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Book {
    public String Id;
    public String Title;
    public String Author;
    public String Price;
    public int ReleaseYear;
    public LocalDate CreatedAt;

    public Book() {
        this.Id = UUID.randomUUID().toString();
        this.CreatedAt = LocalDate.now();
    }
}
