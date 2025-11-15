package challbooks.br.com.service;

import challbooks.br.com.domain.dto.request.AuthorRequestDTO;
import challbooks.br.com.domain.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO create(AuthorRequestDTO dto);
    AuthorResponseDTO update(Long id, AuthorRequestDTO dto);
    void delete(Long id);
    AuthorResponseDTO findById(Long id);
    List<AuthorResponseDTO> findAll();
}
