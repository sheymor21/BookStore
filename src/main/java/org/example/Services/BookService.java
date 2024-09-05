package org.example.Services;

import org.example.Interfaces.BookRepository;
import org.example.Model.Book;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    public void deleteBook(String id) {
        bookRepository.delete(id);
    }

    public Book getBook(String id) {
        return bookRepository.get(id);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.getAll();
    }
}
