package lt.bookstore.rest.service;

import lombok.extern.slf4j.Slf4j;
import lt.bookstore.rest.dto.DtoAuthor;
import lt.bookstore.rest.dto.DtoBook;
import lt.bookstore.rest.model.Author;
import lt.bookstore.rest.model.Book;
import lt.bookstore.rest.repo.RepoAuthor;
import lt.bookstore.rest.repo.RepoBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceImp implements Services {

    private RepoAuthor repoAuthor;
    private RepoBook repoBook;

    @Autowired
    public ServiceImp(RepoAuthor repoAuthor, RepoBook repoBook) {
        this.repoAuthor = repoAuthor;
        this.repoBook = repoBook;
    }



    @Override
    public BigDecimal calculateTotalPriceBooksByCode(List<Long> codes) {
        if (codes.isEmpty()) return null;
        else {
            List<Book> books = new ArrayList<>();
            for (Long code:codes
                 ) {
                books.addAll(repoBook.findByCode(code));
                log.info("IN calculateTotalPriceBooksByCode - find books with code: {}",code);
            }
            if (books.isEmpty()) {
                log.info("IN calculateTotalPriceBooksByCode - books not find by codes: {}",codes);
                return null;
            }else {
                BigDecimal totalPrice = books.stream()
                        .map(Book::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                log.info("IN calculateTotalPriceBooksByCode - total price calculated =: {}",totalPrice);
                return totalPrice;
            }
        }
    }

    @Override
    public List<DtoBook> getBooksByCode(Long code) {
        if (code == null) return null;
        else {
            log.info("IN getBooksByCode - search for books with code: {}",code);
            List<Book> books = repoBook.findByCode(code);
            if (books.isEmpty()) {
                log.info("IN getBooksByCode - books not find by code: {}",code);
                return null;
            }
            else {
                log.info("IN getBooksByCode - books find, list size: {}",books.size());
                List<DtoBook> dtoBooks = new ArrayList<>();
                for (Book book:books
                ) {
                    dtoBooks.add(book.toDtoBook());
                }
                return dtoBooks;
            }
        }
    }

    @Override
    public boolean addBooksWithAuthor(DtoBook dtoBook, DtoAuthor dtoAuthor) {
        if (dtoBook == null) return false;
        else {
            Book book = dtoBook.toBook();
            Author author = dtoAuthor.toAuthor();
            author.addBook(book);
            repoAuthor.save(author);
            repoBook.save(book);
            log.info("IN addBook - adding book: {}",book);
            log.info("IN addBook - adding author: {}",author);
            return true;
        }
    }

    @Override
    public boolean changeBooksByCode(Long code, DtoBook dtoBook) {
        if (code == null || dtoBook == null) return false;
        else {
            List<Book> oldBooks = repoBook.findByCode(code);
            DtoAuthor dtoAuthor = new DtoAuthor();
            dtoAuthor.setName(dtoBook.getAuthor().getName());
            dtoAuthor.setSurname(dtoBook.getAuthor().getSurname());
            dtoAuthor.setBornDate(dtoBook.getAuthor().getBornDate());
            if (oldBooks.isEmpty()){
                log.info("IN changeBooksByCode - book or author not find code: {}",code);
                return false;
            } else {
                for (Book oldBook:oldBooks
                     ) {
                    oldBook.setUpdateDate(LocalDate.now());
                    oldBook.setPrice(dtoBook.getPrice());
                    oldBook.setCode(dtoBook.getCode());
                    oldBook.setType(dtoBook.getType());
                    oldBook.setReleaseDate(dtoBook.getReleaseDate());
                    oldBook.setScienceIndex(dtoBook.getScienceIndex());
                    oldBook.calculatePriceAndType();
                    oldBook.getAuthor().setSurname(dtoBook.getAuthor().getSurname());
                    oldBook.getAuthor().setName(dtoBook.getAuthor().getName());
                    oldBook.getAuthor().setBornDate(dtoBook.getAuthor().getBornDate());
                    repoBook.save(oldBook);
                    log.info("IN changeBooksByCode - book changed: {}",oldBook);
                    log.info("IN changeBooksByCode - author changed: {}",oldBook.getAuthor());
                }
                return true;
            }
        }
    }
    /*@Override
    public DtoBook getBookById(Long id) {
        if (id == null) return null;
        else {
            log.info("IN getBookById - search for book id: {}",id);
            Book book = repoBook.findById(id).orElse(null);
            if (book==null) {
                log.info("IN getBookById - book not find by id: {}",id);
                return null;
            }
            else {
                log.info("IN getBookById - book find: {}",book);
                return book.toDtoBook();
            }
        }
    }

    @Override
    public List<DtoBook> getBookByName(String name) {
        if (name == null) return null;
        else {
            log.info("IN getBookByName - search for book name: {}",name);
            List<Book> books = repoBook.findByName(name);
            if (books==null || books.size() == 0) {
                log.info("IN getBookByName - book by name not find : {}",name);
                return null;
            }
            else {
                log.info("IN getBookByName - book find, list size: {}", books.size());
                List<DtoBook> dtoBooks = new ArrayList<>();
                for (Book book:books
                ) {
                    dtoBooks.add(book.toDtoBook());
                }
                return dtoBooks;
            }
        }
    }

    @Override
    public DtoAuthor getAuthorById(Long id) {
        if (id == null) return null;
        else {
            log.info("IN getAuthorById - search for author id: {}",id);
            Author author = repoAuthor.findById(id).orElse(null);
            if (author==null) {
                log.info("IN getAuthorById - author not find by id: {}",id);
                return null;
            }
            else {
                log.info("IN getAuthorById - author find: {}",author);
                return author.toDtoAuthor();
            }
        }
    }

    @Override
    public List<DtoAuthor> getAuthorByName(String name) {
        if (name == null) return null;
        else {
            log.info("IN getAuthorByName - search for author name: {}",name);
            List<Author> authors = repoAuthor.findByName(name);
            if (authors==null || authors.size() == 0) {
                log.info("IN getAuthorByName - author by name not find : {}",name);
                return null;
            }
            else {
                log.info("IN getAuthorByName - authors find, list size: {}", authors.size());
                List<DtoAuthor> dtoAuthors = new ArrayList<>();
                for (Author author:authors
                     ) {
                    dtoAuthors.add(author.toDtoAuthor());
                }
                return dtoAuthors;
            }
        }
    }

    @Override
    public boolean addBook(DtoBook dtoBook) {
        if (dtoBook == null) return false;
        else {
            Book book = dtoBook.toBook();
            log.info("IN addBook - adding book: {}",book);
            repoBook.save(book);
            log.info("IN addBook - book saved: {}",book);
            return true;
        }
    }

    @Override
    public boolean addAuthor(DtoAuthor dtoAuthor) {
        if (dtoAuthor == null) return false;
        else {
            Author author = dtoAuthor.toAuthor();
            log.info("IN addAuthor - adding author: {}",author);
            repoAuthor.save(author);
            log.info("IN addAuthor - author saved: {}",author);
            return true;
        }
    }

    @Override
    public boolean changeAuthor(Long id,DtoAuthor dtoAuthor) {
        if (id == null || dtoAuthor == null) return false;
        else {
            Author oldAuthor = repoAuthor.findById(id).orElse(null);
            if (oldAuthor == null){
                log.info("IN changeAuthor - author not find id: {}",id);
                return false;
            } else {
                oldAuthor.setUpdateDate(LocalDate.now());
                oldAuthor.setBornDate(dtoAuthor.getBornDate());
                oldAuthor.setName(dtoAuthor.getName());
                oldAuthor.setSurname(dtoAuthor.getSurname());
                repoAuthor.save(oldAuthor);
                log.info("IN changeAuthor - author saved: {}",oldAuthor);
                return true;
            }
        }
    }

    @Override
    public boolean changeBook(Long id,DtoBook dtoBook) {
        if (id == null || dtoBook == null) return false;
        else {
            Book oldBook = repoBook.findById(id).orElse(null);
            if (oldBook == null){
                log.info("IN changeBook - book not find id: {}",id);
                return false;
            } else {
                oldBook.setUpdateDate(LocalDate.now());
                oldBook.setPrice(dtoBook.getPrice());
                oldBook.setCode(dtoBook.getCode());
                oldBook.setType(dtoBook.getType());
                oldBook.setReleaseDate(dtoBook.getReleaseDate());
                oldBook.setScienceIndex(dtoBook.getScienceIndex());
                oldBook.calculatePriceAndType();
                repoBook.save(oldBook);
                log.info("IN changeBook - book saved: {}",oldBook);
                return true;
            }
        }
    }*/
}
