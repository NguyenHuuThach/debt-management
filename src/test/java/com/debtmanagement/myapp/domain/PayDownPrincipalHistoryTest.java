package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayDownPrincipalHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayDownPrincipalHistory.class);
        PayDownPrincipalHistory payDownPrincipalHistory1 = new PayDownPrincipalHistory();
        payDownPrincipalHistory1.setId(1L);
        PayDownPrincipalHistory payDownPrincipalHistory2 = new PayDownPrincipalHistory();
        payDownPrincipalHistory2.setId(payDownPrincipalHistory1.getId());
        assertThat(payDownPrincipalHistory1).isEqualTo(payDownPrincipalHistory2);
        payDownPrincipalHistory2.setId(2L);
        assertThat(payDownPrincipalHistory1).isNotEqualTo(payDownPrincipalHistory2);
        payDownPrincipalHistory1.setId(null);
        assertThat(payDownPrincipalHistory1).isNotEqualTo(payDownPrincipalHistory2);
    }
}
