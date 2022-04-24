package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.PayDownPrincipalHistory;
import com.debtmanagement.myapp.repository.PayDownPrincipalHistoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PayDownPrincipalHistory}.
 */
@Service
@Transactional
public class PayDownPrincipalHistoryService {

    private final Logger log = LoggerFactory.getLogger(PayDownPrincipalHistoryService.class);

    private final PayDownPrincipalHistoryRepository payDownPrincipalHistoryRepository;

    public PayDownPrincipalHistoryService(PayDownPrincipalHistoryRepository payDownPrincipalHistoryRepository) {
        this.payDownPrincipalHistoryRepository = payDownPrincipalHistoryRepository;
    }

    /**
     * Save a payDownPrincipalHistory.
     *
     * @param payDownPrincipalHistory the entity to save.
     * @return the persisted entity.
     */
    public PayDownPrincipalHistory save(PayDownPrincipalHistory payDownPrincipalHistory) {
        log.debug("Request to save PayDownPrincipalHistory : {}", payDownPrincipalHistory);
        return payDownPrincipalHistoryRepository.save(payDownPrincipalHistory);
    }

    /**
     * Update a payDownPrincipalHistory.
     *
     * @param payDownPrincipalHistory the entity to save.
     * @return the persisted entity.
     */
    public PayDownPrincipalHistory update(PayDownPrincipalHistory payDownPrincipalHistory) {
        log.debug("Request to save PayDownPrincipalHistory : {}", payDownPrincipalHistory);
        return payDownPrincipalHistoryRepository.save(payDownPrincipalHistory);
    }

    /**
     * Partially update a payDownPrincipalHistory.
     *
     * @param payDownPrincipalHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PayDownPrincipalHistory> partialUpdate(PayDownPrincipalHistory payDownPrincipalHistory) {
        log.debug("Request to partially update PayDownPrincipalHistory : {}", payDownPrincipalHistory);

        return payDownPrincipalHistoryRepository
            .findById(payDownPrincipalHistory.getId())
            .map(existingPayDownPrincipalHistory -> {
                if (payDownPrincipalHistory.getContractId() != null) {
                    existingPayDownPrincipalHistory.setContractId(payDownPrincipalHistory.getContractId());
                }
                if (payDownPrincipalHistory.getPayDownPrincipalAmount() != null) {
                    existingPayDownPrincipalHistory.setPayDownPrincipalAmount(payDownPrincipalHistory.getPayDownPrincipalAmount());
                }
                if (payDownPrincipalHistory.getPayerName() != null) {
                    existingPayDownPrincipalHistory.setPayerName(payDownPrincipalHistory.getPayerName());
                }

                return existingPayDownPrincipalHistory;
            })
            .map(payDownPrincipalHistoryRepository::save);
    }

    /**
     * Get all the payDownPrincipalHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PayDownPrincipalHistory> findAll() {
        log.debug("Request to get all PayDownPrincipalHistories");
        return payDownPrincipalHistoryRepository.findAll();
    }

    /**
     * Get one payDownPrincipalHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PayDownPrincipalHistory> findOne(Long id) {
        log.debug("Request to get PayDownPrincipalHistory : {}", id);
        return payDownPrincipalHistoryRepository.findById(id);
    }

    /**
     * Delete the payDownPrincipalHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PayDownPrincipalHistory : {}", id);
        payDownPrincipalHistoryRepository.deleteById(id);
    }
}
