package dev.dscfptuhcmc.rest.practices.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

/**
 * @author quangdatpham
 */
@Value
public class BookWithShelf {

	@JsonIgnore
    private final Book book;

	public Long getId() {

		return this.book.getId()
				.orElseThrow(() -> new RuntimeException("Couldn't find anything."));
	}

	public String getName() {
		return this.book.getName();
	}

	public String getDescription() {
		return this.book.getDescription();
	}

	public String getShelf() {
		return this.book.getShelf().getName();
	}

}
