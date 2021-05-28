package dev.dscfptuhcmc.rest.practices.books;

import dev.dscfptuhcmc.rest.practices.RootController;
import dev.dscfptuhcmc.rest.practices.commons.SimpleIdentifiableRepresentationModelAssembler;
import dev.dscfptuhcmc.rest.practices.shelves.ShelfController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author quangdatpham
 */
@Component
class BookRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Book> {

	BookRepresentationModelAssembler() {
		super(BookController.class);
	}

	@Override
	public void addLinks(EntityModel<Book> resource) {

		super.addLinks(resource);
		resource.getContent().getId()
				.ifPresent(id -> {
					resource.add(WebMvcLinkBuilder.linkTo(methodOn(ShelfController.class).findShelfById(id)).withRel("shelf"));
					resource.add(linkTo(methodOn(BookController.class).findDetailedBookById(id)).withRel("detailed"));
				});
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Book>> resources) {

		super.addLinks(resources);
		resources.add(linkTo(methodOn(BookController.class).findAllDetailedBooks()).withRel("detailedBooks"));
		resources.add(linkTo(methodOn(ShelfController.class).findAllShelves()).withRel("shelves"));
		resources.add(WebMvcLinkBuilder.linkTo(methodOn(RootController.class).root()).withRel("root"));
	}
}
