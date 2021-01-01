package lt.bookstore.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lt.bookstore.rest.enums.Status;
import lt.bookstore.rest.enums.Type;
import lt.bookstore.rest.model.Author;
import lt.bookstore.rest.model.Book;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class DtoBook {

    @NotNull
    @Length(min=1,max=120)
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long code;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate releaseDate;

    private int scienceIndex;
    private Type type;
    private DtoAuthor author;

    public Book toBook(){
        return new Book().toBook(this);
    }

}
