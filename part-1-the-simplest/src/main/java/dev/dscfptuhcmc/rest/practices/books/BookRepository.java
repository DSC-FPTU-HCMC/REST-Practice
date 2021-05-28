package dev.dscfptuhcmc.rest.practices.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author quangdatpham
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByShelfId(Long id);
}
