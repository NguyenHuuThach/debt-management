package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.ContractHistory;
import com.debtmanagement.myapp.repository.ContractHistoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractHistory}.
 */
@Service
@Transactional
public class ContractHistoryService {

    private final Logger log = LoggerFactory.getLogger(ContractHistoryService.class);

    private final ContractHistoryRepository contractHistoryRepository;

    public ContractHistoryService(ContractHistoryRepository contractHistoryRepository) {
        this.contractHistoryRepository = contractHistoryRepository;
    }

    /**
     * Save a contractHistory.
     *
     * @param contractHistory the entity to save.
     * @return the persisted entity.
     */
    public ContractHistory save(ContractHistory contractHistory) {
        log.debug("Request to save ContractHistory : {}", contractHistory);
        return contractHistoryRepository.save(contractHistory);
    }

    /**
     * Update a contractHistory.
     *
     * @param contractHistory the entity to save.
     * @return the persisted entity.
     */
    public ContractHistory update(ContractHistory contractHistory) {
        log.debug("Request to save ContractHistory : {}", contractHistory);
        return contractHistoryRepository.save(contractHistory);
    }

    /**
     * Partially update a contractHistory.
     *
     * @param contractHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractHistory> partialUpdate(ContractHistory contractHistory) {
        log.debug("Request to partially update ContractHistory : {}", contractHistory);

        return contractHistoryRepository
            .findById(contractHistory.getId())
            .map(existingContractHistory -> {
                if (contractHistory.getDateStart() != null) {
                    existingContractHistory.setDateStart(contractHistory.getDateStart());
                }
                if (contractHistory.getSettlementDate() != null) {
                    existingContractHistory.setSettlementDate(contractHistory.getSettlementDate());
                }
                if (contractHistory.getTotalLoanAmount() != null) {
                    existingContractHistory.setTotalLoanAmount(contractHistory.getTotalLoanAmount());
                }
                if (contractHistory.getInterestPaymentPeriod() != null) {
                    existingContractHistory.setInterestPaymentPeriod(contractHistory.getInterestPaymentPeriod());
                }
                if (contractHistory.getInterestRate() != null) {
                    existingContractHistory.setInterestRate(contractHistory.getInterestRate());
                }
                if (contractHistory.getCustomerName() != null) {
                    existingContractHistory.setCustomerName(contractHistory.getCustomerName());
                }
                if (contractHistory.getCustomerAddress() != null) {
                    existingContractHistory.setCustomerAddress(contractHistory.getCustomerAddress());
                }
                if (contractHistory.getCustomerPhoneNumber() != null) {
                    existingContractHistory.setCustomerPhoneNumber(contractHistory.getCustomerPhoneNumber());
                }
                if (contractHistory.getCustomerIdentityCard() != null) {
                    existingContractHistory.setCustomerIdentityCard(contractHistory.getCustomerIdentityCard());
                }
                if (contractHistory.getProductName() != null) {
                    existingContractHistory.setProductName(contractHistory.getProductName());
                }
                if (contractHistory.getImei() != null) {
                    existingContractHistory.setImei(contractHistory.getImei());
                }
                if (contractHistory.getIcloud() != null) {
                    existingContractHistory.setIcloud(contractHistory.getIcloud());
                }
                if (contractHistory.getUserCreate() != null) {
                    existingContractHistory.setUserCreate(contractHistory.getUserCreate());
                }
                if (contractHistory.getNote() != null) {
                    existingContractHistory.setNote(contractHistory.getNote());
                }
                if (contractHistory.getStatus() != null) {
                    existingContractHistory.setStatus(contractHistory.getStatus());
                }

                return existingContractHistory;
            })
            .map(contractHistoryRepository::save);
    }

    /**
     * Get all the contractHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ContractHistory> findAll() {
        log.debug("Request to get all ContractHistories");
        return contractHistoryRepository.findAll();
    }

    /**
     * Get one contractHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractHistory> findOne(Long id) {
        log.debug("Request to get ContractHistory : {}", id);
        return contractHistoryRepository.findById(id);
    }

    /**
     * Delete the contractHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractHistory : {}", id);
        contractHistoryRepository.deleteById(id);
    }
}
