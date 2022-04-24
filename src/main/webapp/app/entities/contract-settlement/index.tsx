import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractSettlement from './contract-settlement';
import ContractSettlementDetail from './contract-settlement-detail';
import ContractSettlementUpdate from './contract-settlement-update';
import ContractSettlementDeleteDialog from './contract-settlement-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractSettlementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractSettlementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractSettlementDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractSettlement} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractSettlementDeleteDialog} />
  </>
);

export default Routes;
