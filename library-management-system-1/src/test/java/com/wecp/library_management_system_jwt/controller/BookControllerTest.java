import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wecp.library_management_system_jwt.controller.BookController;
import com.wecp.library_management_system_jwt.entity.Book;
import com.wecp.library_management_system_jwt.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setDescription("Test Description");
        book.setAvailability(true);

        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Book\",\"author\":\"Test Author\",\"description\":\"Test Description\",\"availability\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Book");
        book.setAuthor("Updated Author");
        book.setDescription("Updated Description");
        book.setAvailability(true);

        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\",\"author\":\"Updated Author\",\"description\":\"Updated Description\",\"availability\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        book1.setDescription("Description One");
        book1.setAvailability(true);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");
        book2.setDescription("Description Two");
        book2.setAvailability(false);

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[1].title").value("Book Two"));
    }
}