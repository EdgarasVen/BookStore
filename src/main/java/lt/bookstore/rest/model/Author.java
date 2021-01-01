package lt.bookstore.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.bookstore.rest.dto.DtoAuthor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Author extends BaseEntity {

    private String name;
    private String surname;
    private LocalDate bornDate;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();

    public DtoAuthor toDtoAuthor(){
        DtoAuthor dtoAuthor = new DtoAuthor();
        dtoAuthor.setName(name);
        dtoAuthor.setSurname(surname);
        dtoAuthor.setBornDate(bornDate);
        return dtoAuthor;
    }

    public void addBook(Book book){
        book.setAuthor(this);
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return name.equals(author.name) &&
                surname.equals(author.surname) &&
                bornDate.equals(author.bornDate) &&
                Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, bornDate, books);
    }
}
