import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InterestPay from './interest-pay';
import InterestPayDetail from './interest-pay-detail';
import InterestPayUpdate from './interest-pay-update';
import InterestPayDeleteDialog from './interest-pay-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InterestPayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InterestPayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InterestPayDetail} />
      <ErrorBoundaryRoute path={match.url} component={InterestPay} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InterestPayDeleteDialog} />
  </>
);

export default Routes;
