package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.InterestPaid;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InterestPaid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestPaidRepository extends JpaRepository<InterestPaid, Long> {}
