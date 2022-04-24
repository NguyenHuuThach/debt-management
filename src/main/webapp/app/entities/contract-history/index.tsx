import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractHistory from './contract-history';
import ContractHistoryDetail from './contract-history-detail';
import ContractHistoryUpdate from './contract-history-update';
import ContractHistoryDeleteDialog from './contract-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractHistory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractHistoryDeleteDialog} />
  </>
);

export default Routes;
