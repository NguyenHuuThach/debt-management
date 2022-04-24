import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PayDownPrincipal from './pay-down-principal';
import PayDownPrincipalDetail from './pay-down-principal-detail';
import PayDownPrincipalUpdate from './pay-down-principal-update';
import PayDownPrincipalDeleteDialog from './pay-down-principal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PayDownPrincipalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PayDownPrincipalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PayDownPrincipalDetail} />
      <ErrorBoundaryRoute path={match.url} component={PayDownPrincipal} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PayDownPrincipalDeleteDialog} />
  </>
);

export default Routes;
