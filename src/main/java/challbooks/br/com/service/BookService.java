package challbooks.br.com.service;

import challbooks.br.com.domain.dto.request.BookRequestDTO;
import challbooks.br.com.domain.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO create(BookRequestDTO dto);
    BookResponseDTO update(Long id, BookRequestDTO dto);
    void delete(Long id);
    BookResponseDTO findById(Long id);
    List<BookResponseDTO> findAll();
}