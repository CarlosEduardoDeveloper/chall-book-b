package challbooks.br.com.repository;

import challbooks.br.com.domain.VwBooksByAuthor;
import challbooks.br.com.domain.VwBooksByAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<VwBooksByAuthor, VwBooksByAuthorId> {

    @Query(value = "SELECT * FROM vw_books_by_author ORDER BY book_id, author_name, subject_description",
            nativeQuery = true)
    List<VwBooksByAuthor> findReportData();
}