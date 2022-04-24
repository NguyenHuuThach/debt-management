import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Contract from './contract';
import InterestPaid from './interest-paid';
import PayDownPrincipal from './pay-down-principal';
import ContractSettlement from './contract-settlement';
import ContractHistory from './contract-history';
import InterestPaidHistory from './interest-paid-history';
import PayDownPrincipalHistory from './pay-down-principal-history';
import ContractSettlementHistory from './contract-settlement-history';
import InterestPay from './interest-pay';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}contract`} component={Contract} />
        <ErrorBoundaryRoute path={`${match.url}interest-paid`} component={InterestPaid} />
        <ErrorBoundaryRoute path={`${match.url}pay-down-principal`} component={PayDownPrincipal} />
        <ErrorBoundaryRoute path={`${match.url}contract-settlement`} component={ContractSettlement} />
        <ErrorBoundaryRoute path={`${match.url}contract-history`} component={ContractHistory} />
        <ErrorBoundaryRoute path={`${match.url}interest-paid-history`} component={InterestPaidHistory} />
        <ErrorBoundaryRoute path={`${match.url}pay-down-principal-history`} component={PayDownPrincipalHistory} />
        <ErrorBoundaryRoute path={`${match.url}contract-settlement-history`} component={ContractSettlementHistory} />
        <ErrorBoundaryRoute path={`${match.url}interest-pay`} component={InterestPay} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
