package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.InterestPaid;
import com.debtmanagement.myapp.repository.InterestPaidRepository;
import com.debtmanagement.myapp.service.InterestPaidService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.InterestPaid}.
 */
@RestController
@RequestMapping("/api")
public class InterestPaidResource {

    private final Logger log = LoggerFactory.getLogger(InterestPaidResource.class);

    private static final String ENTITY_NAME = "interestPaid";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterestPaidService interestPaidService;

    private final InterestPaidRepository interestPaidRepository;

    public InterestPaidResource(InterestPaidService interestPaidService, InterestPaidRepository interestPaidRepository) {
        this.interestPaidService = interestPaidService;
        this.interestPaidRepository = interestPaidRepository;
    }

    /**
     * {@code POST  /interest-paids} : Create a new interestPaid.
     *
     * @param interestPaid the interestPaid to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interestPaid, or with status {@code 400 (Bad Request)} if the interestPaid has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interest-paids")
    public ResponseEntity<InterestPaid> createInterestPaid(@Valid @RequestBody InterestPaid interestPaid) throws URISyntaxException {
        log.debug("REST request to save InterestPaid : {}", interestPaid);
        if (interestPaid.getId() != null) {
            throw new BadRequestAlertException("A new interestPaid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterestPaid result = interestPaidService.save(interestPaid);
        return ResponseEntity
            .created(new URI("/api/interest-paids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interest-paids/:id} : Updates an existing interestPaid.
     *
     * @param id the id of the interestPaid to save.
     * @param interestPaid the interestPaid to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPaid,
     * or with status {@code 400 (Bad Request)} if the interestPaid is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interestPaid couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interest-paids/{id}")
    public ResponseEntity<InterestPaid> updateInterestPaid(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InterestPaid interestPaid
    ) throws URISyntaxException {
        log.debug("REST request to update InterestPaid : {}, {}", id, interestPaid);
        if (interestPaid.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPaid.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPaidRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InterestPaid result = interestPaidService.update(interestPaid);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPaid.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /interest-paids/:id} : Partial updates given fields of an existing interestPaid, field will ignore if it is null
     *
     * @param id the id of the interestPaid to save.
     * @param interestPaid the interestPaid to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPaid,
     * or with status {@code 400 (Bad Request)} if the interestPaid is not valid,
     * or with status {@code 404 (Not Found)} if the interestPaid is not found,
     * or with status {@code 500 (Internal Server Error)} if the interestPaid couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/interest-paids/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InterestPaid> partialUpdateInterestPaid(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InterestPaid interestPaid
    ) throws URISyntaxException {
        log.debug("REST request to partial update InterestPaid partially : {}, {}", id, interestPaid);
        if (interestPaid.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPaid.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPaidRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InterestPaid> result = interestPaidService.partialUpdate(interestPaid);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPaid.getId().toString())
        );
    }

    /**
     * {@code GET  /interest-paids} : get all the interestPaids.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interestPaids in body.
     */
    @GetMapping("/interest-paids")
    public List<InterestPaid> getAllInterestPaids() {
        log.debug("REST request to get all InterestPaids");
        return interestPaidService.findAll();
    }

    /**
     * {@code GET  /interest-paids/:id} : get the "id" interestPaid.
     *
     * @param id the id of the interestPaid to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interestPaid, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interest-paids/{id}")
    public ResponseEntity<InterestPaid> getInterestPaid(@PathVariable Long id) {
        log.debug("REST request to get InterestPaid : {}", id);
        Optional<InterestPaid> interestPaid = interestPaidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interestPaid);
    }

    /**
     * {@code DELETE  /interest-paids/:id} : delete the "id" interestPaid.
     *
     * @param id the id of the interestPaid to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interest-paids/{id}")
    public ResponseEntity<Void> deleteInterestPaid(@PathVariable Long id) {
        log.debug("REST request to delete InterestPaid : {}", id);
        interestPaidService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
