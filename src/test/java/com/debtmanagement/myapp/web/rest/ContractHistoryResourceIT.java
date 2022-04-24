package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.ContractHistory;
import com.debtmanagement.myapp.domain.enumeration.StatusContract;
import com.debtmanagement.myapp.repository.ContractHistoryRepository;
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
 * Integration tests for the {@link ContractHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractHistoryResourceIT {

    private static final Instant DEFAULT_DATE_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SETTLEMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SETTLEMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_TOTAL_LOAN_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_LOAN_AMOUNT = 2D;

    private static final Integer DEFAULT_INTEREST_PAYMENT_PERIOD = 1;
    private static final Integer UPDATED_INTEREST_PAYMENT_PERIOD = 2;

    private static final Integer DEFAULT_INTEREST_RATE = 1;
    private static final Integer UPDATED_INTEREST_RATE = 2;

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_IDENTITY_CARD = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_IDENTITY_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMEI = "AAAAAAAAAA";
    private static final String UPDATED_IMEI = "BBBBBBBBBB";

    private static final String DEFAULT_ICLOUD = "AAAAAAAAAA";
    private static final String UPDATED_ICLOUD = "BBBBBBBBBB";

    private static final String DEFAULT_USER_CREATE = "AAAAAAAAAA";
    private static final String UPDATED_USER_CREATE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final StatusContract DEFAULT_STATUS = StatusContract.NORMAL;
    private static final StatusContract UPDATED_STATUS = StatusContract.GOOD;

    private static final String ENTITY_API_URL = "/api/contract-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractHistoryRepository contractHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractHistoryMockMvc;

    private ContractHistory contractHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractHistory createEntity(EntityManager em) {
        ContractHistory contractHistory = new ContractHistory()
            .dateStart(DEFAULT_DATE_START)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .totalLoanAmount(DEFAULT_TOTAL_LOAN_AMOUNT)
            .interestPaymentPeriod(DEFAULT_INTEREST_PAYMENT_PERIOD)
            .interestRate(DEFAULT_INTEREST_RATE)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerAddress(DEFAULT_CUSTOMER_ADDRESS)
            .customerPhoneNumber(DEFAULT_CUSTOMER_PHONE_NUMBER)
            .customerIdentityCard(DEFAULT_CUSTOMER_IDENTITY_CARD)
            .productName(DEFAULT_PRODUCT_NAME)
            .imei(DEFAULT_IMEI)
            .icloud(DEFAULT_ICLOUD)
            .userCreate(DEFAULT_USER_CREATE)
            .note(DEFAULT_NOTE)
            .status(DEFAULT_STATUS);
        return contractHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractHistory createUpdatedEntity(EntityManager em) {
        ContractHistory contractHistory = new ContractHistory()
            .dateStart(UPDATED_DATE_START)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .totalLoanAmount(UPDATED_TOTAL_LOAN_AMOUNT)
            .interestPaymentPeriod(UPDATED_INTEREST_PAYMENT_PERIOD)
            .interestRate(UPDATED_INTEREST_RATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerPhoneNumber(UPDATED_CUSTOMER_PHONE_NUMBER)
            .customerIdentityCard(UPDATED_CUSTOMER_IDENTITY_CARD)
            .productName(UPDATED_PRODUCT_NAME)
            .imei(UPDATED_IMEI)
            .icloud(UPDATED_ICLOUD)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);
        return contractHistory;
    }

    @BeforeEach
    public void initTest() {
        contractHistory = createEntity(em);
    }

    @Test
    @Transactional
    void createContractHistory() throws Exception {
        int databaseSizeBeforeCreate = contractHistoryRepository.findAll().size();
        // Create the ContractHistory
        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isCreated());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ContractHistory testContractHistory = contractHistoryList.get(contractHistoryList.size() - 1);
        assertThat(testContractHistory.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testContractHistory.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testContractHistory.getTotalLoanAmount()).isEqualTo(DEFAULT_TOTAL_LOAN_AMOUNT);
        assertThat(testContractHistory.getInterestPaymentPeriod()).isEqualTo(DEFAULT_INTEREST_PAYMENT_PERIOD);
        assertThat(testContractHistory.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testContractHistory.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testContractHistory.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testContractHistory.getCustomerPhoneNumber()).isEqualTo(DEFAULT_CUSTOMER_PHONE_NUMBER);
        assertThat(testContractHistory.getCustomerIdentityCard()).isEqualTo(DEFAULT_CUSTOMER_IDENTITY_CARD);
        assertThat(testContractHistory.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testContractHistory.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testContractHistory.getIcloud()).isEqualTo(DEFAULT_ICLOUD);
        assertThat(testContractHistory.getUserCreate()).isEqualTo(DEFAULT_USER_CREATE);
        assertThat(testContractHistory.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testContractHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createContractHistoryWithExistingId() throws Exception {
        // Create the ContractHistory with an existing ID
        contractHistory.setId(1L);

        int databaseSizeBeforeCreate = contractHistoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setDateStart(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalLoanAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setTotalLoanAmount(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPaymentPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setInterestPaymentPeriod(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setInterestRate(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setCustomerName(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setCustomerAddress(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setCustomerPhoneNumber(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerIdentityCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setCustomerIdentityCard(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractHistoryRepository.findAll().size();
        // set the field null
        contractHistory.setProductName(null);

        // Create the ContractHistory, which fails.

        restContractHistoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContractHistories() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        // Get all the contractHistoryList
        restContractHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateStart").value(hasItem(DEFAULT_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].settlementDate").value(hasItem(DEFAULT_SETTLEMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalLoanAmount").value(hasItem(DEFAULT_TOTAL_LOAN_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].interestPaymentPeriod").value(hasItem(DEFAULT_INTEREST_PAYMENT_PERIOD)))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(DEFAULT_INTEREST_RATE)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS)))
            .andExpect(jsonPath("$.[*].customerPhoneNumber").value(hasItem(DEFAULT_CUSTOMER_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].customerIdentityCard").value(hasItem(DEFAULT_CUSTOMER_IDENTITY_CARD)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].imei").value(hasItem(DEFAULT_IMEI)))
            .andExpect(jsonPath("$.[*].icloud").value(hasItem(DEFAULT_ICLOUD)))
            .andExpect(jsonPath("$.[*].userCreate").value(hasItem(DEFAULT_USER_CREATE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getContractHistory() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        // Get the contractHistory
        restContractHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, contractHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractHistory.getId().intValue()))
            .andExpect(jsonPath("$.dateStart").value(DEFAULT_DATE_START.toString()))
            .andExpect(jsonPath("$.settlementDate").value(DEFAULT_SETTLEMENT_DATE.toString()))
            .andExpect(jsonPath("$.totalLoanAmount").value(DEFAULT_TOTAL_LOAN_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.interestPaymentPeriod").value(DEFAULT_INTEREST_PAYMENT_PERIOD))
            .andExpect(jsonPath("$.interestRate").value(DEFAULT_INTEREST_RATE))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerAddress").value(DEFAULT_CUSTOMER_ADDRESS))
            .andExpect(jsonPath("$.customerPhoneNumber").value(DEFAULT_CUSTOMER_PHONE_NUMBER))
            .andExpect(jsonPath("$.customerIdentityCard").value(DEFAULT_CUSTOMER_IDENTITY_CARD))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.imei").value(DEFAULT_IMEI))
            .andExpect(jsonPath("$.icloud").value(DEFAULT_ICLOUD))
            .andExpect(jsonPath("$.userCreate").value(DEFAULT_USER_CREATE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingContractHistory() throws Exception {
        // Get the contractHistory
        restContractHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractHistory() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();

        // Update the contractHistory
        ContractHistory updatedContractHistory = contractHistoryRepository.findById(contractHistory.getId()).get();
        // Disconnect from session so that the updates on updatedContractHistory are not directly saved in db
        em.detach(updatedContractHistory);
        updatedContractHistory
            .dateStart(UPDATED_DATE_START)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .totalLoanAmount(UPDATED_TOTAL_LOAN_AMOUNT)
            .interestPaymentPeriod(UPDATED_INTEREST_PAYMENT_PERIOD)
            .interestRate(UPDATED_INTEREST_RATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerPhoneNumber(UPDATED_CUSTOMER_PHONE_NUMBER)
            .customerIdentityCard(UPDATED_CUSTOMER_IDENTITY_CARD)
            .productName(UPDATED_PRODUCT_NAME)
            .imei(UPDATED_IMEI)
            .icloud(UPDATED_ICLOUD)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);

        restContractHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContractHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractHistory testContractHistory = contractHistoryList.get(contractHistoryList.size() - 1);
        assertThat(testContractHistory.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testContractHistory.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testContractHistory.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContractHistory.getInterestPaymentPeriod()).isEqualTo(UPDATED_INTEREST_PAYMENT_PERIOD);
        assertThat(testContractHistory.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testContractHistory.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testContractHistory.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testContractHistory.getCustomerPhoneNumber()).isEqualTo(UPDATED_CUSTOMER_PHONE_NUMBER);
        assertThat(testContractHistory.getCustomerIdentityCard()).isEqualTo(UPDATED_CUSTOMER_IDENTITY_CARD);
        assertThat(testContractHistory.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testContractHistory.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testContractHistory.getIcloud()).isEqualTo(UPDATED_ICLOUD);
        assertThat(testContractHistory.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContractHistory.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testContractHistory.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractHistoryWithPatch() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();

        // Update the contractHistory using partial update
        ContractHistory partialUpdatedContractHistory = new ContractHistory();
        partialUpdatedContractHistory.setId(contractHistory.getId());

        partialUpdatedContractHistory
            .totalLoanAmount(UPDATED_TOTAL_LOAN_AMOUNT)
            .interestPaymentPeriod(UPDATED_INTEREST_PAYMENT_PERIOD)
            .interestRate(UPDATED_INTEREST_RATE)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .userCreate(UPDATED_USER_CREATE);

        restContractHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractHistory testContractHistory = contractHistoryList.get(contractHistoryList.size() - 1);
        assertThat(testContractHistory.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testContractHistory.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testContractHistory.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContractHistory.getInterestPaymentPeriod()).isEqualTo(UPDATED_INTEREST_PAYMENT_PERIOD);
        assertThat(testContractHistory.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testContractHistory.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testContractHistory.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testContractHistory.getCustomerPhoneNumber()).isEqualTo(DEFAULT_CUSTOMER_PHONE_NUMBER);
        assertThat(testContractHistory.getCustomerIdentityCard()).isEqualTo(DEFAULT_CUSTOMER_IDENTITY_CARD);
        assertThat(testContractHistory.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testContractHistory.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testContractHistory.getIcloud()).isEqualTo(DEFAULT_ICLOUD);
        assertThat(testContractHistory.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContractHistory.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testContractHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateContractHistoryWithPatch() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();

        // Update the contractHistory using partial update
        ContractHistory partialUpdatedContractHistory = new ContractHistory();
        partialUpdatedContractHistory.setId(contractHistory.getId());

        partialUpdatedContractHistory
            .dateStart(UPDATED_DATE_START)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .totalLoanAmount(UPDATED_TOTAL_LOAN_AMOUNT)
            .interestPaymentPeriod(UPDATED_INTEREST_PAYMENT_PERIOD)
            .interestRate(UPDATED_INTEREST_RATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerPhoneNumber(UPDATED_CUSTOMER_PHONE_NUMBER)
            .customerIdentityCard(UPDATED_CUSTOMER_IDENTITY_CARD)
            .productName(UPDATED_PRODUCT_NAME)
            .imei(UPDATED_IMEI)
            .icloud(UPDATED_ICLOUD)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS);

        restContractHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractHistory testContractHistory = contractHistoryList.get(contractHistoryList.size() - 1);
        assertThat(testContractHistory.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testContractHistory.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testContractHistory.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContractHistory.getInterestPaymentPeriod()).isEqualTo(UPDATED_INTEREST_PAYMENT_PERIOD);
        assertThat(testContractHistory.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testContractHistory.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testContractHistory.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testContractHistory.getCustomerPhoneNumber()).isEqualTo(UPDATED_CUSTOMER_PHONE_NUMBER);
        assertThat(testContractHistory.getCustomerIdentityCard()).isEqualTo(UPDATED_CUSTOMER_IDENTITY_CARD);
        assertThat(testContractHistory.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testContractHistory.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testContractHistory.getIcloud()).isEqualTo(UPDATED_ICLOUD);
        assertThat(testContractHistory.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContractHistory.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testContractHistory.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractHistoryRepository.findAll().size();
        contractHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractHistory in the database
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractHistory() throws Exception {
        // Initialize the database
        contractHistoryRepository.saveAndFlush(contractHistory);

        int databaseSizeBeforeDelete = contractHistoryRepository.findAll().size();

        // Delete the contractHistory
        restContractHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractHistory> contractHistoryList = contractHistoryRepository.findAll();
        assertThat(contractHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
