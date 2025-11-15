package challbooks.br.com.repository;

import challbooks.br.com.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPublisherContainingIgnoreCase(String publisher);

    List<Book> findByPriceBetween(Double min, Double max);

    Optional<Book> findByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCase(String title);
}
