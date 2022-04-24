import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InterestPaid from './interest-paid';
import InterestPaidDetail from './interest-paid-detail';
import InterestPaidUpdate from './interest-paid-update';
import InterestPaidDeleteDialog from './interest-paid-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InterestPaidUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InterestPaidUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InterestPaidDetail} />
      <ErrorBoundaryRoute path={match.url} component={InterestPaid} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InterestPaidDeleteDialog} />
  </>
);

export default Routes;
