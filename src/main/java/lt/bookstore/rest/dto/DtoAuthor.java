package lt.bookstore.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lt.bookstore.rest.enums.Status;
import lt.bookstore.rest.model.Author;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class DtoAuthor {

    @NotNull
    @Length(min=1,max=120)
    private String name;
    @NotNull
    @Length(min=1,max=120)
    private String surname;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate bornDate;

    public Author toAuthor(){
        Author author = new Author();
        author.setStatus(Status.ACTIVE);
        author.setCreateDate(LocalDate.now());
        author.setUpdateDate(LocalDate.now());
        author.setName(name);
        author.setSurname(surname);
        author.setBornDate(bornDate);
        return author;
    }
}
