package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.PayDownPrincipal;
import com.debtmanagement.myapp.repository.PayDownPrincipalRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PayDownPrincipal}.
 */
@Service
@Transactional
public class PayDownPrincipalService {

    private final Logger log = LoggerFactory.getLogger(PayDownPrincipalService.class);

    private final PayDownPrincipalRepository payDownPrincipalRepository;

    public PayDownPrincipalService(PayDownPrincipalRepository payDownPrincipalRepository) {
        this.payDownPrincipalRepository = payDownPrincipalRepository;
    }

    /**
     * Save a payDownPrincipal.
     *
     * @param payDownPrincipal the entity to save.
     * @return the persisted entity.
     */
    public PayDownPrincipal save(PayDownPrincipal payDownPrincipal) {
        log.debug("Request to save PayDownPrincipal : {}", payDownPrincipal);
        return payDownPrincipalRepository.save(payDownPrincipal);
    }

    /**
     * Update a payDownPrincipal.
     *
     * @param payDownPrincipal the entity to save.
     * @return the persisted entity.
     */
    public PayDownPrincipal update(PayDownPrincipal payDownPrincipal) {
        log.debug("Request to save PayDownPrincipal : {}", payDownPrincipal);
        return payDownPrincipalRepository.save(payDownPrincipal);
    }

    /**
     * Partially update a payDownPrincipal.
     *
     * @param payDownPrincipal the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PayDownPrincipal> partialUpdate(PayDownPrincipal payDownPrincipal) {
        log.debug("Request to partially update PayDownPrincipal : {}", payDownPrincipal);

        return payDownPrincipalRepository
            .findById(payDownPrincipal.getId())
            .map(existingPayDownPrincipal -> {
                if (payDownPrincipal.getContractId() != null) {
                    existingPayDownPrincipal.setContractId(payDownPrincipal.getContractId());
                }
                if (payDownPrincipal.getPayDownPrincipalDate() != null) {
                    existingPayDownPrincipal.setPayDownPrincipalDate(payDownPrincipal.getPayDownPrincipalDate());
                }
                if (payDownPrincipal.getPayDownPrincipalAmount() != null) {
                    existingPayDownPrincipal.setPayDownPrincipalAmount(payDownPrincipal.getPayDownPrincipalAmount());
                }
                if (payDownPrincipal.getPayerName() != null) {
                    existingPayDownPrincipal.setPayerName(payDownPrincipal.getPayerName());
                }
                if (payDownPrincipal.getUserCreate() != null) {
                    existingPayDownPrincipal.setUserCreate(payDownPrincipal.getUserCreate());
                }
                if (payDownPrincipal.getNote() != null) {
                    existingPayDownPrincipal.setNote(payDownPrincipal.getNote());
                }

                return existingPayDownPrincipal;
            })
            .map(payDownPrincipalRepository::save);
    }

    /**
     * Get all the payDownPrincipals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PayDownPrincipal> findAll() {
        log.debug("Request to get all PayDownPrincipals");
        return payDownPrincipalRepository.findAll();
    }

    /**
     * Get one payDownPrincipal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PayDownPrincipal> findOne(Long id) {
        log.debug("Request to get PayDownPrincipal : {}", id);
        return payDownPrincipalRepository.findById(id);
    }

    /**
     * Delete the payDownPrincipal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PayDownPrincipal : {}", id);
        payDownPrincipalRepository.deleteById(id);
    }
}
