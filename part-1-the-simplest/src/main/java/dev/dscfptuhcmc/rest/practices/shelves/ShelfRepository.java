package dev.dscfptuhcmc.rest.practices.shelves;

import org.springframework.data.repository.CrudRepository;

/**
 * @author quangdatpham
 */
public interface ShelfRepository extends CrudRepository<Shelf, Long> {

	Shelf findByBooksId(Long id);
}
