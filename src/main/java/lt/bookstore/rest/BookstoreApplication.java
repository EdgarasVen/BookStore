package lt.bookstore.rest;

import lt.bookstore.rest.api.Controller;
import lt.bookstore.rest.dto.DtoAuthor;
import lt.bookstore.rest.dto.DtoBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	private Controller controller;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DtoAuthor dtoAuthor = new DtoAuthor();
		dtoAuthor.setBornDate(LocalDate.parse("1900-01-01"));
		dtoAuthor.setName("Puskin");
		dtoAuthor.setSurname("Vasilij");

		DtoBook dtoBook = new DtoBook();
		dtoBook.setName("Skazki");
		dtoBook.setReleaseDate(LocalDate.now());
		dtoBook.setPrice(BigDecimal.valueOf(100));
		dtoBook.setCode(30001L);
		dtoBook.setAuthor(dtoAuthor);

		DtoAuthor dtoAuthor2 = new DtoAuthor();
		dtoAuthor2.setBornDate(LocalDate.parse("1933-11-21"));
		dtoAuthor2.setName("Andrej");
		dtoAuthor2.setSurname("Tolstij");

		DtoBook dtoBook2 = new DtoBook();
		dtoBook2.setName("Roman");
		dtoBook2.setReleaseDate(LocalDate.now());
		dtoBook2.setPrice(BigDecimal.valueOf(1001));
		dtoBook2.setCode(30001L);
		dtoBook2.setAuthor(dtoAuthor2);

		DtoAuthor dtoAuthor3 = new DtoAuthor();
		dtoAuthor3.setBornDate(LocalDate.parse("1960-11-21"));
		dtoAuthor3.setName("Vova");
		dtoAuthor3.setSurname("Park");

		DtoBook dtoBook3 = new DtoBook();
		dtoBook3.setName("OOP");
		dtoBook3.setReleaseDate(LocalDate.now());
		dtoBook3.setPrice(BigDecimal.valueOf(100));
		dtoBook3.setCode(30002L);
		dtoBook3.setScienceIndex(10);
		dtoBook3.setAuthor(dtoAuthor3);

		DtoAuthor dtoAuthor4 = new DtoAuthor();
		dtoAuthor4.setBornDate(LocalDate.parse("1910-11-21"));
		dtoAuthor4.setName("Musa");
		dtoAuthor4.setSurname("Kulakov");

		DtoBook dtoBook4 = new DtoBook();
		dtoBook4.setName("OLD");
		dtoBook4.setReleaseDate(LocalDate.parse("1878-01-01"));
		dtoBook4.setPrice(BigDecimal.valueOf(100));
		dtoBook4.setCode(30003L);
		dtoBook4.setScienceIndex(1);
		dtoBook4.setAuthor(dtoAuthor4);

		controller.addBooksWithAuthor(dtoBook);
		controller.addBooksWithAuthor(dtoBook2);
		controller.addBooksWithAuthor(dtoBook3);
		controller.addBooksWithAuthor(dtoBook4);

	}
}
