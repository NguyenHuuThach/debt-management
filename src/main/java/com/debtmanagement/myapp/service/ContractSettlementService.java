package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.ContractSettlement;
import com.debtmanagement.myapp.repository.ContractSettlementRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractSettlement}.
 */
@Service
@Transactional
public class ContractSettlementService {

    private final Logger log = LoggerFactory.getLogger(ContractSettlementService.class);

    private final ContractSettlementRepository contractSettlementRepository;

    public ContractSettlementService(ContractSettlementRepository contractSettlementRepository) {
        this.contractSettlementRepository = contractSettlementRepository;
    }

    /**
     * Save a contractSettlement.
     *
     * @param contractSettlement the entity to save.
     * @return the persisted entity.
     */
    public ContractSettlement save(ContractSettlement contractSettlement) {
        log.debug("Request to save ContractSettlement : {}", contractSettlement);
        return contractSettlementRepository.save(contractSettlement);
    }

    /**
     * Update a contractSettlement.
     *
     * @param contractSettlement the entity to save.
     * @return the persisted entity.
     */
    public ContractSettlement update(ContractSettlement contractSettlement) {
        log.debug("Request to save ContractSettlement : {}", contractSettlement);
        return contractSettlementRepository.save(contractSettlement);
    }

    /**
     * Partially update a contractSettlement.
     *
     * @param contractSettlement the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractSettlement> partialUpdate(ContractSettlement contractSettlement) {
        log.debug("Request to partially update ContractSettlement : {}", contractSettlement);

        return contractSettlementRepository
            .findById(contractSettlement.getId())
            .map(existingContractSettlement -> {
                if (contractSettlement.getContractId() != null) {
                    existingContractSettlement.setContractId(contractSettlement.getContractId());
                }
                if (contractSettlement.getSettlerName() != null) {
                    existingContractSettlement.setSettlerName(contractSettlement.getSettlerName());
                }

                return existingContractSettlement;
            })
            .map(contractSettlementRepository::save);
    }

    /**
     * Get all the contractSettlements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ContractSettlement> findAll() {
        log.debug("Request to get all ContractSettlements");
        return contractSettlementRepository.findAll();
    }

    /**
     * Get one contractSettlement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractSettlement> findOne(Long id) {
        log.debug("Request to get ContractSettlement : {}", id);
        return contractSettlementRepository.findById(id);
    }

    /**
     * Delete the contractSettlement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractSettlement : {}", id);
        contractSettlementRepository.deleteById(id);
    }
}
