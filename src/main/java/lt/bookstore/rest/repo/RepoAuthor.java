package lt.bookstore.rest.repo;

import lt.bookstore.rest.model.Author;
import lt.bookstore.rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepoAuthor extends JpaRepository<Author, Long> {
    @Transactional
    List<Author> findByName(String name);
}
