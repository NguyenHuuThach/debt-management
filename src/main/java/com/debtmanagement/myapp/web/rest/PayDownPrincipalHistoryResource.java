package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.PayDownPrincipalHistory;
import com.debtmanagement.myapp.repository.PayDownPrincipalHistoryRepository;
import com.debtmanagement.myapp.service.PayDownPrincipalHistoryService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.PayDownPrincipalHistory}.
 */
@RestController
@RequestMapping("/api")
public class PayDownPrincipalHistoryResource {

    private final Logger log = LoggerFactory.getLogger(PayDownPrincipalHistoryResource.class);

    private static final String ENTITY_NAME = "payDownPrincipalHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayDownPrincipalHistoryService payDownPrincipalHistoryService;

    private final PayDownPrincipalHistoryRepository payDownPrincipalHistoryRepository;

    public PayDownPrincipalHistoryResource(
        PayDownPrincipalHistoryService payDownPrincipalHistoryService,
        PayDownPrincipalHistoryRepository payDownPrincipalHistoryRepository
    ) {
        this.payDownPrincipalHistoryService = payDownPrincipalHistoryService;
        this.payDownPrincipalHistoryRepository = payDownPrincipalHistoryRepository;
    }

    /**
     * {@code POST  /pay-down-principal-histories} : Create a new payDownPrincipalHistory.
     *
     * @param payDownPrincipalHistory the payDownPrincipalHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payDownPrincipalHistory, or with status {@code 400 (Bad Request)} if the payDownPrincipalHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pay-down-principal-histories")
    public ResponseEntity<PayDownPrincipalHistory> createPayDownPrincipalHistory(
        @Valid @RequestBody PayDownPrincipalHistory payDownPrincipalHistory
    ) throws URISyntaxException {
        log.debug("REST request to save PayDownPrincipalHistory : {}", payDownPrincipalHistory);
        if (payDownPrincipalHistory.getId() != null) {
            throw new BadRequestAlertException("A new payDownPrincipalHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayDownPrincipalHistory result = payDownPrincipalHistoryService.save(payDownPrincipalHistory);
        return ResponseEntity
            .created(new URI("/api/pay-down-principal-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pay-down-principal-histories/:id} : Updates an existing payDownPrincipalHistory.
     *
     * @param id the id of the payDownPrincipalHistory to save.
     * @param payDownPrincipalHistory the payDownPrincipalHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payDownPrincipalHistory,
     * or with status {@code 400 (Bad Request)} if the payDownPrincipalHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payDownPrincipalHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pay-down-principal-histories/{id}")
    public ResponseEntity<PayDownPrincipalHistory> updatePayDownPrincipalHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PayDownPrincipalHistory payDownPrincipalHistory
    ) throws URISyntaxException {
        log.debug("REST request to update PayDownPrincipalHistory : {}, {}", id, payDownPrincipalHistory);
        if (payDownPrincipalHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payDownPrincipalHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payDownPrincipalHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PayDownPrincipalHistory result = payDownPrincipalHistoryService.update(payDownPrincipalHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payDownPrincipalHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pay-down-principal-histories/:id} : Partial updates given fields of an existing payDownPrincipalHistory, field will ignore if it is null
     *
     * @param id the id of the payDownPrincipalHistory to save.
     * @param payDownPrincipalHistory the payDownPrincipalHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payDownPrincipalHistory,
     * or with status {@code 400 (Bad Request)} if the payDownPrincipalHistory is not valid,
     * or with status {@code 404 (Not Found)} if the payDownPrincipalHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the payDownPrincipalHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pay-down-principal-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PayDownPrincipalHistory> partialUpdatePayDownPrincipalHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PayDownPrincipalHistory payDownPrincipalHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update PayDownPrincipalHistory partially : {}, {}", id, payDownPrincipalHistory);
        if (payDownPrincipalHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payDownPrincipalHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payDownPrincipalHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayDownPrincipalHistory> result = payDownPrincipalHistoryService.partialUpdate(payDownPrincipalHistory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payDownPrincipalHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /pay-down-principal-histories} : get all the payDownPrincipalHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payDownPrincipalHistories in body.
     */
    @GetMapping("/pay-down-principal-histories")
    public List<PayDownPrincipalHistory> getAllPayDownPrincipalHistories() {
        log.debug("REST request to get all PayDownPrincipalHistories");
        return payDownPrincipalHistoryService.findAll();
    }

    /**
     * {@code GET  /pay-down-principal-histories/:id} : get the "id" payDownPrincipalHistory.
     *
     * @param id the id of the payDownPrincipalHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payDownPrincipalHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pay-down-principal-histories/{id}")
    public ResponseEntity<PayDownPrincipalHistory> getPayDownPrincipalHistory(@PathVariable Long id) {
        log.debug("REST request to get PayDownPrincipalHistory : {}", id);
        Optional<PayDownPrincipalHistory> payDownPrincipalHistory = payDownPrincipalHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payDownPrincipalHistory);
    }

    /**
     * {@code DELETE  /pay-down-principal-histories/:id} : delete the "id" payDownPrincipalHistory.
     *
     * @param id the id of the payDownPrincipalHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pay-down-principal-histories/{id}")
    public ResponseEntity<Void> deletePayDownPrincipalHistory(@PathVariable Long id) {
        log.debug("REST request to delete PayDownPrincipalHistory : {}", id);
        payDownPrincipalHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
