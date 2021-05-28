package dev.dscfptuhcmc.rest.practices.shelves;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dscfptuhcmc.rest.practices.books.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author quangdatpham
 */
@Data
@Entity
@NoArgsConstructor
public class Shelf {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "shelf")
	private List<Book> books = new ArrayList<>();

	public Shelf(String name) {
		this.name = name;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
}
