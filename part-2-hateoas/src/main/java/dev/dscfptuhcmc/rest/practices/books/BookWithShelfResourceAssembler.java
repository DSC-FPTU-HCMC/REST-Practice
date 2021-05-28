package dev.dscfptuhcmc.rest.practices.books;

import dev.dscfptuhcmc.rest.practices.RootController;
import dev.dscfptuhcmc.rest.practices.shelves.ShelfController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author quangdatpham
 */
@Component
class BookWithShelfResourceAssembler implements SimpleRepresentationModelAssembler<BookWithShelf> {

	@Override
	public void addLinks(EntityModel<BookWithShelf> resource) {

		resource.add(linkTo(methodOn(BookController.class).findDetailedBookById(resource.getContent().getId())).withSelfRel());
		resource.add(linkTo(methodOn(BookController.class).findBookById(resource.getContent().getId())).withRel("summary"));
		resource.add(linkTo(methodOn(BookController.class).findAllDetailedBooks()).withRel("detailedBooks"));
		// resource.getContent().getBook().getShelf().getId().ifPresent(id ->
		// 	resource.add(linkTo(methodOn(ShelfController.class).findShelfById(id)).withRel("shelf"))
		// );
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<BookWithShelf>> resources) {

		resources.add(linkTo(methodOn(BookController.class).findAllDetailedBooks()).withSelfRel());
		resources.add(linkTo(methodOn(BookController.class).findAllBooks()).withRel("books"));
		resources.add(WebMvcLinkBuilder.linkTo(methodOn(ShelfController.class).findAllShelves()).withRel("shelves"));
		resources.add(WebMvcLinkBuilder.linkTo(methodOn(RootController.class).root()).withRel("root"));
	}
}
