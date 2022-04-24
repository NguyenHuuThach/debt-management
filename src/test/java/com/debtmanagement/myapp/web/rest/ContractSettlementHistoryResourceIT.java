package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.ContractSettlementHistory;
import com.debtmanagement.myapp.repository.ContractSettlementHistoryRepository;
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
 * Integration tests for the {@link ContractSettlementHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractSettlementHistoryResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final String DEFAULT_SETTLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETTLER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contract-settlement-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractSettlementHistoryRepository contractSettlementHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractSettlementHistoryMockMvc;

    private ContractSettlementHistory contractSettlementHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSettlementHistory createEntity(EntityManager em) {
        ContractSettlementHistory contractSettlementHistory = new ContractSettlementHistory()
            .contractId(DEFAULT_CONTRACT_ID)
            .settlerName(DEFAULT_SETTLER_NAME);
        return contractSettlementHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSettlementHistory createUpdatedEntity(EntityManager em) {
        ContractSettlementHistory contractSettlementHistory = new ContractSettlementHistory()
            .contractId(UPDATED_CONTRACT_ID)
            .settlerName(UPDATED_SETTLER_NAME);
        return contractSettlementHistory;
    }

    @BeforeEach
    public void initTest() {
        contractSettlementHistory = createEntity(em);
    }

    @Test
    @Transactional
    void createContractSettlementHistory() throws Exception {
        int databaseSizeBeforeCreate = contractSettlementHistoryRepository.findAll().size();
        // Create the ContractSettlementHistory
        restContractSettlementHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isCreated());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ContractSettlementHistory testContractSettlementHistory = contractSettlementHistoryList.get(
            contractSettlementHistoryList.size() - 1
        );
        assertThat(testContractSettlementHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testContractSettlementHistory.getSettlerName()).isEqualTo(DEFAULT_SETTLER_NAME);
    }

    @Test
    @Transactional
    void createContractSettlementHistoryWithExistingId() throws Exception {
        // Create the ContractSettlementHistory with an existing ID
        contractSettlementHistory.setId(1L);

        int databaseSizeBeforeCreate = contractSettlementHistoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractSettlementHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractSettlementHistoryRepository.findAll().size();
        // set the field null
        contractSettlementHistory.setContractId(null);

        // Create the ContractSettlementHistory, which fails.

        restContractSettlementHistoryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContractSettlementHistories() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        // Get all the contractSettlementHistoryList
        restContractSettlementHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractSettlementHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].settlerName").value(hasItem(DEFAULT_SETTLER_NAME)));
    }

    @Test
    @Transactional
    void getContractSettlementHistory() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        // Get the contractSettlementHistory
        restContractSettlementHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, contractSettlementHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractSettlementHistory.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.settlerName").value(DEFAULT_SETTLER_NAME));
    }

    @Test
    @Transactional
    void getNonExistingContractSettlementHistory() throws Exception {
        // Get the contractSettlementHistory
        restContractSettlementHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractSettlementHistory() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();

        // Update the contractSettlementHistory
        ContractSettlementHistory updatedContractSettlementHistory = contractSettlementHistoryRepository
            .findById(contractSettlementHistory.getId())
            .get();
        // Disconnect from session so that the updates on updatedContractSettlementHistory are not directly saved in db
        em.detach(updatedContractSettlementHistory);
        updatedContractSettlementHistory.contractId(UPDATED_CONTRACT_ID).settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractSettlementHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContractSettlementHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlementHistory testContractSettlementHistory = contractSettlementHistoryList.get(
            contractSettlementHistoryList.size() - 1
        );
        assertThat(testContractSettlementHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testContractSettlementHistory.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void putNonExistingContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSettlementHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractSettlementHistoryWithPatch() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();

        // Update the contractSettlementHistory using partial update
        ContractSettlementHistory partialUpdatedContractSettlementHistory = new ContractSettlementHistory();
        partialUpdatedContractSettlementHistory.setId(contractSettlementHistory.getId());

        partialUpdatedContractSettlementHistory.settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSettlementHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSettlementHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlementHistory testContractSettlementHistory = contractSettlementHistoryList.get(
            contractSettlementHistoryList.size() - 1
        );
        assertThat(testContractSettlementHistory.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testContractSettlementHistory.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void fullUpdateContractSettlementHistoryWithPatch() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();

        // Update the contractSettlementHistory using partial update
        ContractSettlementHistory partialUpdatedContractSettlementHistory = new ContractSettlementHistory();
        partialUpdatedContractSettlementHistory.setId(contractSettlementHistory.getId());

        partialUpdatedContractSettlementHistory.contractId(UPDATED_CONTRACT_ID).settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSettlementHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSettlementHistory))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlementHistory testContractSettlementHistory = contractSettlementHistoryList.get(
            contractSettlementHistoryList.size() - 1
        );
        assertThat(testContractSettlementHistory.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testContractSettlementHistory.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractSettlementHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractSettlementHistory() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementHistoryRepository.findAll().size();
        contractSettlementHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlementHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSettlementHistory in the database
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractSettlementHistory() throws Exception {
        // Initialize the database
        contractSettlementHistoryRepository.saveAndFlush(contractSettlementHistory);

        int databaseSizeBeforeDelete = contractSettlementHistoryRepository.findAll().size();

        // Delete the contractSettlementHistory
        restContractSettlementHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractSettlementHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractSettlementHistory> contractSettlementHistoryList = contractSettlementHistoryRepository.findAll();
        assertThat(contractSettlementHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
