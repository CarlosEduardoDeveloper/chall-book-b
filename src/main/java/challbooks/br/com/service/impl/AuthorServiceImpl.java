package challbooks.br.com.service.impl;

import challbooks.br.com.domain.Author;
import challbooks.br.com.domain.dto.request.AuthorRequestDTO;
import challbooks.br.com.domain.dto.response.AuthorResponseDTO;
import challbooks.br.com.exception.BusinessException;
import challbooks.br.com.repository.AuthorRepository;
import challbooks.br.com.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public AuthorResponseDTO create(AuthorRequestDTO dto) {
        if (authorRepository.existsByNameIgnoreCase(dto.name())) {
            throw new BusinessException("Author with same name already exists.");
        }
        Author author = new Author();
        author.setName(dto.name());
        Author saved = authorRepository.save(author);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public AuthorResponseDTO update(Long id, AuthorRequestDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found."));
        author.setName(dto.name());
        Author saved = authorRepository.save(author);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found.");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorResponseDTO findById(Long id) {
        return authorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Author not found."));
    }

    @Override
    public List<AuthorResponseDTO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private AuthorResponseDTO toDTO(Author a) {
        return new AuthorResponseDTO(a.getId(), a.getName());
    }
}