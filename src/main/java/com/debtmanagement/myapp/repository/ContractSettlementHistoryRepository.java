package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.ContractSettlementHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractSettlementHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractSettlementHistoryRepository extends JpaRepository<ContractSettlementHistory, Long> {}
