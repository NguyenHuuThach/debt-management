package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterestPaidTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InterestPaid.class);
        InterestPaid interestPaid1 = new InterestPaid();
        interestPaid1.setId(1L);
        InterestPaid interestPaid2 = new InterestPaid();
        interestPaid2.setId(interestPaid1.getId());
        assertThat(interestPaid1).isEqualTo(interestPaid2);
        interestPaid2.setId(2L);
        assertThat(interestPaid1).isNotEqualTo(interestPaid2);
        interestPaid1.setId(null);
        assertThat(interestPaid1).isNotEqualTo(interestPaid2);
    }
}
