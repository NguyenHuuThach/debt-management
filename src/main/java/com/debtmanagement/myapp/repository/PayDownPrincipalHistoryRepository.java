package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.PayDownPrincipalHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PayDownPrincipalHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayDownPrincipalHistoryRepository extends JpaRepository<PayDownPrincipalHistory, Long> {}
