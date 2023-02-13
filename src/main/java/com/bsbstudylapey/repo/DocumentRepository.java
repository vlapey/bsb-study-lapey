package com.bsbstudylapey.repo;

import com.bsbstudylapey.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
