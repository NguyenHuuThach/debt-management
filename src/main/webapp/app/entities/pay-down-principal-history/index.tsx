import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PayDownPrincipalHistory from './pay-down-principal-history';
import PayDownPrincipalHistoryDetail from './pay-down-principal-history-detail';
import PayDownPrincipalHistoryUpdate from './pay-down-principal-history-update';
import PayDownPrincipalHistoryDeleteDialog from './pay-down-principal-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PayDownPrincipalHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PayDownPrincipalHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PayDownPrincipalHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={PayDownPrincipalHistory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PayDownPrincipalHistoryDeleteDialog} />
  </>
);

export default Routes;
