package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.InterestPay;
import com.debtmanagement.myapp.repository.InterestPayRepository;
import com.debtmanagement.myapp.service.InterestPayService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.InterestPay}.
 */
@RestController
@RequestMapping("/api")
public class InterestPayResource {

    private final Logger log = LoggerFactory.getLogger(InterestPayResource.class);

    private static final String ENTITY_NAME = "interestPay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterestPayService interestPayService;

    private final InterestPayRepository interestPayRepository;

    public InterestPayResource(InterestPayService interestPayService, InterestPayRepository interestPayRepository) {
        this.interestPayService = interestPayService;
        this.interestPayRepository = interestPayRepository;
    }

    /**
     * {@code POST  /interest-pays} : Create a new interestPay.
     *
     * @param interestPay the interestPay to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interestPay, or with status {@code 400 (Bad Request)} if the interestPay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interest-pays")
    public ResponseEntity<InterestPay> createInterestPay(@Valid @RequestBody InterestPay interestPay) throws URISyntaxException {
        log.debug("REST request to save InterestPay : {}", interestPay);
        if (interestPay.getId() != null) {
            throw new BadRequestAlertException("A new interestPay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterestPay result = interestPayService.save(interestPay);
        return ResponseEntity
            .created(new URI("/api/interest-pays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interest-pays/:id} : Updates an existing interestPay.
     *
     * @param id the id of the interestPay to save.
     * @param interestPay the interestPay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPay,
     * or with status {@code 400 (Bad Request)} if the interestPay is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interestPay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interest-pays/{id}")
    public ResponseEntity<InterestPay> updateInterestPay(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InterestPay interestPay
    ) throws URISyntaxException {
        log.debug("REST request to update InterestPay : {}, {}", id, interestPay);
        if (interestPay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPay.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPayRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InterestPay result = interestPayService.update(interestPay);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPay.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /interest-pays/:id} : Partial updates given fields of an existing interestPay, field will ignore if it is null
     *
     * @param id the id of the interestPay to save.
     * @param interestPay the interestPay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interestPay,
     * or with status {@code 400 (Bad Request)} if the interestPay is not valid,
     * or with status {@code 404 (Not Found)} if the interestPay is not found,
     * or with status {@code 500 (Internal Server Error)} if the interestPay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/interest-pays/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InterestPay> partialUpdateInterestPay(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InterestPay interestPay
    ) throws URISyntaxException {
        log.debug("REST request to partial update InterestPay partially : {}, {}", id, interestPay);
        if (interestPay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interestPay.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestPayRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InterestPay> result = interestPayService.partialUpdate(interestPay);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, interestPay.getId().toString())
        );
    }

    /**
     * {@code GET  /interest-pays} : get all the interestPays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interestPays in body.
     */
    @GetMapping("/interest-pays")
    public List<InterestPay> getAllInterestPays() {
        log.debug("REST request to get all InterestPays");
        return interestPayService.findAll();
    }

    /**
     * {@code GET  /interest-pays/:id} : get the "id" interestPay.
     *
     * @param id the id of the interestPay to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interestPay, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interest-pays/{id}")
    public ResponseEntity<InterestPay> getInterestPay(@PathVariable Long id) {
        log.debug("REST request to get InterestPay : {}", id);
        Optional<InterestPay> interestPay = interestPayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interestPay);
    }

    /**
     * {@code DELETE  /interest-pays/:id} : delete the "id" interestPay.
     *
     * @param id the id of the interestPay to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interest-pays/{id}")
    public ResponseEntity<Void> deleteInterestPay(@PathVariable Long id) {
        log.debug("REST request to delete InterestPay : {}", id);
        interestPayService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
