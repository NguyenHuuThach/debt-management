package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.ContractSettlement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractSettlement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractSettlementRepository extends JpaRepository<ContractSettlement, Long> {}
