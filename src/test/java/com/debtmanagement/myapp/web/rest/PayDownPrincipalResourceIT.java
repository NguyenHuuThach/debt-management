package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.PayDownPrincipal;
import com.debtmanagement.myapp.repository.PayDownPrincipalRepository;
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
 * Integration tests for the {@link PayDownPrincipalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayDownPrincipalResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final Instant DEFAULT_PAY_DOWN_PRINCIPAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAY_DOWN_PRINCIPAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT = 1D;
    private static final Double UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT = 2D;

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_CREATE = "AAAAAAAAAA";
    private static final String UPDATED_USER_CREATE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pay-down-principals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayDownPrincipalRepository payDownPrincipalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayDownPrincipalMockMvc;

    private PayDownPrincipal payDownPrincipal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayDownPrincipal createEntity(EntityManager em) {
        PayDownPrincipal payDownPrincipal = new PayDownPrincipal()
            .contractId(DEFAULT_CONTRACT_ID)
            .payDownPrincipalDate(DEFAULT_PAY_DOWN_PRINCIPAL_DATE)
            .payDownPrincipalAmount(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(DEFAULT_PAYER_NAME)
            .userCreate(DEFAULT_USER_CREATE)
            .note(DEFAULT_NOTE);
        return payDownPrincipal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayDownPrincipal createUpdatedEntity(EntityManager em) {
        PayDownPrincipal payDownPrincipal = new PayDownPrincipal()
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalDate(UPDATED_PAY_DOWN_PRINCIPAL_DATE)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE);
        return payDownPrincipal;
    }

    @BeforeEach
    public void initTest() {
        payDownPrincipal = createEntity(em);
    }

    @Test
    @Transactional
    void createPayDownPrincipal() throws Exception {
        int databaseSizeBeforeCreate = payDownPrincipalRepository.findAll().size();
        // Create the PayDownPrincipal
        restPayDownPrincipalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isCreated());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeCreate + 1);
        PayDownPrincipal testPayDownPrincipal = payDownPrincipalList.get(payDownPrincipalList.size() - 1);
        assertThat(testPayDownPrincipal.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testPayDownPrincipal.getPayDownPrincipalDate()).isEqualTo(DEFAULT_PAY_DOWN_PRINCIPAL_DATE);
        assertThat(testPayDownPrincipal.getPayDownPrincipalAmount()).isEqualTo(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipal.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testPayDownPrincipal.getUserCreate()).isEqualTo(DEFAULT_USER_CREATE);
        assertThat(testPayDownPrincipal.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void createPayDownPrincipalWithExistingId() throws Exception {
        // Create the PayDownPrincipal with an existing ID
        payDownPrincipal.setId(1L);

        int databaseSizeBeforeCreate = payDownPrincipalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayDownPrincipalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = payDownPrincipalRepository.findAll().size();
        // set the field null
        payDownPrincipal.setContractId(null);

        // Create the PayDownPrincipal, which fails.

        restPayDownPrincipalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPayDownPrincipalDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = payDownPrincipalRepository.findAll().size();
        // set the field null
        payDownPrincipal.setPayDownPrincipalDate(null);

        // Create the PayDownPrincipal, which fails.

        restPayDownPrincipalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPayDownPrincipalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = payDownPrincipalRepository.findAll().size();
        // set the field null
        payDownPrincipal.setPayDownPrincipalAmount(null);

        // Create the PayDownPrincipal, which fails.

        restPayDownPrincipalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPayDownPrincipals() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        // Get all the payDownPrincipalList
        restPayDownPrincipalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payDownPrincipal.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].payDownPrincipalDate").value(hasItem(DEFAULT_PAY_DOWN_PRINCIPAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].payDownPrincipalAmount").value(hasItem(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].userCreate").value(hasItem(DEFAULT_USER_CREATE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }

    @Test
    @Transactional
    void getPayDownPrincipal() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        // Get the payDownPrincipal
        restPayDownPrincipalMockMvc
            .perform(get(ENTITY_API_URL_ID, payDownPrincipal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payDownPrincipal.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.payDownPrincipalDate").value(DEFAULT_PAY_DOWN_PRINCIPAL_DATE.toString()))
            .andExpect(jsonPath("$.payDownPrincipalAmount").value(DEFAULT_PAY_DOWN_PRINCIPAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.userCreate").value(DEFAULT_USER_CREATE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    void getNonExistingPayDownPrincipal() throws Exception {
        // Get the payDownPrincipal
        restPayDownPrincipalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPayDownPrincipal() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();

        // Update the payDownPrincipal
        PayDownPrincipal updatedPayDownPrincipal = payDownPrincipalRepository.findById(payDownPrincipal.getId()).get();
        // Disconnect from session so that the updates on updatedPayDownPrincipal are not directly saved in db
        em.detach(updatedPayDownPrincipal);
        updatedPayDownPrincipal
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalDate(UPDATED_PAY_DOWN_PRINCIPAL_DATE)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE);

        restPayDownPrincipalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPayDownPrincipal.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPayDownPrincipal))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipal testPayDownPrincipal = payDownPrincipalList.get(payDownPrincipalList.size() - 1);
        assertThat(testPayDownPrincipal.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testPayDownPrincipal.getPayDownPrincipalDate()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_DATE);
        assertThat(testPayDownPrincipal.getPayDownPrincipalAmount()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipal.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayDownPrincipal.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testPayDownPrincipal.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void putNonExistingPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payDownPrincipal.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayDownPrincipalWithPatch() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();

        // Update the payDownPrincipal using partial update
        PayDownPrincipal partialUpdatedPayDownPrincipal = new PayDownPrincipal();
        partialUpdatedPayDownPrincipal.setId(payDownPrincipal.getId());

        partialUpdatedPayDownPrincipal
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .userCreate(UPDATED_USER_CREATE);

        restPayDownPrincipalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayDownPrincipal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayDownPrincipal))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipal testPayDownPrincipal = payDownPrincipalList.get(payDownPrincipalList.size() - 1);
        assertThat(testPayDownPrincipal.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testPayDownPrincipal.getPayDownPrincipalDate()).isEqualTo(DEFAULT_PAY_DOWN_PRINCIPAL_DATE);
        assertThat(testPayDownPrincipal.getPayDownPrincipalAmount()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipal.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayDownPrincipal.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testPayDownPrincipal.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void fullUpdatePayDownPrincipalWithPatch() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();

        // Update the payDownPrincipal using partial update
        PayDownPrincipal partialUpdatedPayDownPrincipal = new PayDownPrincipal();
        partialUpdatedPayDownPrincipal.setId(payDownPrincipal.getId());

        partialUpdatedPayDownPrincipal
            .contractId(UPDATED_CONTRACT_ID)
            .payDownPrincipalDate(UPDATED_PAY_DOWN_PRINCIPAL_DATE)
            .payDownPrincipalAmount(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT)
            .payerName(UPDATED_PAYER_NAME)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE);

        restPayDownPrincipalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayDownPrincipal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayDownPrincipal))
            )
            .andExpect(status().isOk());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
        PayDownPrincipal testPayDownPrincipal = payDownPrincipalList.get(payDownPrincipalList.size() - 1);
        assertThat(testPayDownPrincipal.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testPayDownPrincipal.getPayDownPrincipalDate()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_DATE);
        assertThat(testPayDownPrincipal.getPayDownPrincipalAmount()).isEqualTo(UPDATED_PAY_DOWN_PRINCIPAL_AMOUNT);
        assertThat(testPayDownPrincipal.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayDownPrincipal.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testPayDownPrincipal.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void patchNonExistingPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payDownPrincipal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayDownPrincipal() throws Exception {
        int databaseSizeBeforeUpdate = payDownPrincipalRepository.findAll().size();
        payDownPrincipal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayDownPrincipalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payDownPrincipal))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayDownPrincipal in the database
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayDownPrincipal() throws Exception {
        // Initialize the database
        payDownPrincipalRepository.saveAndFlush(payDownPrincipal);

        int databaseSizeBeforeDelete = payDownPrincipalRepository.findAll().size();

        // Delete the payDownPrincipal
        restPayDownPrincipalMockMvc
            .perform(delete(ENTITY_API_URL_ID, payDownPrincipal.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayDownPrincipal> payDownPrincipalList = payDownPrincipalRepository.findAll();
        assertThat(payDownPrincipalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
