package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.InterestPay;
import com.debtmanagement.myapp.repository.InterestPayRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InterestPay}.
 */
@Service
@Transactional
public class InterestPayService {

    private final Logger log = LoggerFactory.getLogger(InterestPayService.class);

    private final InterestPayRepository interestPayRepository;

    public InterestPayService(InterestPayRepository interestPayRepository) {
        this.interestPayRepository = interestPayRepository;
    }

    /**
     * Save a interestPay.
     *
     * @param interestPay the entity to save.
     * @return the persisted entity.
     */
    public InterestPay save(InterestPay interestPay) {
        log.debug("Request to save InterestPay : {}", interestPay);
        return interestPayRepository.save(interestPay);
    }

    /**
     * Update a interestPay.
     *
     * @param interestPay the entity to save.
     * @return the persisted entity.
     */
    public InterestPay update(InterestPay interestPay) {
        log.debug("Request to save InterestPay : {}", interestPay);
        return interestPayRepository.save(interestPay);
    }

    /**
     * Partially update a interestPay.
     *
     * @param interestPay the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InterestPay> partialUpdate(InterestPay interestPay) {
        log.debug("Request to partially update InterestPay : {}", interestPay);

        return interestPayRepository
            .findById(interestPay.getId())
            .map(existingInterestPay -> {
                if (interestPay.getContractId() != null) {
                    existingInterestPay.setContractId(interestPay.getContractId());
                }
                if (interestPay.getInterestPayDate() != null) {
                    existingInterestPay.setInterestPayDate(interestPay.getInterestPayDate());
                }
                if (interestPay.getInterestPayAmount() != null) {
                    existingInterestPay.setInterestPayAmount(interestPay.getInterestPayAmount());
                }
                if (interestPay.getPayerName() != null) {
                    existingInterestPay.setPayerName(interestPay.getPayerName());
                }
                if (interestPay.getNote() != null) {
                    existingInterestPay.setNote(interestPay.getNote());
                }
                if (interestPay.getStatus() != null) {
                    existingInterestPay.setStatus(interestPay.getStatus());
                }

                return existingInterestPay;
            })
            .map(interestPayRepository::save);
    }

    /**
     * Get all the interestPays.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InterestPay> findAll() {
        log.debug("Request to get all InterestPays");
        return interestPayRepository.findAll();
    }

    /**
     * Get one interestPay by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InterestPay> findOne(Long id) {
        log.debug("Request to get InterestPay : {}", id);
        return interestPayRepository.findById(id);
    }

    /**
     * Delete the interestPay by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InterestPay : {}", id);
        interestPayRepository.deleteById(id);
    }
}
