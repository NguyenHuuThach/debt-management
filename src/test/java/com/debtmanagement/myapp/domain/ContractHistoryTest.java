package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractHistory.class);
        ContractHistory contractHistory1 = new ContractHistory();
        contractHistory1.setId(1L);
        ContractHistory contractHistory2 = new ContractHistory();
        contractHistory2.setId(contractHistory1.getId());
        assertThat(contractHistory1).isEqualTo(contractHistory2);
        contractHistory2.setId(2L);
        assertThat(contractHistory1).isNotEqualTo(contractHistory2);
        contractHistory1.setId(null);
        assertThat(contractHistory1).isNotEqualTo(contractHistory2);
    }
}
