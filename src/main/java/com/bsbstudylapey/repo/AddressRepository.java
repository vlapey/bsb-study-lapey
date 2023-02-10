package com.bsbstudylapey.repo;

import com.bsbstudylapey.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
