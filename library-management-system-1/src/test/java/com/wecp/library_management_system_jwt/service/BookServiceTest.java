import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.wecp.library_management_system_jwt.entity.Book;
import com.wecp.library_management_system_jwt.repository.BookRepository;
import com.wecp.library_management_system_jwt.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setDescription("Test Description");
        book.setAvailability(true);
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book createdBook = bookService.createBook(book);
        assertEquals(book.getTitle(), createdBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> foundBook = bookService.getBookById(1L);
        assertTrue(foundBook.isPresent());
        assertEquals(book.getTitle(), foundBook.get().getTitle());
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        book.setTitle("Updated Title");
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book updatedBook = bookService.updateBook(1L, book);
        assertEquals("Updated Title", updatedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}