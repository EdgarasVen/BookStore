package lt.bookstore.rest.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.bookstore.rest.dto.DtoBook;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface Controller {

    @ApiOperation(
            value = "Calculates total price.",
            notes = "Calculates total price of all books by code provided, code provided by list. ",
            response = ResponseEntity.class)
    ResponseEntity<Map<Object,Object>> calculateTotalPriceBooksByCode (
            @ApiParam(
                    value = "List of codes",
                    name = "codes",
                    type = "List")
                    List<Long> codes);
    @ApiOperation(
            value = "Get book",
            notes = "Returns book by code provided ",
            response = ResponseEntity.class)
    ResponseEntity<Map<Object,Object>> getBookByCode (
            @ApiParam(
                    value = "Book code",
                    name = "code",
                    type = "Long")
                    Long code);
    @ApiOperation(
            value = "Saves book and author",
            notes = "Creates book and author provided by book dto with author info ",
            response = ResponseEntity.class)
    ResponseEntity<Map<Object,Object>> addBooksWithAuthor (
            @ApiParam(
                    value = "Book object , contains book and author parameters",
                    name = "DtoBook",
                    type = "Object")
                    DtoBook dtoBook);
    @ApiOperation(
            value = "Changes books and author",
            notes = "Changes books and authors parameters by provided code, also recalculates total price and type ",
            response = ResponseEntity.class)
    ResponseEntity<Map<Object,Object>> changeBooksByCode (
            @ApiParam(
                    value = "Book code",
                    name = "code",
                    type = "Long")
                    Long code,
            @ApiParam(
                    value = "Book object with parameters to change",
                    name = "DtoBook",
                    type = "Object")
                    DtoBook dtoBook);

    /*ResponseEntity<Map<Object,Object>> getBookById (Long id);
    ResponseEntity<Map<Object,Object>> getBookByName (String name);
    ResponseEntity<Map<Object,Object>> getAuthorById (Long id);
    ResponseEntity<Map<Object,Object>> getAuthorByName (String name);

    ResponseEntity<Map<Object,Object>> addBook (DtoBook dtoBook);
    ResponseEntity<Map<Object,Object>> addAuthor (DtoAuthor dtoAuthor);

    ResponseEntity<Map<Object,Object>> changeAuthor (Long id,DtoAuthor dtoAuthor);
    ResponseEntity<Map<Object,Object>> changeBook (Long id,DtoBook dtoBook);*/
}
