package challbooks.br.com.service.impl;

import challbooks.br.com.domain.Subject;
import challbooks.br.com.domain.dto.request.SubjectRequestDTO;
import challbooks.br.com.domain.dto.response.SubjectResponseDTO;
import challbooks.br.com.exception.BusinessException;
import challbooks.br.com.repository.SubjectRepository;
import challbooks.br.com.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public SubjectResponseDTO create(SubjectRequestDTO dto) {
        if (subjectRepository.existsByDescriptionIgnoreCase(dto.description())) {
            throw new BusinessException("Subject with same description already exists.");
        }
        Subject subject = new Subject();
        subject.setDescription(dto.description());
        Subject saved = subjectRepository.save(subject);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public SubjectResponseDTO update(Long id, SubjectRequestDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found."));
        subject.setDescription(dto.description());
        Subject saved = subjectRepository.save(subject);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject not found.");
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectResponseDTO findById(Long id) {
        return subjectRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found."));
    }

    @Override
    public List<SubjectResponseDTO> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private SubjectResponseDTO toDTO(Subject s) {
        return new SubjectResponseDTO(s.getId(), s.getDescription());
    }
}