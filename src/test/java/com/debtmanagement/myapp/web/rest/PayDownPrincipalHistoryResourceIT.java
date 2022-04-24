package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.PayDownPrincipalHistory;
import com.debtmanagement.myapp.repository.PayDownPrincipalHistoryRepository;
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
 * Integration tests for the {@link PayDownPrincipalHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayDownPrincipalHistoryResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final Double DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT = 1D;
    private static final Double UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT = 2D;

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pay-down-principal-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayDownPrincipalHistoryRepository payDownPrincipalHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayDownPrincipalHistoryMockMvc;

    private PayDownPrincipalHistory payDownPrincipalHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayDownPrincipalHistory createEntity(EntityManager em) {
        PayDownPrincipalHistory payDownPrincipalHistory = new PayDownPrincipalHistory()
            .contractId(DEFAULT_CONTRACT_ID)
            .payDownPrincipalAmount(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(DEFAULT_PAYER_NAME);
        return payDownPrincipalHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayDownPrincipalHistory createUpdatedEntity(EntityManager em) {
        PayDownPrincipalHistory payDownPrincipalHistory = new PayDownPrincipalHistory()
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME);
        return payDownPrincipalHistory;
    }

    @BeforeEach
    public void initTest() {
        payDownPrincipalHistory = createEntity(em);
    }

    @Test
    @Transactional
    void createPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeCreate = payDownPrincipalHistoryRepository.findAll().size();
        // Create the PayDownPrincipalHistory
        restPayDownPrincipalHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isCreated());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        PayDownPrincipalHistory testPayDownPrincipalHistory = payDownPrincipalHistoryList.get(payDownPrincipalHistoryList.size() - 1);
        assertThat(testPayDownPrincipalHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testPayDownPrincipalHistory.getPayDownPrincipalAmount()).isEqualTo(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipalHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
    }

    @Test
    @Transactional
    void createPayDownPrincipalHistoryWithExistingId() throws Exception {
        // Create the PayDownPrincipalHistory with an existing ID
        payDownPrincipalHistory.setId(1L);

        int databaseSizeBeforeCreate = payDownPrincipalHistoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayDownPrincipalHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = payDownPrincipalHistoryRepository.findAll().size();
        // set the field null
        payDownPrincipalHistory.setContractId(null);

        // Create the PayDownPrincipalHistory, which fails.

        restPayDownPrincipalHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPayDownPrincipalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = payDownPrincipalHistoryRepository.findAll().size();
        // set the field null
        payDownPrincipalHistory.setPayDownPrincipalAmount(null);

        // Create the PayDownPrincipalHistory, which fails.

        restPayDownPrincipalHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPayDownPrincipalHistories() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        // Get all the payDownPrincipalHistoryList
        restPayDownPrincipalHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payDownPrincipalHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].payDownPrincipalAmount").value(hasItem(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)));
    }

    @Test
    @Transactional
    void getPayDownPrincipalHistory() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        // Get the payDownPrincipalHistory
        restPayDownPrincipalHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, payDownPrincipalHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payDownPrincipalHistory.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.payDownPrincipalAmount").value(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME));
    }

    @Test
    @Transactional
    void getNonExistingPayDownPrincipalHistory() throws Exception {
        // Get the payDownPrincipalHistory
        restPayDownPrincipalHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPayDownPrincipalHistory() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();

        // Update the payDownPrincipalHistory
        PayDownPrincipalHistory updatedPayDownPrincipalHistory = payDownPrincipalHistoryRepository
            .findById(payDownPrincipalHistory.getId())
            .get();
        // Disconnect from session so that the updates on updatedPayDownPrincipalHistory are not directly saved in db
        em.detach(updatedPayDownPrincipalHistory);
        updatedPayDownPrincipalHistory
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME);

        restPayDownPrincipalHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPayDownPrincipalHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPayDownPrincipalHistory))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipalHistory testPayDownPrincipalHistory = payDownPrincipalHistoryList.get(payDownPrincipalHistoryList.size() - 1);
        assertThat(testPayDownPrincipalHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testPayDownPrincipalHistory.getPayDownPrincipalAmount()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipalHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
    }

    @Test
    @Transactional
    void putNonExistingPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payDownPrincipalHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayDownPrincipalHistoryWithPatch() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();

        // Update the payDownPrincipalHistory using partial update
        PayDownPrincipalHistory partialUpdatedPayDownPrincipalHistory = new PayDownPrincipalHistory();
        partialUpdatedPayDownPrincipalHistory.setId(payDownPrincipalHistory.getId());

        restPayDownPrincipalHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayDownPrincipalHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayDownPrincipalHistory))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipalHistory testPayDownPrincipalHistory = payDownPrincipalHistoryList.get(payDownPrincipalHistoryList.size() - 1);
        assertThat(testPayDownPrincipalHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testPayDownPrincipalHistory.getPayDownPrincipalAmount()).isEqualTo(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipalHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
    }

    @Test
    @Transactional
    void fullUpdatePayDownPrincipalHistoryWithPatch() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();

        // Update the payDownPrincipalHistory using partial update
        PayDownPrincipalHistory partialUpdatedPayDownPrincipalHistory = new PayDownPrincipalHistory();
        partialUpdatedPayDownPrincipalHistory.setId(payDownPrincipalHistory.getId());

        partialUpdatedPayDownPrincipalHistory
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME);

        restPayDownPrincipalHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayDownPrincipalHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayDownPrincipalHistory))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipalHistory testPayDownPrincipalHistory = payDownPrincipalHistoryList.get(payDownPrincipalHistoryList.size() - 1);
        assertThat(testPayDownPrincipalHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testPayDownPrincipalHistory.getPayDownPrincipalAmount()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipalHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payDownPrincipalHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayDownPrincipalHistory() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalHistoryRepository.findAll().size();
        payDownPrincipalHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipalHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayDownPrincipalHistory in the database
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayDownPrincipalHistory() throws Exception {
        // Initialize the database
        payDownPrincipalHistoryRepository.saveAndFlush(payDownPrincipalHistory);

        int databaseSizeBeforeDelete = payDownPrincipalHistoryRepository.findAll().size();

        // Delete the payDownPrincipalHistory
        restPayDownPrincipalHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, payDownPrincipalHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayDownPrincipalHistory> payDownPrincipalHistoryList = payDownPrincipalHistoryRepository.findAll();
        assertThat(payDownPrincipalHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
