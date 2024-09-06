package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Order {

    public String Id;
    public String UserId;
    public String BookId;
    public LocalDate CreatedAt;

    private User user;
    private Book book;

    public Order() {
        this.Id = UUID.randomUUID().toString();
        this.CreatedAt = LocalDate.now();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return this.user;
    }

    public Book getBook() {
        return this.book;
    }
}
