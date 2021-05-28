package dev.dscfptuhcmc.rest.practices.shelves;

import dev.dscfptuhcmc.rest.practices.books.Book;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quangdatpham
 */
@RestController
public class ShelfController {

	private final ShelfRepository repository;
	private final ShelfRepresentationModelAssembler assembler;

	ShelfController(ShelfRepository repository, ShelfRepresentationModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/shelves")
    public ResponseEntity<CollectionModel<EntityModel<Shelf>>> findAllShelves() {

		return ResponseEntity.ok(
				assembler.toCollectionModel(repository.findAll()));

	}

	@GetMapping("/shelves/{id}")
    public ResponseEntity<EntityModel<Shelf>> findShelfById(@PathVariable long id) {

		return repository.findById(id)
				.map(assembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/books/{id}/shelf")
    public ResponseEntity<EntityModel<Shelf>> findShelfByBook(@PathVariable long id) {

		return ResponseEntity.ok(
				assembler.toModel(repository.findByBooksId(id)));
	}
}
