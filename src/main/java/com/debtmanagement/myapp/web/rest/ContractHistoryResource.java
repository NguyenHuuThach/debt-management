package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.ContractHistory;
import com.debtmanagement.myapp.repository.ContractHistoryRepository;
import com.debtmanagement.myapp.service.ContractHistoryService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.ContractHistory}.
 */
@RestController
@RequestMapping("/api")
public class ContractHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ContractHistoryResource.class);

    private static final String ENTITY_NAME = "contractHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractHistoryService contractHistoryService;

    private final ContractHistoryRepository contractHistoryRepository;

    public ContractHistoryResource(ContractHistoryService contractHistoryService, ContractHistoryRepository contractHistoryRepository) {
        this.contractHistoryService = contractHistoryService;
        this.contractHistoryRepository = contractHistoryRepository;
    }

    /**
     * {@code POST  /contract-histories} : Create a new contractHistory.
     *
     * @param contractHistory the contractHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractHistory, or with status {@code 400 (Bad Request)} if the contractHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-histories")
    public ResponseEntity<ContractHistory> createContractHistory(@Valid @RequestBody ContractHistory contractHistory)
        throws URISyntaxException {
        log.debug("REST request to save ContractHistory : {}", contractHistory);
        if (contractHistory.getId() != null) {
            throw new BadRequestAlertException("A new contractHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractHistory result = contractHistoryService.save(contractHistory);
        return ResponseEntity
            .created(new URI("/api/contract-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-histories/:id} : Updates an existing contractHistory.
     *
     * @param id the id of the contractHistory to save.
     * @param contractHistory the contractHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractHistory,
     * or with status {@code 400 (Bad Request)} if the contractHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-histories/{id}")
    public ResponseEntity<ContractHistory> updateContractHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractHistory contractHistory
    ) throws URISyntaxException {
        log.debug("REST request to update ContractHistory : {}, {}", id, contractHistory);
        if (contractHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractHistory result = contractHistoryService.update(contractHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-histories/:id} : Partial updates given fields of an existing contractHistory, field will ignore if it is null
     *
     * @param id the id of the contractHistory to save.
     * @param contractHistory the contractHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractHistory,
     * or with status {@code 400 (Bad Request)} if the contractHistory is not valid,
     * or with status {@code 404 (Not Found)} if the contractHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractHistory> partialUpdateContractHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractHistory contractHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractHistory partially : {}, {}", id, contractHistory);
        if (contractHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractHistory> result = contractHistoryService.partialUpdate(contractHistory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-histories} : get all the contractHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractHistories in body.
     */
    @GetMapping("/contract-histories")
    public List<ContractHistory> getAllContractHistories() {
        log.debug("REST request to get all ContractHistories");
        return contractHistoryService.findAll();
    }

    /**
     * {@code GET  /contract-histories/:id} : get the "id" contractHistory.
     *
     * @param id the id of the contractHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-histories/{id}")
    public ResponseEntity<ContractHistory> getContractHistory(@PathVariable Long id) {
        log.debug("REST request to get ContractHistory : {}", id);
        Optional<ContractHistory> contractHistory = contractHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractHistory);
    }

    /**
     * {@code DELETE  /contract-histories/:id} : delete the "id" contractHistory.
     *
     * @param id the id of the contractHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-histories/{id}")
    public ResponseEntity<Void> deleteContractHistory(@PathVariable Long id) {
        log.debug("REST request to delete ContractHistory : {}", id);
        contractHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
