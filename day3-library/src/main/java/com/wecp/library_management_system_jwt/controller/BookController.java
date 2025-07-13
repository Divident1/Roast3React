package com.wecp.library_management_system_jwt.controller;

import com.wecp.library_management_system_jwt.entity.Book;
import com.wecp.library_management_system_jwt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId) {
        bookService.borrowBook(bookId, bookId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully");
    }
}
