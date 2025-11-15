package challbooks.br.com.service;

import challbooks.br.com.domain.dto.request.SubjectRequestDTO;
import challbooks.br.com.domain.dto.response.SubjectResponseDTO;

import java.util.List;

public interface SubjectService {
    SubjectResponseDTO create(SubjectRequestDTO dto);
    SubjectResponseDTO update(Long id, SubjectRequestDTO dto);
    void delete(Long id);
    SubjectResponseDTO findById(Long id);
    List<SubjectResponseDTO> findAll();
}
