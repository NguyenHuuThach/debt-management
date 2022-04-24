package com.debtmanagement.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.debtmanagement.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterestPaidHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InterestPaidHistory.class);
        InterestPaidHistory interestPaidHistory1 = new InterestPaidHistory();
        interestPaidHistory1.setId(1L);
        InterestPaidHistory interestPaidHistory2 = new InterestPaidHistory();
        interestPaidHistory2.setId(interestPaidHistory1.getId());
        assertThat(interestPaidHistory1).isEqualTo(interestPaidHistory2);
        interestPaidHistory2.setId(2L);
        assertThat(interestPaidHistory1).isNotEqualTo(interestPaidHistory2);
        interestPaidHistory1.setId(null);
        assertThat(interestPaidHistory1).isNotEqualTo(interestPaidHistory2);
    }
}
