package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterestPayTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InterestPay.class);
        InterestPay interestPay1 = new InterestPay();
        interestPay1.setId(1L);
        InterestPay interestPay2 = new InterestPay();
        interestPay2.setId(interestPay1.getId());
        assertThat(interestPay1).isEqualTo(interestPay2);
        interestPay2.setId(2L);
        assertThat(interestPay1).isNotEqualTo(interestPay2);
        interestPay1.setId(null);
        assertThat(interestPay1).isNotEqualTo(interestPay2);
    }
}
