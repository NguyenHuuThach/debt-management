package com.debtmanagement.myapp.repository;

import com.debtmanagement.myapp.domain.PayDownPrincipal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PayDownPrincipal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayDownPrincipalRepository extends JpaRepository<PayDownPrincipal, Long> {}
