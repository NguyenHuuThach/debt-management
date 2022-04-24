package com.debtmanagement.myapp.service;

import com.debtmanagement.myapp.domain.Contract;
import com.debtmanagement.myapp.repository.ContractRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Contract}.
 */
@Service
@Transactional
public class ContractService {

    private final Logger log = LoggerFactory.getLogger(ContractService.class);

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    /**
     * Save a contract.
     *
     * @param contract the entity to save.
     * @return the persisted entity.
     */
    public Contract save(Contract contract) {
        log.debug("Request to save Contract : {}", contract);
        return contractRepository.save(contract);
    }

    /**
     * Update a contract.
     *
     * @param contract the entity to save.
     * @return the persisted entity.
     */
    public Contract update(Contract contract) {
        log.debug("Request to save Contract : {}", contract);
        return contractRepository.save(contract);
    }

    /**
     * Partially update a contract.
     *
     * @param contract the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Contract> partialUpdate(Contract contract) {
        log.debug("Request to partially update Contract : {}", contract);

        return contractRepository
            .findById(contract.getId())
            .map(existingContract -> {
                if (contract.getDateStart() != null) {
                    existingContract.setDateStart(contract.getDateStart());
                }
                if (contract.getNumberInterestPayments() != null) {
                    existingContract.setNumberInterestPayments(contract.getNumberInterestPayments());
                }
                if (contract.getTotalLoanAmount() != null) {
                    existingContract.setTotalLoanAmount(contract.getTotalLoanAmount());
                }
                if (contract.getInterestPaymentPeriod() != null) {
                    existingContract.setInterestPaymentPeriod(contract.getInterestPaymentPeriod());
                }
                if (contract.getInterestRate() != null) {
                    existingContract.setInterestRate(contract.getInterestRate());
                }
                if (contract.getCustomerName() != null) {
                    existingContract.setCustomerName(contract.getCustomerName());
                }
                if (contract.getCustomerAddress() != null) {
                    existingContract.setCustomerAddress(contract.getCustomerAddress());
                }
                if (contract.getCustomerPhoneNumber() != null) {
                    existingContract.setCustomerPhoneNumber(contract.getCustomerPhoneNumber());
                }
                if (contract.getCustomerIdentityCard() != null) {
                    existingContract.setCustomerIdentityCard(contract.getCustomerIdentityCard());
                }
                if (contract.getProductName() != null) {
                    existingContract.setProductName(contract.getProductName());
                }
                if (contract.getImei() != null) {
                    existingContract.setImei(contract.getImei());
                }
                if (contract.getIcloud() != null) {
                    existingContract.setIcloud(contract.getIcloud());
                }
                if (contract.getUserCreate() != null) {
                    existingContract.setUserCreate(contract.getUserCreate());
                }
                if (contract.getNote() != null) {
                    existingContract.setNote(contract.getNote());
                }
                if (contract.getStatus() != null) {
                    existingContract.setStatus(contract.getStatus());
                }
                if (contract.getIsDeleted() != null) {
                    existingContract.setIsDeleted(contract.getIsDeleted());
                }

                return existingContract;
            })
            .map(contractRepository::save);
    }

    /**
     * Get all the contracts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Contract> findAll() {
        log.debug("Request to get all Contracts");
        return contractRepository.findAll();
    }

    /**
     * Get one contract by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Contract> findOne(Long id) {
        log.debug("Request to get Contract : {}", id);
        return contractRepository.findById(id);
    }

    /**
     * Delete the contract by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Contract : {}", id);
        contractRepository.deleteById(id);
    }
}
