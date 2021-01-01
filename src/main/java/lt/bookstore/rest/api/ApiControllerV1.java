package lt.bookstore.rest.api;

import lt.bookstore.rest.dto.DtoAuthor;
import lt.bookstore.rest.dto.DtoBook;
import lt.bookstore.rest.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ApiControllerV1 implements Controller{

    @Autowired
    private Services services;

    @Override
    @PostMapping("price")
    public ResponseEntity<Map<Object, Object>> calculateTotalPriceBooksByCode(@RequestBody final List<Long> codes) {
        Map<Object, Object> response = new HashMap<>();
        BigDecimal totalPrice = services.calculateTotalPriceBooksByCode(codes);
        if ( totalPrice!=null || totalPrice.intValue()!=0 ){
            response.put("total_price",totalPrice);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @GetMapping("books/{code}")
    public ResponseEntity<Map<Object, Object>> getBookByCode(@PathVariable Long code) {
        Map<Object, Object> response = new HashMap<>();
        List<DtoBook> books= services.getBooksByCode(code);
        if (books != null ){
            response.put("book",books);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("books")
    public ResponseEntity<Map<Object, Object>> addBooksWithAuthor(@RequestBody final  @Valid DtoBook dtoBook) {
        Map<Object, Object> response = new HashMap<>();
        response.put("book",dtoBook);
        DtoAuthor dtoAuthor = new DtoAuthor();
        dtoAuthor.setBornDate(dtoBook.getAuthor().getBornDate());
        dtoAuthor.setSurname(dtoBook.getAuthor().getSurname());
        dtoAuthor.setName(dtoBook.getAuthor().getName());
        response.put("author",dtoAuthor);
        boolean result = services.addBooksWithAuthor(dtoBook,dtoAuthor);
        response.put("result",result);
        return result ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("books/{code}")
    public ResponseEntity<Map<Object, Object>> changeBooksByCode(@PathVariable Long code, @RequestBody final  @Valid DtoBook dtoBook) {
        Map<Object, Object> response = new HashMap<>();
        response.put("book",dtoBook);
        response.put("code",code);
        boolean result = services.changeBooksByCode(code,dtoBook);
        return result ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*@Override
    @GetMapping("book/{id}")
    public ResponseEntity<Map<Object, Object>> getBookById( @PathVariable Long id) {
        Map<Object, Object> response = new HashMap<>();
        DtoBook book = services.getBookById(id);
        if (book != null ){
            response.put("book",book);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("book")
    public ResponseEntity<Map<Object, Object>> getBookByName( @RequestParam String name) {
        Map<Object, Object> response = new HashMap<>();
        List<DtoBook> dtoBooks = services.getBookByName(name);
        if (dtoBooks != null ){
            response.put("book",dtoBooks);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("author/{id}")
    public ResponseEntity<Map<Object, Object>> getAuthorById( @PathVariable Long id) {
        Map<Object, Object> response = new HashMap<>();
        DtoAuthor author = services.getAuthorById(id);
        if (author != null ){
            response.put("author",author);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("author")
    public ResponseEntity<Map<Object, Object>> getAuthorByName( @RequestParam String name) {
        Map<Object, Object> response = new HashMap<>();
        List<DtoAuthor> authors= services.getAuthorByName(name);
        if (authors != null ){
            response.put("author",authors);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("book")
    public ResponseEntity<Map<Object, Object>> addBook( @RequestBody final @Valid DtoBook dtoBook) {
        Map<Object, Object> response = new HashMap<>();
        response.put("book",dtoBook);
        boolean result = services.addBook(dtoBook);
        response.put("result",result);
        return result ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PostMapping("author")
    public ResponseEntity<Map<Object, Object>> addAuthor( @RequestBody final @Valid DtoAuthor dtoAuthor) {
        Map<Object, Object> response = new HashMap<>();
        response.put("author",dtoAuthor);
        boolean result = services.addAuthor(dtoAuthor);
        response.put("result",result);
        return result ?  ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("author/{id}")
    public ResponseEntity<Map<Object, Object>> changeAuthor(@PathVariable Long id, @RequestBody final @Valid DtoAuthor dtoAuthor) {
        Map<Object, Object> response = new HashMap<>();
        response.put("author",dtoAuthor);
        boolean result = services.changeAuthor(id,dtoAuthor);
        return result ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("book/{id}")
    public ResponseEntity<Map<Object, Object>> changeBook(@PathVariable Long id, @RequestBody final @Valid DtoBook dtoBook) {
        Map<Object, Object> response = new HashMap<>();
        response.put("book",dtoBook);
        boolean result = services.changeBook(id,dtoBook);
        return result ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
}
