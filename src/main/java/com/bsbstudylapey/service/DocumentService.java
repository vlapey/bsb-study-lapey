package com.bsbstudylapey.service;

import com.bsbstudylapey.dto.DocumentDto;
import com.bsbstudylapey.mappers.DocumentMapper;
import com.bsbstudylapey.models.Document;
import com.bsbstudylapey.models.User;
import com.bsbstudylapey.repo.DocumentRepository;
import com.bsbstudylapey.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static com.bsbstudylapey.Constants.DELETED_SUCCESSFULLY;
import static com.bsbstudylapey.Constants.SUCH_ENTITY_DOES_NOT_EXIST;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such document does not exist"));
    }

    public Document createDocument(DocumentDto documentDto, Long userId) {
        if (userId == null) {
            return documentRepository.save(DocumentMapper.INSTANCE.dtoToDocument(documentDto));
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        documentDto.setUser(user);
        return documentRepository.save(DocumentMapper.INSTANCE.dtoToDocument(documentDto));
    }

    public Document updateDocument(DocumentDto documentDto, Long id, Long userId) {
        Document oldDocument = documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        documentDto.setId(oldDocument.getId());
        if (documentDto.getDocumentName().isEmpty()) {
            documentDto.setDocumentName(oldDocument.getDocumentName());
        }
        if (userId == null) {
            return documentRepository.save(DocumentMapper.INSTANCE.dtoToDocument(documentDto));
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        documentDto.setUser(user);
        return documentRepository.save(DocumentMapper.INSTANCE.dtoToDocument(documentDto));
    }

    public String deleteById(Long id) {
        if (!documentRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        documentRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
