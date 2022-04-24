package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.InterestPaidHistory;
import com.debtmanagement.myapp.repository.InterestPaidHistoryRepository;
import com.debtmanagement.myapp.service.InterestPaidHistoryService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.InterestPaidHistory}.
 */
@RestController
@RequestMapping("/api")
public class InterestPaidHistoryResource {

    private final Logger log = LoggerFactory.getLogger(InterestPaidHistoryResource.class);

    private static final String ENTITY_NAME = "interestPaidHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterestPaidHistoryService interestPaidHistoryService;

    private final InterestPaidHistoryRepository interestPaidHistoryRepository;

    public InterestPaidHistoryResource(
        InterestPaidHistoryService interestPaidHistoryService,
        InterestPaidHistoryRepository interestPaidHistoryRepository
    ) {
        this.interestPaidHistoryService = interestPaidHistoryService;
        this.interestPaidHistoryRepository = interestPaidHistoryRepository;
    }

    /**
     * {@code POST  /interest-paid-histories} : Create a new interestPaidHistory.
     *
     * @param interestPaidHistory the interestPaidHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interestPaidHistory, or with status {@code 400 (Bad Request)} if the interestPaidHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interest-paid-histories")
    public ResponseEntity<InterestPaidHistory> createInterestPaidHistory(@Valid @RequestBody InterestPaidHistory interestPaidHistory)
        throws URISyntaxException {
        log.debug("REST request to save InterestPaidHistory : {}", interestPaidHistory);
        if (interestPaidHistory.getId() != null) {
            throw new BadRequestAlertException("A new interestPaidHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterestPaidHistory result = interestPaidHistoryService.save(interestPaidHistory);
        return ResponseEntity
            .created(new URI("/api/interest-paid-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interest-paid-histories/:id} : Updates an existing interestPaidHistory.
     *
     * @param id the id of the interestPaidHistory to save.
     * @param interestPaidHistory the interestPaidHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPaidHistory,
     * or with status {@code 400 (Bad Request)} if the interestPaidHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interestPaidHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interest-paid-histories/{id}")
    public ResponseEntity<InterestPaidHistory> updateInterestPaidHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InterestPaidHistory interestPaidHistory
    ) throws URISyntaxException {
        log.debug("REST request to update InterestPaidHistory : {}, {}", id, interestPaidHistory);
        if (interestPaidHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPaidHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPaidHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InterestPaidHistory result = interestPaidHistoryService.update(interestPaidHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPaidHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /interest-paid-histories/:id} : Partial updates given fields of an existing interestPaidHistory, field will ignore if it is null
     *
     * @param id the id of the interestPaidHistory to save.
     * @param interestPaidHistory the interestPaidHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPaidHistory,
     * or with status {@code 400 (Bad Request)} if the interestPaidHistory is not valid,
     * or with status {@code 404 (Not Found)} if the interestPaidHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the interestPaidHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/interest-paid-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InterestPaidHistory> partialUpdateInterestPaidHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InterestPaidHistory interestPaidHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update InterestPaidHistory partially : {}, {}", id, interestPaidHistory);
        if (interestPaidHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPaidHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPaidHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InterestPaidHistory> result = interestPaidHistoryService.partialUpdate(interestPaidHistory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPaidHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /interest-paid-histories} : get all the interestPaidHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interestPaidHistories in body.
     */
    @GetMapping("/interest-paid-histories")
    public List<InterestPaidHistory> getAllInterestPaidHistories() {
        log.debug("REST request to get all InterestPaidHistories");
        return interestPaidHistoryService.findAll();
    }

    /**
     * {@code GET  /interest-paid-histories/:id} : get the "id" interestPaidHistory.
     *
     * @param id the id of the interestPaidHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interestPaidHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interest-paid-histories/{id}")
    public ResponseEntity<InterestPaidHistory> getInterestPaidHistory(@PathVariable Long id) {
        log.debug("REST request to get InterestPaidHistory : {}", id);
        Optional<InterestPaidHistory> interestPaidHistory = interestPaidHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interestPaidHistory);
    }

    /**
     * {@code DELETE  /interest-paid-histories/:id} : delete the "id" interestPaidHistory.
     *
     * @param id the id of the interestPaidHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interest-paid-histories/{id}")
    public ResponseEntity<Void> deleteInterestPaidHistory(@PathVariable Long id) {
        log.debug("REST request to delete InterestPaidHistory : {}", id);
        interestPaidHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
