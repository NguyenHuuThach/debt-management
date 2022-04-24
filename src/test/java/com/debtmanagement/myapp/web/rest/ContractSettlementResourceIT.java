package com.debtmanagement.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.debtmanagement.myapp.IntegrationTest;
import com.debtmanagement.myapp.domain.ContractSettlement;
import com.debtmanagement.myapp.repository.ContractSettlementRepository;
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
 * Integration tests for the {@link ContractSettlementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractSettlementResourceIT {

    private static final Long DEFAULT_CONTRACT_ID = 1L;
    private static final Long UPDATED_CONTRACT_ID = 2L;

    private static final String DEFAULT_SETTLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETTLER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contract-settlements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractSettlementRepository contractSettlementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractSettlementMockMvc;

    private ContractSettlement contractSettlement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSettlement createEntity(EntityManager em) {
        ContractSettlement contractSettlement = new ContractSettlement().contractId(DEFAULT_CONTRACT_ID).settlerName(DEFAULT_SETTLER_NAME);
        return contractSettlement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSettlement createUpdatedEntity(EntityManager em) {
        ContractSettlement contractSettlement = new ContractSettlement().contractId(UPDATED_CONTRACT_ID).settlerName(UPDATED_SETTLER_NAME);
        return contractSettlement;
    }

    @BeforeEach
    public void initTest() {
        contractSettlement = createEntity(em);
    }

    @Test
    @Transactional
    void createContractSettlement() throws Exception {
        int databaseSizeBeforeCreate = contractSettlementRepository.findAll().size();
        // Create the ContractSettlement
        restContractSettlementMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isCreated());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeCreate + 1);
        ContractSettlement testContractSettlement = contractSettlementList.get(contractSettlementList.size() - 1);
        assertThat(testContractSettlement.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testContractSettlement.getSettlerName()).isEqualTo(DEFAULT_SETTLER_NAME);
    }

    @Test
    @Transactional
    void createContractSettlementWithExistingId() throws Exception {
        // Create the ContractSettlement with an existing ID
        contractSettlement.setId(1L);

        int databaseSizeBeforeCreate = contractSettlementRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractSettlementMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractSettlementRepository.findAll().size();
        // set the field null
        contractSettlement.setContractId(null);

        // Create the ContractSettlement, which fails.

        restContractSettlementMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContractSettlements() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        // Get all the contractSettlementList
        restContractSettlementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractSettlement.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].settlerName").value(hasItem(DEFAULT_SETTLER_NAME)));
    }

    @Test
    @Transactional
    void getContractSettlement() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        // Get the contractSettlement
        restContractSettlementMockMvc
            .perform(get(ENTITY_API_URL_ID, contractSettlement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractSettlement.getId().intValue()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.intValue()))
            .andExpect(jsonPath("$.settlerName").value(DEFAULT_SETTLER_NAME));
    }

    @Test
    @Transactional
    void getNonExistingContractSettlement() throws Exception {
        // Get the contractSettlement
        restContractSettlementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractSettlement() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();

        // Update the contractSettlement
        ContractSettlement updatedContractSettlement = contractSettlementRepository.findById(contractSettlement.getId()).get();
        // Disconnect from session so that the updates on updatedContractSettlement are not directly saved in db
        em.detach(updatedContractSettlement);
        updatedContractSettlement.contractId(UPDATED_CONTRACT_ID).settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractSettlement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContractSettlement))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlement testContractSettlement = contractSettlementList.get(contractSettlementList.size() - 1);
        assertThat(testContractSettlement.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testContractSettlement.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void putNonExistingContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSettlement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractSettlementWithPatch() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();

        // Update the contractSettlement using partial update
        ContractSettlement partialUpdatedContractSettlement = new ContractSettlement();
        partialUpdatedContractSettlement.setId(contractSettlement.getId());

        partialUpdatedContractSettlement.settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSettlement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSettlement))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlement testContractSettlement = contractSettlementList.get(contractSettlementList.size() - 1);
        assertThat(testContractSettlement.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testContractSettlement.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void fullUpdateContractSettlementWithPatch() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();

        // Update the contractSettlement using partial update
        ContractSettlement partialUpdatedContractSettlement = new ContractSettlement();
        partialUpdatedContractSettlement.setId(contractSettlement.getId());

        partialUpdatedContractSettlement.contractId(UPDATED_CONTRACT_ID).settlerName(UPDATED_SETTLER_NAME);

        restContractSettlementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSettlement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSettlement))
            )
            .andExpect(status().isOk());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
        ContractSettlement testContractSettlement = contractSettlementList.get(contractSettlementList.size() - 1);
        assertThat(testContractSettlement.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testContractSettlement.getSettlerName()).isEqualTo(UPDATED_SETTLER_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractSettlement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractSettlement() throws Exception {
        int databaseSizeBeforeUpdate = contractSettlementRepository.findAll().size();
        contractSettlement.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSettlementMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSettlement))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSettlement in the database
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractSettlement() throws Exception {
        // Initialize the database
        contractSettlementRepository.saveAndFlush(contractSettlement);

        int databaseSizeBeforeDelete = contractSettlementRepository.findAll().size();

        // Delete the contractSettlement
        restContractSettlementMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractSettlement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractSettlement> contractSettlementList = contractSettlementRepository.findAll();
        assertThat(contractSettlementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
