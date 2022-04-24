package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.InterestPay;
import com.debtmanagement.myapp.repository.InterestPayRepository;
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
 * Integration tests for the {@link InterestPayResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InterestPayResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final Instant DEFAULT_INTEREST_PAY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INTEREST_PAY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_INTEREST_PAY_AMOUNT = 1D;
    private static final Double UPDATED_INTEREST_PAY_AMOUNT = 2D;

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String ENTITY_API_URL = "/api/interest-pays";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InterestPayRepository interestPayRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInterestPayMockMvc;

    private InterestPay interestPay;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPay createEntity(EntityManager em) {
        InterestPay interestPay = new InterestPay()
            .contractId(DEFAULT_CONTRACT_ID)
            .interestPayDate(DEFAULT_INTEREST_PAY_DATE)
            .interestPayAmount(DEFAULT_INTEREST_PAY_AMOUNT)
            .payerName(DEFAULT_PAYER_NAME)
            .note(DEFAULT_NOTE)
            .status(DEFAULT_STATUS);
        return interestPay;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterestPay createUpdatedEntity(EntityManager em) {
        InterestPay interestPay = new InterestPay()
            .contractId(UPDATED_CONTRACT_ID)
            .interestPayDate(UPDATED_INTEREST_PAY_DATE)
            .interestPayAmount(UPDATED_INTEREST_PAY_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);
        return interestPay;
    }

    @BeforeEach
    public void initTest() {
        interestPay = createEntity(em);
    }

    @Test
    @Transactional
    void createInterestPay() throws Exception {
        int databaseSizeBeforeCreate = interestPayRepository.findAll().size();
        // Create the InterestPay
        restInterestPayMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isCreated());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeCreate + 1);
        InterestPay testInterestPay = interestPayList.get(interestPayList.size() - 1);
        assertThat(testInterestPay.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPay.getInterestPayDate()).isEqualTo(DEFAULT_INTEREST_PAY_DATE);
        assertThat(testInterestPay.getInterestPayAmount()).isEqualTo(DEFAULT_INTEREST_PAY_AMOUNT);
        assertThat(testInterestPay.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testInterestPay.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testInterestPay.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createInterestPayWithExistingId() throws Exception {
        // Create the InterestPay with an existing ID
        interestPay.setId(1L);

        int databaseSizeBeforeCreate = interestPayRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterestPayMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isBadRequest());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPayRepository.findAll().size();
        // set the field null
        interestPay.setContractId(null);

        // Create the InterestPay, which fails.

        restInterestPayMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isBadRequest());

        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPayDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPayRepository.findAll().size();
        // set the field null
        interestPay.setInterestPayDate(null);

        // Create the InterestPay, which fails.

        restInterestPayMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isBadRequest());

        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPayAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = interestPayRepository.findAll().size();
        // set the field null
        interestPay.setInterestPayAmount(null);

        // Create the InterestPay, which fails.

        restInterestPayMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isBadRequest());

        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInterestPays() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        // Get all the interestPayList
        restInterestPayMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interestPay.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].interestPayDate").value(hasItem(DEFAULT_INTEREST_PAY_DATE.toString())))
            .andExpect(jsonPath("$.[*].interestPayAmount").value(hasItem(DEFAULT_INTEREST_PAY_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }

    @Test
    @Transactional
    void getInterestPay() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        // Get the interestPay
        restInterestPayMockMvc
            .perform(get(ENTITY_API_URL_ID, interestPay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interestPay.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.interestPayDate").value(DEFAULT_INTEREST_PAY_DATE.toString()))
            .andExpect(jsonPath("$.interestPayAmount").value(DEFAULT_INTEREST_PAY_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingInterestPay() throws Exception {
        // Get the interestPay
        restInterestPayMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInterestPay() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();

        // Update the interestPay
        InterestPay updatedInterestPay = interestPayRepository.findById(interestPay.getId()).get();
        // Disconnect from session so that the updates on updatedInterestPay are not directly saved in db
        em.detach(updatedInterestPay);
        updatedInterestPay
            .contractId(UPDATED_CONTRACT_ID)
            .interestPayDate(UPDATED_INTEREST_PAY_DATE)
            .interestPayAmount(UPDATED_INTEREST_PAY_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);

        restInterestPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInterestPay.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInterestPay))
            )
            .andExpect(status().isOk());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
        InterestPay testInterestPay = interestPayList.get(interestPayList.size() - 1);
        assertThat(testInterestPay.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPay.getInterestPayDate()).isEqualTo(UPDATED_INTEREST_PAY_DATE);
        assertThat(testInterestPay.getInterestPayAmount()).isEqualTo(UPDATED_INTEREST_PAY_AMOUNT);
        assertThat(testInterestPay.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPay.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testInterestPay.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interestPay.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPay))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interestPay))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interestPay)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInterestPayWithPatch() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();

        // Update the interestPay using partial update
        InterestPay partialUpdatedInterestPay = new InterestPay();
        partialUpdatedInterestPay.setId(interestPay.getId());

        partialUpdatedInterestPay.interestPayDate(UPDATED_INTEREST_PAY_DATE).payerName(UPDATED_PAYER_NAME).status(UPDATED_STATUS);

        restInterestPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPay.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPay))
            )
            .andExpect(status().isOk());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
        InterestPay testInterestPay = interestPayList.get(interestPayList.size() - 1);
        assertThat(testInterestPay.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testInterestPay.getInterestPayDate()).isEqualTo(UPDATED_INTEREST_PAY_DATE);
        assertThat(testInterestPay.getInterestPayAmount()).isEqualTo(DEFAULT_INTEREST_PAY_AMOUNT);
        assertThat(testInterestPay.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPay.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testInterestPay.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateInterestPayWithPatch() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();

        // Update the interestPay using partial update
        InterestPay partialUpdatedInterestPay = new InterestPay();
        partialUpdatedInterestPay.setId(interestPay.getId());

        partialUpdatedInterestPay
            .contractId(UPDATED_CONTRACT_ID)
            .interestPayDate(UPDATED_INTEREST_PAY_DATE)
            .interestPayAmount(UPDATED_INTEREST_PAY_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);

        restInterestPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterestPay.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterestPay))
            )
            .andExpect(status().isOk());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
        InterestPay testInterestPay = interestPayList.get(interestPayList.size() - 1);
        assertThat(testInterestPay.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testInterestPay.getInterestPayDate()).isEqualTo(UPDATED_INTEREST_PAY_DATE);
        assertThat(testInterestPay.getInterestPayAmount()).isEqualTo(UPDATED_INTEREST_PAY_AMOUNT);
        assertThat(testInterestPay.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testInterestPay.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testInterestPay.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, interestPay.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPay))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interestPay))
            )
            .andExpect(status().isBadRequest());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInterestPay() throws Exception {
        int databaseSizeBeforeUpdate = interestPayRepository.findAll().size();
        interestPay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestPayMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(interestPay))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InterestPay in the database
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInterestPay() throws Exception {
        // Initialize the database
        interestPayRepository.saveAndFlush(interestPay);

        int databaseSizeBeforeDelete = interestPayRepository.findAll().size();

        // Delete the interestPay
        restInterestPayMockMvc
            .perform(delete(ENTITY_API_URL_ID, interestPay.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InterestPay> interestPayList = interestPayRepository.findAll();
        assertThat(interestPayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
