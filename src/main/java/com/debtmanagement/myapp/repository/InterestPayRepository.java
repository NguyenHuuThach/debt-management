package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.InterestPay;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InterestPay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestPayRepository extends JpaRepository<InterestPay, Long> {}
