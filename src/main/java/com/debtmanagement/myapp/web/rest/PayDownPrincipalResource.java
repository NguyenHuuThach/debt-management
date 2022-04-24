package com.debtmanagement.myapp.web.rest;

import com.debtmanagement.myapp.domain.PayDownPrincipal;
import com.debtmanagement.myapp.repository.PayDownPrincipalRepository;
import com.debtmanagement.myapp.service.PayDownPrincipalService;
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
 * REST controller for managing {@link com.debtmanagement.myapp.domain.PayDownPrincipal}.
 */
@RestController
@RequestMapping("/api")
public class PayDownPrincipalResource {

    private final Logger log = LoggerFactory.getLogger(PayDownPrincipalResource.class);

    private static final String ENTITY_NAME = "payDownPrincipal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayDownPrincipalService payDownPrincipalService;

    private final PayDownPrincipalRepository payDownPrincipalRepository;

    public PayDownPrincipalResource(
        PayDownPrincipalService payDownPrincipalService,
        PayDownPrincipalRepository payDownPrincipalRepository
    ) {
        this.payDownPrincipalService = payDownPrincipalService;
        this.payDownPrincipalRepository = payDownPrincipalRepository;
    }

    /**
     * {@code POST  /pay-down-principals} : Create a new payDownPrincipal.
     *
     * @param payDownPrincipal the payDownPrincipal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payDownPrincipal, or with status {@code 400 (Bad Request)} if the payDownPrincipal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pay-down-principals")
    public ResponseEntity<PayDownPrincipal> createPayDownPrincipal(@Valid @RequestBody PayDownPrincipal payDownPrincipal)
        throws URISyntaxException {
        log.debug("REST request to save PayDownPrincipal : {}", payDownPrincipal);
        if (payDownPrincipal.getId() != null) {
            throw new BadRequestAlertException("A new payDownPrincipal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayDownPrincipal result = payDownPrincipalService.save(payDownPrincipal);
        return ResponseEntity
            .created(new URI("/api/pay-down-principals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pay-down-principals/:id} : Updates an existing payDownPrincipal.
     *
     * @param id the id of the payDownPrincipal to save.
     * @param payDownPrincipal the payDownPrincipal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payDownPrincipal,
     * or with status {@code 400 (Bad Request)} if the payDownPrincipal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payDownPrincipal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pay-down-principals/{id}")
    public ResponseEntity<PayDownPrincipal> updatePayDownPrincipal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PayDownPrincipal payDownPrincipal
    ) throws URISyntaxException {
        log.debug("REST request to update PayDownPrincipal : {}, {}", id, payDownPrincipal);
        if (payDownPrincipal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payDownPrincipal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payDownPrincipalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PayDownPrincipal result = payDownPrincipalService.update(payDownPrincipal);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payDownPrincipal.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pay-down-principals/:id} : Partial updates given fields of an existing payDownPrincipal, field will ignore if it is null
     *
     * @param id the id of the payDownPrincipal to save.
     * @param payDownPrincipal the payDownPrincipal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payDownPrincipal,
     * or with status {@code 400 (Bad Request)} if the payDownPrincipal is not valid,
     * or with status {@code 404 (Not Found)} if the payDownPrincipal is not found,
     * or with status {@code 500 (Internal Server Error)} if the payDownPrincipal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pay-down-principals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PayDownPrincipal> partialUpdatePayDownPrincipal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PayDownPrincipal payDownPrincipal
    ) throws URISyntaxException {
        log.debug("REST request to partial update PayDownPrincipal partially : {}, {}", id, payDownPrincipal);
        if (payDownPrincipal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payDownPrincipal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payDownPrincipalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayDownPrincipal> result = payDownPrincipalService.partialUpdate(payDownPrincipal);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payDownPrincipal.getId().toString())
        );
    }

    /**
     * {@code GET  /pay-down-principals} : get all the payDownPrincipals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payDownPrincipals in body.
     */
    @GetMapping("/pay-down-principals")
    public List<PayDownPrincipal> getAllPayDownPrincipals() {
        log.debug("REST request to get all PayDownPrincipals");
        return payDownPrincipalService.findAll();
    }

    /**
     * {@code GET  /pay-down-principals/:id} : get the "id" payDownPrincipal.
     *
     * @param id the id of the payDownPrincipal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payDownPrincipal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pay-down-principals/{id}")
    public ResponseEntity<PayDownPrincipal> getPayDownPrincipal(@PathVariable Long id) {
        log.debug("REST request to get PayDownPrincipal : {}", id);
        Optional<PayDownPrincipal> payDownPrincipal = payDownPrincipalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payDownPrincipal);
    }

    /**
     * {@code DELETE  /pay-down-principals/:id} : delete the "id" payDownPrincipal.
     *
     * @param id the id of the payDownPrincipal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pay-down-principals/{id}")
    public ResponseEntity<Void> deletePayDownPrincipal(@PathVariable Long id) {
        log.debug("REST request to delete PayDownPrincipal : {}", id);
        payDownPrincipalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
