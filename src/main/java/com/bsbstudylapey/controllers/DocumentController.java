package com.bsbstudylapey.controllers;

import com.bsbstudylapey.dto.DocumentDto;
import com.bsbstudylapey.models.Document;
import com.bsbstudylapey.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {


    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Document>> findAll() {
        return new ResponseEntity<>(documentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Document> findById(Long id) {
        return new ResponseEntity<>(documentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/saveDocument")
    public ResponseEntity<Document> createDocument(@RequestBody @Valid DocumentDto documentDto, Long userId) {
        return new ResponseEntity<>(documentService.createDocument(documentDto, userId), HttpStatus.OK);
    }

    @PostMapping("/updateDocument")
    public ResponseEntity<Document> updateDocument(@RequestBody @Valid DocumentDto documentDto, Long id, Long userId) {
        return new ResponseEntity<>(documentService.updateDocument(documentDto, id, userId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(documentService.deleteById(id), HttpStatus.OK);
    }
}
