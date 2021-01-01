package lt.bookstore.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.bookstore.rest.enums.Constraints;
import lt.bookstore.rest.dto.DtoBook;
import lt.bookstore.rest.enums.Status;
import lt.bookstore.rest.enums.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String name;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Long code;
    private LocalDate releaseDate;
    private int scienceIndex;
    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public DtoBook toDtoBook(){
        DtoBook dtoBook = new DtoBook();
        dtoBook.setName(name);
        dtoBook.setPrice(totalPrice);
        dtoBook.setReleaseDate(releaseDate);
        dtoBook.setCode(code);
        dtoBook.setType(type);
        dtoBook.setScienceIndex(scienceIndex);
        dtoBook.setAuthor(author.toDtoAuthor());
        return dtoBook;
    }

    public Book toBook(DtoBook dtoBook){
        this.setStatus(Status.ACTIVE);
        this.setCreateDate(LocalDate.now());
        this.setUpdateDate(LocalDate.now());
        this.setName(dtoBook.getName());
        this.setPrice(dtoBook.getPrice());
        this.setCode(dtoBook.getCode());
        this.setScienceIndex(dtoBook.getScienceIndex());
        this.setReleaseDate(dtoBook.getReleaseDate());
        this.setType(Type.BOOK);
        this.calculatePriceAndType();
        return this;
    }

    public void calculatePriceAndType(){
        this.totalPrice = price;
        if (scienceIndex > 0){
            this.setType(Type.SCIENCE_BOOK);
            this.totalPrice = price.multiply(BigDecimal.valueOf(scienceIndex));
        }
        LocalDate antiqueDate = LocalDate.parse(Constraints.DATE);
        LocalDate nowDate = LocalDate.now();
        if (releaseDate.compareTo(antiqueDate) <= 0){
            this.setType(Type.ANTIQUE_BOOK);
            BigDecimal dateDifferent = BigDecimal.valueOf(nowDate.compareTo(releaseDate));
            this.setTotalPrice(dateDifferent.divide(BigDecimal.valueOf(10),3).multiply(price));
        }
    }

    public void addAuthor(Author author){
        author.addBook(this);
        this.author=author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return code == book.code &&
                scienceIndex == book.scienceIndex &&
                name.equals(book.name) &&
                price.equals(book.price) &&
                totalPrice.equals(book.totalPrice) &&
                releaseDate.equals(book.releaseDate) &&
                type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, totalPrice, code, releaseDate, scienceIndex, type);
    }
}
