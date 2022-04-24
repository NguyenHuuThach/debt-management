package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.ContractSettlementHistory;
import com.debtmanagement.myapp.repository.ContractSettlementHistoryRepository;
import com.debtmanagement.myapp.service.ContractSettlementHistoryService;
import com.debtmanagement.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.debtmanagement.myapp.domain.ContractSettlementHistory}.
 */
@RestController
@RequestMapping("/api")
public class ContractSettlementHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ContractSettlementHistoryResource.class);

    private static final String ENTITY_NAME = "contractSettlementHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractSettlementHistoryService contractSettlementHistoryService;

    private final ContractSettlementHistoryRepository contractSettlementHistoryRepository;

    public ContractSettlementHistoryResource(
        ContractSettlementHistoryService contractSettlementHistoryService,
        ContractSettlementHistoryRepository contractSettlementHistoryRepository
    ) {
        this.contractSettlementHistoryService = contractSettlementHistoryService;
        this.contractSettlementHistoryRepository = contractSettlementHistoryRepository;
    }

    /**
     * {@code POST  /contract-settlement-histories} : Create a new contractSettlementHistory.
     *
     * @param contractSettlementHistory the contractSettlementHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractSettlementHistory, or with status {@code 400 (Bad Request)} if the contractSettlementHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-settlement-histories")
    public ResponseEntity<ContractSettlementHistory> createContractSettlementHistory(
        @Valid @RequestBody ContractSettlementHistory contractSettlementHistory
    ) throws URISyntaxException {
        log.debug("REST request to save ContractSettlementHistory : {}", contractSettlementHistory);
        if (contractSettlementHistory.getId() != null) {
            throw new BadRequestAlertException("A new contractSettlementHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractSettlementHistory result = contractSettlementHistoryService.save(contractSettlementHistory);
        return ResponseEntity
            .created(new URI("/api/contract-settlement-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-settlement-histories/:id} : Updates an existing contractSettlementHistory.
     *
     * @param id the id of the contractSettlementHistory to save.
     * @param contractSettlementHistory the contractSettlementHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSettlementHistory,
     * or with status {@code 400 (Bad Request)} if the contractSettlementHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractSettlementHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-settlement-histories/{id}")
    public ResponseEntity<ContractSettlementHistory> updateContractSettlementHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractSettlementHistory contractSettlementHistory
    ) throws URISyntaxException {
        log.debug("REST request to update ContractSettlementHistory : {}, {}", id, contractSettlementHistory);
        if (contractSettlementHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSettlementHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSettlementHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractSettlementHistory result = contractSettlementHistoryService.update(contractSettlementHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractSettlementHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-settlement-histories/:id} : Partial updates given fields of an existing contractSettlementHistory, field will ignore if it is null
     *
     * @param id the id of the contractSettlementHistory to save.
     * @param contractSettlementHistory the contractSettlementHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSettlementHistory,
     * or with status {@code 400 (Bad Request)} if the contractSettlementHistory is not valid,
     * or with status {@code 404 (Not Found)} if the contractSettlementHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractSettlementHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-settlement-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractSettlementHistory> partialUpdateContractSettlementHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractSettlementHistory contractSettlementHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractSettlementHistory partially : {}, {}", id, contractSettlementHistory);
        if (contractSettlementHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSettlementHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSettlementHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractSettlementHistory> result = contractSettlementHistoryService.partialUpdate(contractSettlementHistory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractSettlementHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-settlement-histories} : get all the contractSettlementHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractSettlementHistories in body.
     */
    @GetMapping("/contract-settlement-histories")
    public List<ContractSettlementHistory> getAllContractSettlementHistories() {
        log.debug("REST request to get all ContractSettlementHistories");
        return contractSettlementHistoryService.findAll();
    }

    /**
     * {@code GET  /contract-settlement-histories/:id} : get the "id" contractSettlementHistory.
     *
     * @param id the id of the contractSettlementHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractSettlementHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-settlement-histories/{id}")
    public ResponseEntity<ContractSettlementHistory> getContractSettlementHistory(@PathVariable Long id) {
        log.debug("REST request to get ContractSettlementHistory : {}", id);
        Optional<ContractSettlementHistory> contractSettlementHistory = contractSettlementHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractSettlementHistory);
    }

    /**
     * {@code DELETE  /contract-settlement-histories/:id} : delete the "id" contractSettlementHistory.
     *
     * @param id the id of the contractSettlementHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-settlement-histories/{id}")
    public ResponseEntity<Void> deleteContractSettlementHistory(@PathVariable Long id) {
        log.debug("REST request to delete ContractSettlementHistory : {}", id);
        contractSettlementHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
