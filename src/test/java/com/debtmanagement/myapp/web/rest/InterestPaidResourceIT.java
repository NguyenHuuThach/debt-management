package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.InterestPaid;
import com.debtmanagement.myapp.repository.InterestPaidRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link InterestPaidResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InterestPaidResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final Instant DEFAULT_INTEREST_PAID_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INTEREST_PAID_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PAID_AMOUNT = 1D;
    private static final Double UPDATED_PAID_AMOUNT = 2D;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/interest-paids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InterestPaidRepository interestPaidRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInterestPaidMockMvc;

    private InterestPaid interestPaid;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPaid createEntity(EntityManager em) {
        InterestPaid interestPaid = new InterestPaid()
            .contractId(DEFAULT_CONTRACT_ID)
            .interestPaidDate(DEFAULT_INTEREST_PAID_DATE)
            .payerName(DEFAULT_PAYER_NAME)
            .paidAmount(DEFAULT_PAID_AMOUNT)
            .note(DEFAULT_NOTE);
        return interestPaid;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPaid createUpdatedEntity(EntityManager em) {
        InterestPaid interestPaid = new InterestPaid()
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);
        return interestPaid;
    }

    @BeforeEach
    public void initTest() {
        interestPaid = createEntity(em);
    }

    @Test
    @Transactional
    void createInterestPaid() throws Exception {
        int databaseSizeBeforeCreate = interestPaidRepository.findAll().size();
        // Create the InterestPaid
        restInterestPaidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isCreated());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeCreate + 1);
        InterestPaid testInterestPaid = interestPaidList.get(interestPaidList.size() - 1);
        assertThat(testInterestPaid.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPaid.getInterestPaidDate()).isEqualTo(DEFAULT_INTEREST_PAID_DATE);
        assertThat(testInterestPaid.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testInterestPaid.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testInterestPaid.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void createInterestPaidWithExistingId() throws Exception {
        // Create the InterestPaid with an existing ID
        interestPaid.setId(1L);

        int databaseSizeBeforeCreate = interestPaidRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterestPaidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isBadRequest());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidRepository.findAll().size();
        // set the field null
        interestPaid.setContractId(null);

        // Create the InterestPaid, which fails.

        restInterestPaidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isBadRequest());

        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPaidDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidRepository.findAll().size();
        // set the field null
        interestPaid.setInterestPaidDate(null);

        // Create the InterestPaid, which fails.

        restInterestPaidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isBadRequest());

        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaidAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidRepository.findAll().size();
        // set the field null
        interestPaid.setPaidAmount(null);

        // Create the InterestPaid, which fails.

        restInterestPaidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isBadRequest());

        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInterestPaids() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        // Get all the interestPaidList
        restInterestPaidMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interestPaid.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].interestPaidDate").value(hasItem(DEFAULT_INTEREST_PAID_DATE.toString())))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].paidAmount").value(hasItem(DEFAULT_PAID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }

    @Test
    @Transactional
    void getInterestPaid() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        // Get the interestPaid
        restInterestPaidMockMvc
            .perform(get(ENTITY_API_URL_ID, interestPaid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interestPaid.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.interestPaidDate").value(DEFAULT_INTEREST_PAID_DATE.toString()))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.paidAmount").value(DEFAULT_PAID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    void getNonExistingInterestPaid() throws Exception {
        // Get the interestPaid
        restInterestPaidMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInterestPaid() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();

        // Update the interestPaid
        InterestPaid updatedInterestPaid = interestPaidRepository.findById(interestPaid.getId()).get();
        // Disconnect from session so that the updates on updatedInterestPaid are not directly saved in db
        em.detach(updatedInterestPaid);
        updatedInterestPaid
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);

        restInterestPaidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInterestPaid.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInterestPaid))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
        InterestPaid testInterestPaid = interestPaidList.get(interestPaidList.size() - 1);
        assertThat(testInterestPaid.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPaid.getInterestPaidDate()).isEqualTo(UPDATED_INTEREST_PAID_DATE);
        assertThat(testInterestPaid.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPaid.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testInterestPaid.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void putNonExistingInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interestPaid.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPaid))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPaid))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaid)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInterestPaidWithPatch() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();

        // Update the interestPaid using partial update
        InterestPaid partialUpdatedInterestPaid = new InterestPaid();
        partialUpdatedInterestPaid.setId(interestPaid.getId());

        partialUpdatedInterestPaid.interestPaidDate(UPDATED_INTEREST_PAID_DATE).note(UPDATED_NOTE);

        restInterestPaidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPaid.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPaid))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
        InterestPaid testInterestPaid = interestPaidList.get(interestPaidList.size() - 1);
        assertThat(testInterestPaid.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPaid.getInterestPaidDate()).isEqualTo(UPDATED_INTEREST_PAID_DATE);
        assertThat(testInterestPaid.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testInterestPaid.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testInterestPaid.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void fullUpdateInterestPaidWithPatch() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();

        // Update the interestPaid using partial update
        InterestPaid partialUpdatedInterestPaid = new InterestPaid();
        partialUpdatedInterestPaid.setId(interestPaid.getId());

        partialUpdatedInterestPaid
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);

        restInterestPaidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPaid.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPaid))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
        InterestPaid testInterestPaid = interestPaidList.get(interestPaidList.size() - 1);
        assertThat(testInterestPaid.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPaid.getInterestPaidDate()).isEqualTo(UPDATED_INTEREST_PAID_DATE);
        assertThat(testInterestPaid.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPaid.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testInterestPaid.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void patchNonExistingInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, interestPaid.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPaid))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPaid))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInterestPaid() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidRepository.findAll().size();
        interestPaid.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(interestPaid))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPaid in the database
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInterestPaid() throws Exception {
        // Initialize the database
        interestPaidRepository.saveAndFlush(interestPaid);

        int databaseSizeBeforeDelete = interestPaidRepository.findAll().size();

        // Delete the interestPaid
        restInterestPaidMockMvc
            .perform(delete(ENTITY_API_URL_ID, interestPaid.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InterestPaid> interestPaidList = interestPaidRepository.findAll();
        assertThat(interestPaidList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
