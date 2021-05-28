package dev.dscfptuhcmc.rest.practices;

import dev.dscfptuhcmc.rest.practices.books.BookController;
import dev.dscfptuhcmc.rest.practices.shelves.ShelfController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author quangdatpham
 */
@RestController
public class RootController {

	@GetMapping("/")
    public ResponseEntity<RepresentationModel> root() {

		RepresentationModel model = new RepresentationModel();

		model.add(linkTo(methodOn(RootController.class).root()).withSelfRel());
		model.add(linkTo(methodOn(BookController.class).findAllBooks()).withRel("books"));
		model.add(linkTo(methodOn(BookController.class).findAllDetailedBooks()).withRel("detailedBooks"));
		model.add(WebMvcLinkBuilder.linkTo(methodOn(ShelfController.class).findAllShelves()).withRel("shelves"));

		return ResponseEntity.ok(model);
	}
}
