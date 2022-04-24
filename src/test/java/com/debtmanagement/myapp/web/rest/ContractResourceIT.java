package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.Contract;
import com.debtmanagement.myapp.domain.enumeration.StatusContract;
import com.debtmanagement.myapp.repository.ContractRepository;
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
 * Integration tests for the {@link ContractResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractResourceIT {

    private static final Instant DEFAULT_DATE_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NUMBER_INTEREST_PAYMENTS = 1;
    private static final Integer UPDATED_NUMBER_INTEREST_PAYMENTS = 2;

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

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String ENTITY_API_URL = "/api/contracts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractMockMvc;

    private Contract contract;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contract createEntity(EntityManager em) {
        Contract contract = new Contract()
            .dateStart(DEFAULT_DATE_START)
            .numberInterestPayments(DEFAULT_NUMBER_INTEREST_PAYMENTS)
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
            .status(DEFAULT_STATUS)
            .isDeleted(DEFAULT_IS_DELETED);
        return contract;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contract createUpdatedEntity(EntityManager em) {
        Contract contract = new Contract()
            .dateStart(UPDATED_DATE_START)
            .numberInterestPayments(UPDATED_NUMBER_INTEREST_PAYMENTS)
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
            .status(UPDATED_STATUS)
            .isDeleted(UPDATED_IS_DELETED);
        return contract;
    }

    @BeforeEach
    public void initTest() {
        contract = createEntity(em);
    }

    @Test
    @Transactional
    void createContract() throws Exception {
        int databaseSizeBeforeCreate = contractRepository.findAll().size();
        // Create the Contract
        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isCreated());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate + 1);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testContract.getNumberInterestPayments()).isEqualTo(DEFAULT_NUMBER_INTEREST_PAYMENTS);
        assertThat(testContract.getTotalLoanAmount()).isEqualTo(DEFAULT_TOTAL_LOAN_AMOUNT);
        assertThat(testContract.getInterestPaymentPeriod()).isEqualTo(DEFAULT_INTEREST_PAYMENT_PERIOD);
        assertThat(testContract.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testContract.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testContract.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testContract.getCustomerPhoneNumber()).isEqualTo(DEFAULT_CUSTOMER_PHONE_NUMBER);
        assertThat(testContract.getCustomerIdentityCard()).isEqualTo(DEFAULT_CUSTOMER_IDENTITY_CARD);
        assertThat(testContract.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testContract.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testContract.getIcloud()).isEqualTo(DEFAULT_ICLOUD);
        assertThat(testContract.getUserCreate()).isEqualTo(DEFAULT_USER_CREATE);
        assertThat(testContract.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testContract.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContract.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    void createContractWithExistingId() throws Exception {
        // Create the Contract with an existing ID
        contract.setId(1L);

        int databaseSizeBeforeCreate = contractRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setDateStart(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberInterestPaymentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setNumberInterestPayments(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalLoanAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setTotalLoanAmount(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestPaymentPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setInterestPaymentPeriod(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterestRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setInterestRate(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setCustomerName(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setCustomerAddress(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setCustomerPhoneNumber(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerIdentityCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setCustomerIdentityCard(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractRepository.findAll().size();
        // set the field null
        contract.setProductName(null);

        // Create the Contract, which fails.

        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isBadRequest());

        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContracts() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList
        restContractMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contract.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateStart").value(hasItem(DEFAULT_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].numberInterestPayments").value(hasItem(DEFAULT_NUMBER_INTEREST_PAYMENTS)))
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
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    void getContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get the contract
        restContractMockMvc
            .perform(get(ENTITY_API_URL_ID, contract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contract.getId().intValue()))
            .andExpect(jsonPath("$.dateStart").value(DEFAULT_DATE_START.toString()))
            .andExpect(jsonPath("$.numberInterestPayments").value(DEFAULT_NUMBER_INTEREST_PAYMENTS))
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
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingContract() throws Exception {
        // Get the contract
        restContractMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract
        Contract updatedContract = contractRepository.findById(contract.getId()).get();
        // Disconnect from session so that the updates on updatedContract are not directly saved in db
        em.detach(updatedContract);
        updatedContract
            .dateStart(UPDATED_DATE_START)
            .numberInterestPayments(UPDATED_NUMBER_INTEREST_PAYMENTS)
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
            .status(UPDATED_STATUS)
            .isDeleted(UPDATED_IS_DELETED);

        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContract))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testContract.getNumberInterestPayments()).isEqualTo(UPDATED_NUMBER_INTEREST_PAYMENTS);
        assertThat(testContract.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContract.getInterestPaymentPeriod()).isEqualTo(UPDATED_INTEREST_PAYMENT_PERIOD);
        assertThat(testContract.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testContract.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testContract.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testContract.getCustomerPhoneNumber()).isEqualTo(UPDATED_CUSTOMER_PHONE_NUMBER);
        assertThat(testContract.getCustomerIdentityCard()).isEqualTo(UPDATED_CUSTOMER_IDENTITY_CARD);
        assertThat(testContract.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testContract.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testContract.getIcloud()).isEqualTo(UPDATED_ICLOUD);
        assertThat(testContract.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContract.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testContract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContract.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void putNonExistingContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contract))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contract))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractWithPatch() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract using partial update
        Contract partialUpdatedContract = new Contract();
        partialUpdatedContract.setId(contract.getId());

        partialUpdatedContract
            .numberInterestPayments(UPDATED_NUMBER_INTEREST_PAYMENTS)
            .totalLoanAmount(UPDATED_TOTAL_LOAN_AMOUNT)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerIdentityCard(UPDATED_CUSTOMER_IDENTITY_CARD)
            .icloud(UPDATED_ICLOUD)
            .userCreate(UPDATED_USER_CREATE)
            .note(UPDATED_NOTE)
            .isDeleted(UPDATED_IS_DELETED);

        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testContract.getNumberInterestPayments()).isEqualTo(UPDATED_NUMBER_INTEREST_PAYMENTS);
        assertThat(testContract.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContract.getInterestPaymentPeriod()).isEqualTo(DEFAULT_INTEREST_PAYMENT_PERIOD);
        assertThat(testContract.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testContract.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testContract.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testContract.getCustomerPhoneNumber()).isEqualTo(DEFAULT_CUSTOMER_PHONE_NUMBER);
        assertThat(testContract.getCustomerIdentityCard()).isEqualTo(UPDATED_CUSTOMER_IDENTITY_CARD);
        assertThat(testContract.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testContract.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testContract.getIcloud()).isEqualTo(UPDATED_ICLOUD);
        assertThat(testContract.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContract.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testContract.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContract.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void fullUpdateContractWithPatch() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract using partial update
        Contract partialUpdatedContract = new Contract();
        partialUpdatedContract.setId(contract.getId());

        partialUpdatedContract
            .dateStart(UPDATED_DATE_START)
            .numberInterestPayments(UPDATED_NUMBER_INTEREST_PAYMENTS)
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
            .status(UPDATED_STATUS)
            .isDeleted(UPDATED_IS_DELETED);

        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testContract.getNumberInterestPayments()).isEqualTo(UPDATED_NUMBER_INTEREST_PAYMENTS);
        assertThat(testContract.getTotalLoanAmount()).isEqualTo(UPDATED_TOTAL_LOAN_AMOUNT);
        assertThat(testContract.getInterestPaymentPeriod()).isEqualTo(UPDATED_INTEREST_PAYMENT_PERIOD);
        assertThat(testContract.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testContract.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testContract.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testContract.getCustomerPhoneNumber()).isEqualTo(UPDATED_CUSTOMER_PHONE_NUMBER);
        assertThat(testContract.getCustomerIdentityCard()).isEqualTo(UPDATED_CUSTOMER_IDENTITY_CARD);
        assertThat(testContract.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testContract.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testContract.getIcloud()).isEqualTo(UPDATED_ICLOUD);
        assertThat(testContract.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testContract.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testContract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContract.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void patchNonExistingContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contract))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contract))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeDelete = contractRepository.findAll().size();

        // Delete the contract
        restContractMockMvc
            .perform(delete(ENTITY_API_URL_ID, contract.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
