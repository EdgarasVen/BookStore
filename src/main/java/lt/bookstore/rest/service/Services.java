package lt.bookstore.rest.service;

import lt.bookstore.rest.dto.DtoAuthor;
import lt.bookstore.rest.dto.DtoBook;

import java.math.BigDecimal;
import java.util.List;

public interface Services {


    BigDecimal calculateTotalPriceBooksByCode (List<Long> codes);
    List<DtoBook> getBooksByCode (Long code);
    boolean addBooksWithAuthor (DtoBook dtoBook, DtoAuthor dtoAuthor);
    boolean changeBooksByCode (Long code,DtoBook dtoBook);

    /*DtoBook getBookById (Long id);
    List<DtoBook> getBookByName (String name);
    DtoAuthor getAuthorById (Long id);
    List<DtoAuthor> getAuthorByName (String name);

    boolean addBook (DtoBook dtoBook);
    boolean addAuthor (DtoAuthor dtoAuthor);

    boolean changeAuthor (Long id,DtoAuthor dtoAuthor);
    boolean changeBook (Long id,DtoBook dtoBook);*/

}
