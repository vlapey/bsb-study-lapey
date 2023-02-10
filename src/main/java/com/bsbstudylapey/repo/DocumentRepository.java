package com.bsbstudylapey.repo;

import com.bsbstudylapey.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<User, Long> {
}
