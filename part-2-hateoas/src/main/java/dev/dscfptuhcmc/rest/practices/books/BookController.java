package dev.dscfptuhcmc.rest.practices.books;

import dev.dscfptuhcmc.rest.practices.commons.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author quangdatpham
 */
@RestController
public class BookController {

	private final BookRepository repository;
	private final BookRepresentationModelAssembler assembler;
	private final BookWithShelfResourceAssembler bookWithShelfResourceAssembler;

	BookController(BookRepository repository, BookRepresentationModelAssembler assembler,
				   BookWithShelfResourceAssembler bookWithShelfResourceAssembler) {

		this.repository = repository;
		this.assembler = assembler;
		this.bookWithShelfResourceAssembler = bookWithShelfResourceAssembler;
	}

	@GetMapping("/books")
	public ResponseEntity<CollectionModel<EntityModel<Book>>> findAllBooks() {

		return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
	}

	@PostMapping("/books")
	public ResponseEntity<EntityModel<Book>> createBook(@RequestBody Book book) {
		Book savedBook = repository.save(book);
		EntityModel entityModel = assembler.toModel(savedBook);
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<EntityModel<Book>> findBookById(@PathVariable long id) {

		return repository.findById(id)
				.map(assembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<EntityModel<Book>> updateBook(@RequestBody Book updateBook, @PathVariable long id) {

		return repository.findById(id)
				.map(book -> {
					book.setName(updateBook.getName());
					book.setAuthor(updateBook.getAuthor());
					book.setDescription(updateBook.getDescription());
					repository.save(book);
					return ResponseEntity.ok(assembler.toModel(book));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/shelves/{id}/books")
	public ResponseEntity<CollectionModel<EntityModel<Book>>> findBooksByShelfId(@PathVariable long id) {

		CollectionModel<EntityModel<Book>> collectionModel = assembler
				.toCollectionModel(repository.findByShelfId(id));

		Links newLinks = collectionModel.getLinks().merge(Links.MergeMode.REPLACE_BY_REL,
				linkTo(methodOn(BookController.class).findBooksByShelfId(id)).withSelfRel());

		return ResponseEntity.ok(CollectionModel.of(collectionModel.getContent(), newLinks));
	}

	@GetMapping("/books/detailed")
	public ResponseEntity<CollectionModel<EntityModel<BookWithShelf>>> findAllDetailedBooks() {

		return ResponseEntity.ok(
				bookWithShelfResourceAssembler.toCollectionModel(
						StreamSupport.stream(repository.findAll().spliterator(), false)
								.map(BookWithShelf::new)
								.collect(Collectors.toList())));
	}

	@GetMapping("/books/{id}/detailed")
	public ResponseEntity<EntityModel<BookWithShelf>> findDetailedBookById(@PathVariable Long id) {

		return repository.findById(id)
				.map(BookWithShelf::new)
				.map(bookWithShelfResourceAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
