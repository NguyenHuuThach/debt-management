import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractSettlementHistory from './contract-settlement-history';
import ContractSettlementHistoryDetail from './contract-settlement-history-detail';
import ContractSettlementHistoryUpdate from './contract-settlement-history-update';
import ContractSettlementHistoryDeleteDialog from './contract-settlement-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractSettlementHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractSettlementHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractSettlementHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractSettlementHistory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractSettlementHistoryDeleteDialog} />
  </>
);

export default Routes;
