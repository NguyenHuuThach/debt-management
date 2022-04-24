package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.ContractSettlement;
import com.debtmanagement.myapp.repository.ContractSettlementRepository;
import com.debtmanagement.myapp.service.ContractSettlementService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.ContractSettlement}.
 */
@RestController
@RequestMapping("/api")
public class ContractSettlementResource {

    private final Logger log = LoggerFactory.getLogger(ContractSettlementResource.class);

    private static final String ENTITY_NAME = "contractSettlement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractSettlementService contractSettlementService;

    private final ContractSettlementRepository contractSettlementRepository;

    public ContractSettlementResource(
        ContractSettlementService contractSettlementService,
        ContractSettlementRepository contractSettlementRepository
    ) {
        this.contractSettlementService = contractSettlementService;
        this.contractSettlementRepository = contractSettlementRepository;
    }

    /**
     * {@code POST  /contract-settlements} : Create a new contractSettlement.
     *
     * @param contractSettlement the contractSettlement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractSettlement, or with status {@code 400 (Bad Request)} if the contractSettlement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-settlements")
    public ResponseEntity<ContractSettlement> createContractSettlement(@Valid @RequestBody ContractSettlement contractSettlement)
        throws URISyntaxException {
        log.debug("REST request to save ContractSettlement : {}", contractSettlement);
        if (contractSettlement.getId() != null) {
            throw new BadRequestAlertException("A new contractSettlement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractSettlement result = contractSettlementService.save(contractSettlement);
        return ResponseEntity
            .created(new URI("/api/contract-settlements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-settlements/:id} : Updates an existing contractSettlement.
     *
     * @param id the id of the contractSettlement to save.
     * @param contractSettlement the contractSettlement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSettlement,
     * or with status {@code 400 (Bad Request)} if the contractSettlement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractSettlement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-settlements/{id}")
    public ResponseEntity<ContractSettlement> updateContractSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractSettlement contractSettlement
    ) throws URISyntaxException {
        log.debug("REST request to update ContractSettlement : {}, {}", id, contractSettlement);
        if (contractSettlement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSettlement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSettlementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractSettlement result = contractSettlementService.update(contractSettlement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractSettlement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-settlements/:id} : Partial updates given fields of an existing contractSettlement, field will ignore if it is null
     *
     * @param id the id of the contractSettlement to save.
     * @param contractSettlement the contractSettlement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSettlement,
     * or with status {@code 400 (Bad Request)} if the contractSettlement is not valid,
     * or with status {@code 404 (Not Found)} if the contractSettlement is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractSettlement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-settlements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractSettlement> partialUpdateContractSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractSettlement contractSettlement
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractSettlement partially : {}, {}", id, contractSettlement);
        if (contractSettlement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSettlement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSettlementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractSettlement> result = contractSettlementService.partialUpdate(contractSettlement);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractSettlement.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-settlements} : get all the contractSettlements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractSettlements in body.
     */
    @GetMapping("/contract-settlements")
    public List<ContractSettlement> getAllContractSettlements() {
        log.debug("REST request to get all ContractSettlements");
        return contractSettlementService.findAll();
    }

    /**
     * {@code GET  /contract-settlements/:id} : get the "id" contractSettlement.
     *
     * @param id the id of the contractSettlement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractSettlement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-settlements/{id}")
    public ResponseEntity<ContractSettlement> getContractSettlement(@PathVariable Long id) {
        log.debug("REST request to get ContractSettlement : {}", id);
        Optional<ContractSettlement> contractSettlement = contractSettlementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractSettlement);
    }

    /**
     * {@code DELETE  /contract-settlements/:id} : delete the "id" contractSettlement.
     *
     * @param id the id of the contractSettlement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-settlements/{id}")
    public ResponseEntity<Void> deleteContractSettlement(@PathVariable Long id) {
        log.debug("REST request to delete ContractSettlement : {}", id);
        contractSettlementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
