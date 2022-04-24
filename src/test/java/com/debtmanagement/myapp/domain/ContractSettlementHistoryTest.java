package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSettlementHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSettlementHistory.class);
        ContractSettlementHistory contractSettlementHistory1 = new ContractSettlementHistory();
        contractSettlementHistory1.setId(1L);
        ContractSettlementHistory contractSettlementHistory2 = new ContractSettlementHistory();
        contractSettlementHistory2.setId(contractSettlementHistory1.getId());
        assertThat(contractSettlementHistory1).isEqualTo(contractSettlementHistory2);
        contractSettlementHistory2.setId(2L);
        assertThat(contractSettlementHistory1).isNotEqualTo(contractSettlementHistory2);
        contractSettlementHistory1.setId(null);
        assertThat(contractSettlementHistory1).isNotEqualTo(contractSettlementHistory2);
    }
}
