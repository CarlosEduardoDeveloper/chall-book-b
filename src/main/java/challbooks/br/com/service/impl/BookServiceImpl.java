package challbooks.br.com.service.impl;

import challbooks.br.com.domain.Author;
import challbooks.br.com.domain.Book;
import challbooks.br.com.domain.Subject;
import challbooks.br.com.domain.dto.request.BookRequestDTO;
import challbooks.br.com.domain.dto.response.AuthorResponseDTO;
import challbooks.br.com.domain.dto.response.BookResponseDTO;
import challbooks.br.com.domain.dto.response.SubjectResponseDTO;
import challbooks.br.com.exception.BusinessException;
import challbooks.br.com.repository.AuthorRepository;
import challbooks.br.com.repository.BookRepository;
import challbooks.br.com.repository.SubjectRepository;
import challbooks.br.com.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public BookResponseDTO create(BookRequestDTO dto) {
        if (bookRepository.existsByTitleIgnoreCase(dto.title())) {
            throw new BusinessException("Book with same title already exists.");
        }
        Book book = new Book();
        setFields(book, dto);
        Book saved = bookRepository.save(book);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found."));
        setFields(book, dto);
        Book saved = bookRepository.save(book);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found.");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Book not found."));
    }

    @Override
    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private void setFields(Book book, BookRequestDTO dto) {
        book.setTitle(dto.title());
        book.setPublisher(dto.publisher());
        book.setEdition(dto.edition());
        book.setPublicationYear(dto.publicationYear());
        book.setPrice(dto.price());

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.authorIds()));
        if (authors.isEmpty()) {
            throw new BusinessException("Invalid author list. No valid authors found.");
        }

        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(dto.subjectIds()));
        if (subjects.isEmpty()) {
            throw new BusinessException("Invalid subject list. No valid subjects found.");
        }

        book.setAuthors(authors);
        book.setSubjects(subjects);
    }

    private BookResponseDTO toDTO(Book book) {
        Set<AuthorResponseDTO> authors = book.getAuthors()
                .stream()
                .map(a -> new AuthorResponseDTO(a.getId(), a.getName()))
                .collect(Collectors.toSet());

        Set<SubjectResponseDTO> subjects = book.getSubjects()
                .stream()
                .map(s -> new SubjectResponseDTO(s.getId(), s.getDescription()))
                .collect(Collectors.toSet());

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getPublisher(),
                book.getEdition(),
                book.getPublicationYear(),
                book.getPrice(),
                authors,
                subjects
        );
    }
}
