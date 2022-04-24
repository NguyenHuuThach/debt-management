package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.ContractSettlementHistory;
import com.debtmanagement.myapp.repository.ContractSettlementHistoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractSettlementHistory}.
 */
@Service
@Transactional
public class ContractSettlementHistoryService {

    private final Logger log = LoggerFactory.getLogger(ContractSettlementHistoryService.class);

    private final ContractSettlementHistoryRepository contractSettlementHistoryRepository;

    public ContractSettlementHistoryService(ContractSettlementHistoryRepository contractSettlementHistoryRepository) {
        this.contractSettlementHistoryRepository = contractSettlementHistoryRepository;
    }

    /**
     * Save a contractSettlementHistory.
     *
     * @param contractSettlementHistory the entity to save.
     * @return the persisted entity.
     */
    public ContractSettlementHistory save(ContractSettlementHistory contractSettlementHistory) {
        log.debug("Request to save ContractSettlementHistory : {}", contractSettlementHistory);
        return contractSettlementHistoryRepository.save(contractSettlementHistory);
    }

    /**
     * Update a contractSettlementHistory.
     *
     * @param contractSettlementHistory the entity to save.
     * @return the persisted entity.
     */
    public ContractSettlementHistory update(ContractSettlementHistory contractSettlementHistory) {
        log.debug("Request to save ContractSettlementHistory : {}", contractSettlementHistory);
        return contractSettlementHistoryRepository.save(contractSettlementHistory);
    }

    /**
     * Partially update a contractSettlementHistory.
     *
     * @param contractSettlementHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractSettlementHistory> partialUpdate(ContractSettlementHistory contractSettlementHistory) {
        log.debug("Request to partially update ContractSettlementHistory : {}", contractSettlementHistory);

        return contractSettlementHistoryRepository
            .findById(contractSettlementHistory.getId())
            .map(existingContractSettlementHistory -> {
                if (contractSettlementHistory.getContractId() != null) {
                    existingContractSettlementHistory.setContractId(contractSettlementHistory.getContractId());
                }
                if (contractSettlementHistory.getSettlerName() != null) {
                    existingContractSettlementHistory.setSettlerName(contractSettlementHistory.getSettlerName());
                }

                return existingContractSettlementHistory;
            })
            .map(contractSettlementHistoryRepository::save);
    }

    /**
     * Get all the contractSettlementHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ContractSettlementHistory> findAll() {
        log.debug("Request to get all ContractSettlementHistories");
        return contractSettlementHistoryRepository.findAll();
    }

    /**
     * Get one contractSettlementHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractSettlementHistory> findOne(Long id) {
        log.debug("Request to get ContractSettlementHistory : {}", id);
        return contractSettlementHistoryRepository.findById(id);
    }

    /**
     * Delete the contractSettlementHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractSettlementHistory : {}", id);
        contractSettlementHistoryRepository.deleteById(id);
    }
}
