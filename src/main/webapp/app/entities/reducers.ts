import contract from 'app/entities/contract/contract.reducer';
import interestPaid from 'app/entities/interest-paid/interest-paid.reducer';
import payDownPrincipal from 'app/entities/pay-down-principal/pay-down-principal.reducer';
import contractSettlement from 'app/entities/contract-settlement/contract-settlement.reducer';
import contractHistory from 'app/entities/contract-history/contract-history.reducer';
import interestPaidHistory from 'app/entities/interest-paid-history/interest-paid-history.reducer';
import payDownPrincipalHistory from 'app/entities/pay-down-principal-history/pay-down-principal-history.reducer';
import contractSettlementHistory from 'app/entities/contract-settlement-history/contract-settlement-history.reducer';
import interestPay from 'app/entities/interest-pay/interest-pay.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  contract,
  interestPaid,
  payDownPrincipal,
  contractSettlement,
  contractHistory,
  interestPaidHistory,
  payDownPrincipalHistory,
  contractSettlementHistory,
  interestPay,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
