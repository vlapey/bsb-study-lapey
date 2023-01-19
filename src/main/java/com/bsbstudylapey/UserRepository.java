package com.bsbstudylapey;

import com.bsbstudylapey.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
