package com.tcc.repository;

import com.tcc.entity.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Integer> {
}
