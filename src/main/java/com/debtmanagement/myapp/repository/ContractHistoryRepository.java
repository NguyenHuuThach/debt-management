package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.ContractHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractHistoryRepository extends JpaRepository<ContractHistory, Long> {}
