package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.InterestPaidHistory;
import com.debtmanagement.myapp.repository.InterestPaidHistoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InterestPaidHistory}.
 */
@Service
@Transactional
public class InterestPaidHistoryService {

    private final Logger log = LoggerFactory.getLogger(InterestPaidHistoryService.class);

    private final InterestPaidHistoryRepository interestPaidHistoryRepository;

    public InterestPaidHistoryService(InterestPaidHistoryRepository interestPaidHistoryRepository) {
        this.interestPaidHistoryRepository = interestPaidHistoryRepository;
    }

    /**
     * Save a interestPaidHistory.
     *
     * @param interestPaidHistory the entity to save.
     * @return the persisted entity.
     */
    public InterestPaidHistory save(InterestPaidHistory interestPaidHistory) {
        log.debug("Request to save InterestPaidHistory : {}", interestPaidHistory);
        return interestPaidHistoryRepository.save(interestPaidHistory);
    }

    /**
     * Update a interestPaidHistory.
     *
     * @param interestPaidHistory the entity to save.
     * @return the persisted entity.
     */
    public InterestPaidHistory update(InterestPaidHistory interestPaidHistory) {
        log.debug("Request to save InterestPaidHistory : {}", interestPaidHistory);
        return interestPaidHistoryRepository.save(interestPaidHistory);
    }

    /**
     * Partially update a interestPaidHistory.
     *
     * @param interestPaidHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InterestPaidHistory> partialUpdate(InterestPaidHistory interestPaidHistory) {
        log.debug("Request to partially update InterestPaidHistory : {}", interestPaidHistory);

        return interestPaidHistoryRepository
            .findById(interestPaidHistory.getId())
            .map(existingInterestPaidHistory -> {
                if (interestPaidHistory.getContractId() != null) {
                    existingInterestPaidHistory.setContractId(interestPaidHistory.getContractId());
                }
                if (interestPaidHistory.getInterestPaidDate() != null) {
                    existingInterestPaidHistory.setInterestPaidDate(interestPaidHistory.getInterestPaidDate());
                }
                if (interestPaidHistory.getPayerName() != null) {
                    existingInterestPaidHistory.setPayerName(interestPaidHistory.getPayerName());
                }
                if (interestPaidHistory.getPaidAmount() != null) {
                    existingInterestPaidHistory.setPaidAmount(interestPaidHistory.getPaidAmount());
                }
                if (interestPaidHistory.getNote() != null) {
                    existingInterestPaidHistory.setNote(interestPaidHistory.getNote());
                }

                return existingInterestPaidHistory;
            })
            .map(interestPaidHistoryRepository::save);
    }

    /**
     * Get all the interestPaidHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InterestPaidHistory> findAll() {
        log.debug("Request to get all InterestPaidHistories");
        return interestPaidHistoryRepository.findAll();
    }

    /**
     * Get one interestPaidHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InterestPaidHistory> findOne(Long id) {
        log.debug("Request to get InterestPaidHistory : {}", id);
        return interestPaidHistoryRepository.findById(id);
    }

    /**
     * Delete the interestPaidHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InterestPaidHistory : {}", id);
        interestPaidHistoryRepository.deleteById(id);
    }
}
