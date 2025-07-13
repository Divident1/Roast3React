package com.wecp.library_management_system_jwt.controller;

import com.wecp.library_management_system_jwt.entity.Book;
import com.wecp.library_management_system_jwt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/{bookId}/borrow")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId) {
        String message = bookService.borrowBook(bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/{bookId}/return")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        String message = bookService.returnBook(bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}