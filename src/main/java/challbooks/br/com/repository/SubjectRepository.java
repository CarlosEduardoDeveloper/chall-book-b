package challbooks.br.com.repository;

import challbooks.br.com.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByDescriptionIgnoreCase(String description);

    boolean existsByDescriptionIgnoreCase(String description);
}
