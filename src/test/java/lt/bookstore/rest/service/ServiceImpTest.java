package lt.bookstore.rest.service;

import lt.bookstore.rest.api.ApiControllerV1;
import lt.bookstore.rest.dto.DtoAuthor;
import lt.bookstore.rest.dto.DtoBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ServiceImpTest {

    @Autowired
    private ServiceImp serviceImp;
    @Autowired
    private ApiControllerV1 controllerV1;

    @Test
    void checkIfCalculationTotalPriceBooksIsGood() {

        DtoAuthor dtoAuthor = new DtoAuthor();
        dtoAuthor.setBornDate(LocalDate.parse("1900-01-01"));
        dtoAuthor.setName("Name");
        dtoAuthor.setSurname("Surname");

        DtoBook dtoBook = new DtoBook();
        dtoBook.setName("Skazki");
        dtoBook.setReleaseDate(LocalDate.now());
        dtoBook.setPrice(BigDecimal.valueOf(100));
        dtoBook.setCode(11L);
        dtoBook.setAuthor(dtoAuthor);

        DtoBook dtoBook1 = new DtoBook();
        dtoBook1.setName("Skazki");
        dtoBook1.setReleaseDate(LocalDate.now());
        dtoBook1.setPrice(BigDecimal.valueOf(100));
        dtoBook1.setCode(22L);
        dtoBook1.setAuthor(dtoAuthor);

        controllerV1.addBooksWithAuthor(dtoBook);
        controllerV1.addBooksWithAuthor(dtoBook1);

        List<Long> codes = new ArrayList<>();
        codes.add(11L);
        codes.add(22L);
        assertEquals("Total price should be 200",
                200,
                ((BigDecimal)controllerV1.calculateTotalPriceBooksByCode(codes).getBody().get("total_price")).intValue());
    }

}