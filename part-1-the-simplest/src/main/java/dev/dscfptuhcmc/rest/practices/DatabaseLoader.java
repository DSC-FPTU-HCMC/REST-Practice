package dev.dscfptuhcmc.rest.practices;

import dev.dscfptuhcmc.rest.practices.books.BookRepository;
import dev.dscfptuhcmc.rest.practices.books.Book;
import dev.dscfptuhcmc.rest.practices.shelves.Shelf;
import dev.dscfptuhcmc.rest.practices.shelves.ShelfRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author quangdatpham
 */
@Component
class DatabaseLoader {

	@Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, ShelfRepository shelfRepository) {
		return args -> {

			Shelf computerScienceAndProgrammingShelf = shelfRepository.save(new Shelf("Computer Science and Programming"));

			Book theBestQuantumComputingBook = bookRepository.save(new Book(
					"Quantum Computing for Everyone",
					"Chris Bernhardt",
					"Quantum Computing for Everyone is a much-needed dose of reality, and an honest path for the earnest beginner.",
					computerScienceAndProgrammingShelf));
			Book cleanCodeBook = bookRepository.save(new Book(
					"Clean Code: A Handbook of Agile Software Craftsmanship",
					"Robert Cecil Martin",
					"Every year, countless hours and significant resources are lost because of poorly written code. But it doesn't have to be that way. Noted software expert Robert C.",
					computerScienceAndProgrammingShelf));

			computerScienceAndProgrammingShelf.setBooks(Arrays.asList(theBestQuantumComputingBook, cleanCodeBook));
			shelfRepository.save(computerScienceAndProgrammingShelf);

			Shelf softwareArchitectureShelf = shelfRepository.save(new Shelf("Software Architecture"));

			Book designPatternsBook = bookRepository.save(new Book(
					"Design Patterns: Elements of Reusable Object-Oriented Software",
					"John Vlissides, Richard Helm, Ralph Johnson, Erich Gamma",
					"Design Patterns is a modern classic in the literature of object-oriented development, offering timeless and elegant solutions to common problems in software design. ",
					softwareArchitectureShelf));

			softwareArchitectureShelf.setBooks(Arrays.asList(designPatternsBook));

			shelfRepository.save(softwareArchitectureShelf);
		};
	}
}
