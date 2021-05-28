package dev.dscfptuhcmc.rest.practices.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dscfptuhcmc.rest.practices.shelves.Shelf;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

/**
 * @author quangdatpham
 */
@Data
@Entity
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String author;

	private String description;

	@JsonIgnore
    @ManyToOne
	private Shelf shelf;

	public Book(String name, String author, String description, Shelf shelf) {
		this.name = name;
		this.author = author;
		this.description = description;
		this.shelf = shelf;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
}
