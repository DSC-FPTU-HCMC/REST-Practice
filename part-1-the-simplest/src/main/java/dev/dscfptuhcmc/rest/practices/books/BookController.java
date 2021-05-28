package dev.dscfptuhcmc.rest.practices.books;

import dev.dscfptuhcmc.rest.practices.commons.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author quangdatpham
 */
@RestController
public class BookController {

	private final BookRepository repository;

	BookController(BookRepository repository) {

		this.repository = repository;
	}

	@GetMapping("/books")
	public List<Book> findAll() {

		return StreamSupport
				.stream(repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return repository.save(book);
	}

	@GetMapping("/books/{id}")
	public Book findBookById(@PathVariable long id) {

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PutMapping("/books/{id}")
	public Book updateBook(@RequestBody Book bookResource, @PathVariable long id) {

		return repository.findById(id)
				.map(book -> {
					book.setName(bookResource.getName());
					book.setAuthor(bookResource.getAuthor());
					book.setDescription(bookResource.getDescription());
					return repository.save(book);
				})
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable long id) {

		repository.deleteById(id);
	}

	@GetMapping("/shelves/{id}/books")
	public List<Book> findBooksOnAShelf(@PathVariable long id) {

		return repository.findByShelfId(id);
	}

	@GetMapping("/books/detailed")
	public List<BookWithShelf> findAllDetailedBooks() {

		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(BookWithShelf::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/books/{id}/detailed")
	public BookWithShelf findDetailedBookById(@PathVariable Long id) {

		return repository.findById(id)
				.map(BookWithShelf::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
