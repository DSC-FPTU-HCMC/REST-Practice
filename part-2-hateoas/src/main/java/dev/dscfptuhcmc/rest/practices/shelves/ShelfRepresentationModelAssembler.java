package dev.dscfptuhcmc.rest.practices.shelves;

import dev.dscfptuhcmc.rest.practices.RootController;
import dev.dscfptuhcmc.rest.practices.books.BookController;
import dev.dscfptuhcmc.rest.practices.commons.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Component
class ShelfRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Shelf> {

	ShelfRepresentationModelAssembler() {
		super(ShelfController.class);
	}

	@Override
	public void addLinks(EntityModel<Shelf> resource) {

		super.addLinks(resource);
		resource.getContent().getId()
				.ifPresent(id -> {
					resource.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).findBooksByShelfId(id)).withRel("books"));
				});
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Shelf>> resources) {

		super.addLinks(resources);
		resources.add(linkTo(methodOn(BookController.class).findAllBooks()).withRel("books"));
		resources.add(linkTo(methodOn(BookController.class).findAllDetailedBooks()).withRel("detailedBooks"));
		resources.add(WebMvcLinkBuilder.linkTo(methodOn(RootController.class).root()).withRel("root"));
	}
}
