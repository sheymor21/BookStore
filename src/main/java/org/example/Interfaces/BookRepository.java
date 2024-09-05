package org.example.Interfaces;

import org.example.Model.Book;

public interface BookRepository {
    void save(Book user);

    void update(Book user);

    void delete(String id);

    Book get(String id);

    Book getByTitle(String title);

    Iterable<Book> getAll();
}
