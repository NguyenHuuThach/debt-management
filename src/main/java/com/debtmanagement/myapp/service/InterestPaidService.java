package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.InterestPaid;
import com.debtmanagement.myapp.repository.InterestPaidRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InterestPaid}.
 */
@Service
@Transactional
public class InterestPaidService {

    private final Logger log = LoggerFactory.getLogger(InterestPaidService.class);

    private final InterestPaidRepository interestPaidRepository;

    public InterestPaidService(InterestPaidRepository interestPaidRepository) {
        this.interestPaidRepository = interestPaidRepository;
    }

    /**
     * Save a interestPaid.
     *
     * @param interestPaid the entity to save.
     * @return the persisted entity.
     */
    public InterestPaid save(InterestPaid interestPaid) {
        log.debug("Request to save InterestPaid : {}", interestPaid);
        return interestPaidRepository.save(interestPaid);
    }

    /**
     * Update a interestPaid.
     *
     * @param interestPaid the entity to save.
     * @return the persisted entity.
     */
    public InterestPaid update(InterestPaid interestPaid) {
        log.debug("Request to save InterestPaid : {}", interestPaid);
        return interestPaidRepository.save(interestPaid);
    }

    /**
     * Partially update a interestPaid.
     *
     * @param interestPaid the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InterestPaid> partialUpdate(InterestPaid interestPaid) {
        log.debug("Request to partially update InterestPaid : {}", interestPaid);

        return interestPaidRepository
            .findById(interestPaid.getId())
            .map(existingInterestPaid -> {
                if (interestPaid.getContractId() != null) {
                    existingInterestPaid.setContractId(interestPaid.getContractId());
                }
                if (interestPaid.getInterestPaidDate() != null) {
                    existingInterestPaid.setInterestPaidDate(interestPaid.getInterestPaidDate());
                }
                if (interestPaid.getPayerName() != null) {
                    existingInterestPaid.setPayerName(interestPaid.getPayerName());
                }
                if (interestPaid.getPaidAmount() != null) {
                    existingInterestPaid.setPaidAmount(interestPaid.getPaidAmount());
                }
                if (interestPaid.getNote() != null) {
                    existingInterestPaid.setNote(interestPaid.getNote());
                }

                return existingInterestPaid;
            })
            .map(interestPaidRepository::save);
    }

    /**
     * Get all the interestPaids.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InterestPaid> findAll() {
        log.debug("Request to get all InterestPaids");
        return interestPaidRepository.findAll();
    }

    /**
     * Get one interestPaid by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InterestPaid> findOne(Long id) {
        log.debug("Request to get InterestPaid : {}", id);
        return interestPaidRepository.findById(id);
    }

    /**
     * Delete the interestPaid by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InterestPaid : {}", id);
        interestPaidRepository.deleteById(id);
    }
}
