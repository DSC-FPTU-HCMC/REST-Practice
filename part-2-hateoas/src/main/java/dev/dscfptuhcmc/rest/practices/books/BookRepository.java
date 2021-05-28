package dev.dscfptuhcmc.rest.practices.books;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author quangdatpham
 */
public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByShelfId(Long id);

}
