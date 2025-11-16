package challbooks.br.com.repository;

import challbooks.br.com.domain.VwBooksByAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<VwBooksByAuthor, Long> {

    @Query(value = "SELECT * FROM vw_books_by_author ORDER BY author_name, book_title, subject_description",
            nativeQuery = true)
    List<VwBooksByAuthor> findReportData();

}