package org.example.Interfaces;

import org.example.Model.Book;

public interface BookRepository {
    void save(Book book);

    void update(Book book);

    void delete(String id);

    Book get(String id);

    Iterable<Book> getAll();
}
