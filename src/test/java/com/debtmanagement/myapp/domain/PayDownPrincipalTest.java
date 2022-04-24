package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayDownPrincipalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayDownPrincipal.class);
        PayDownPrincipal payDownPrincipal1 = new PayDownPrincipal();
        payDownPrincipal1.setId(1L);
        PayDownPrincipal payDownPrincipal2 = new PayDownPrincipal();
        payDownPrincipal2.setId(payDownPrincipal1.getId());
        assertThat(payDownPrincipal1).isEqualTo(payDownPrincipal2);
        payDownPrincipal2.setId(2L);
        assertThat(payDownPrincipal1).isNotEqualTo(payDownPrincipal2);
        payDownPrincipal1.setId(null);
        assertThat(payDownPrincipal1).isNotEqualTo(payDownPrincipal2);
    }
}
