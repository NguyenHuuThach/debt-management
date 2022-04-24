package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.InterestPaidHistory;
import com.debtmanagement.myapp.repository.InterestPaidHistoryRepository;
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
 * Integration tests for the {@link InterestPaidHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InterestPaidHistoryResourceIT {

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

    private static final String ENTITY_API_URL = "/api/interest-paid-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InterestPaidHistoryRepository interestPaidHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInterestPaidHistoryMockMvc;

    private InterestPaidHistory interestPaidHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPaidHistory createEntity(EntityManager em) {
        InterestPaidHistory interestPaidHistory = new InterestPaidHistory()
            .contractId(DEFAULT_CONTRACT_ID)
            .interestPaidDate(DEFAULT_INTEREST_PAID_DATE)
            .payerName(DEFAULT_PAYER_NAME)
            .paidAmount(DEFAULT_PAID_AMOUNT)
            .note(DEFAULT_NOTE);
        return interestPaidHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPaidHistory createUpdatedEntity(EntityManager em) {
        InterestPaidHistory interestPaidHistory = new InterestPaidHistory()
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);
        return interestPaidHistory;
    }

    @BeforeEach
    public void initTest() {
        interestPaidHistory = createEntity(em);
    }

    @Test
    @Transactional
    void createInterestPaidHistory() throws Exception {
        int databaseSizeBeforeCreate = interestPaidHistoryRepository.findAll().size();
        // Create the InterestPaidHistory
        restInterestPaidHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isCreated());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        InterestPaidHistory testInterestPaidHistory = interestPaidHistoryList.get(interestPaidHistoryList.size() - 1);
        assertThat(testInterestPaidHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPaidHistory.getInterestPaidDate()).isEqualTo(DEFAULT_INTEREST_PAID_DATE);
        assertThat(testInterestPaidHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testInterestPaidHistory.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testInterestPaidHistory.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void createInterestPaidHistoryWithExistingId() throws Exception {
        // Create the InterestPaidHistory with an existing ID
        interestPaidHistory.setId(1L);

        int databaseSizeBeforeCreate = interestPaidHistoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterestPaidHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidHistoryRepository.findAll().size();
        // set the field null
        interestPaidHistory.setContractId(null);

        // Create the InterestPaidHistory, which fails.

        restInterestPaidHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPaidDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidHistoryRepository.findAll().size();
        // set the field null
        interestPaidHistory.setInterestPaidDate(null);

        // Create the InterestPaidHistory, which fails.

        restInterestPaidHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaidAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPaidHistoryRepository.findAll().size();
        // set the field null
        interestPaidHistory.setPaidAmount(null);

        // Create the InterestPaidHistory, which fails.

        restInterestPaidHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInterestPaidHistories() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        // Get all the interestPaidHistoryList
        restInterestPaidHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interestPaidHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].interestPaidDate").value(hasItem(DEFAULT_INTEREST_PAID_DATE.toString())))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].paidAmount").value(hasItem(DEFAULT_PAID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }

    @Test
    @Transactional
    void getInterestPaidHistory() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        // Get the interestPaidHistory
        restInterestPaidHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, interestPaidHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interestPaidHistory.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.interestPaidDate").value(DEFAULT_INTEREST_PAID_DATE.toString()))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.paidAmount").value(DEFAULT_PAID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    void getNonExistingInterestPaidHistory() throws Exception {
        // Get the interestPaidHistory
        restInterestPaidHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInterestPaidHistory() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();

        // Update the interestPaidHistory
        InterestPaidHistory updatedInterestPaidHistory = interestPaidHistoryRepository.findById(interestPaidHistory.getId()).get();
        // Disconnect from session so that the updates on updatedInterestPaidHistory are not directly saved in db
        em.detach(updatedInterestPaidHistory);
        updatedInterestPaidHistory
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);

        restInterestPaidHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInterestPaidHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInterestPaidHistory))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
        InterestPaidHistory testInterestPaidHistory = interestPaidHistoryList.get(interestPaidHistoryList.size() - 1);
        assertThat(testInterestPaidHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPaidHistory.getInterestPaidDate()).isEqualTo(UPDATED_INTEREST_PAID_DATE);
        assertThat(testInterestPaidHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPaidHistory.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testInterestPaidHistory.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void putNonExistingInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interestPaidHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInterestPaidHistoryWithPatch() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();

        // Update the interestPaidHistory using partial update
        InterestPaidHistory partialUpdatedInterestPaidHistory = new InterestPaidHistory();
        partialUpdatedInterestPaidHistory.setId(interestPaidHistory.getId());

        restInterestPaidHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPaidHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPaidHistory))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
        InterestPaidHistory testInterestPaidHistory = interestPaidHistoryList.get(interestPaidHistoryList.size() - 1);
        assertThat(testInterestPaidHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPaidHistory.getInterestPaidDate()).isEqualTo(DEFAULT_INTEREST_PAID_DATE);
        assertThat(testInterestPaidHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testInterestPaidHistory.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testInterestPaidHistory.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void fullUpdateInterestPaidHistoryWithPatch() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();

        // Update the interestPaidHistory using partial update
        InterestPaidHistory partialUpdatedInterestPaidHistory = new InterestPaidHistory();
        partialUpdatedInterestPaidHistory.setId(interestPaidHistory.getId());

        partialUpdatedInterestPaidHistory
            .contractId(UPDATED_CONTRACT_ID)
            .interestPaidDate(UPDATED_INTEREST_PAID_DATE)
            .payerName(UPDATED_PAYER_NAME)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .note(UPDATED_NOTE);

        restInterestPaidHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPaidHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPaidHistory))
            )
            .andExpect(status().isOk());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
        InterestPaidHistory testInterestPaidHistory = interestPaidHistoryList.get(interestPaidHistoryList.size() - 1);
        assertThat(testInterestPaidHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPaidHistory.getInterestPaidDate()).isEqualTo(UPDATED_INTEREST_PAID_DATE);
        assertThat(testInterestPaidHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPaidHistory.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testInterestPaidHistory.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void patchNonExistingInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, interestPaidHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInterestPaidHistory() throws Exception {
        int databaseSizeBeforeUpdate = interestPaidHistoryRepository.findAll().size();
        interestPaidHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPaidHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPaidHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPaidHistory in the database
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInterestPaidHistory() throws Exception {
        // Initialize the database
        interestPaidHistoryRepository.saveAndFlush(interestPaidHistory);

        int databaseSizeBeforeDelete = interestPaidHistoryRepository.findAll().size();

        // Delete the interestPaidHistory
        restInterestPaidHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, interestPaidHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InterestPaidHistory> interestPaidHistoryList = interestPaidHistoryRepository.findAll();
        assertThat(interestPaidHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
