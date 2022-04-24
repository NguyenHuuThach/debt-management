package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.InterestPaidHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InterestPaidHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestPaidHistoryRepository extends JpaRepository<InterestPaidHistory, Long> {}
