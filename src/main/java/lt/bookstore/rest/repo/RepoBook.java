package lt.bookstore.rest.repo;

import lt.bookstore.rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepoBook extends JpaRepository<Book, Long> {
    @Transactional
    List<Book> findByName(String name);
    @Transactional
    List<Book> findByCode(Long code);
}
