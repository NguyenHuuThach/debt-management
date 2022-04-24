package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSettlementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSettlement.class);
        ContractSettlement contractSettlement1 = new ContractSettlement();
        contractSettlement1.setId(1L);
        ContractSettlement contractSettlement2 = new ContractSettlement();
        contractSettlement2.setId(contractSettlement1.getId());
        assertThat(contractSettlement1).isEqualTo(contractSettlement2);
        contractSettlement2.setId(2L);
        assertThat(contractSettlement1).isNotEqualTo(contractSettlement2);
        contractSettlement1.setId(null);
        assertThat(contractSettlement1).isNotEqualTo(contractSettlement2);
    }
}
