import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InterestPaidHistory from './interest-paid-history';
import InterestPaidHistoryDetail from './interest-paid-history-detail';
import InterestPaidHistoryUpdate from './interest-paid-history-update';
import InterestPaidHistoryDeleteDialog from './interest-paid-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InterestPaidHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InterestPaidHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InterestPaidHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={InterestPaidHistory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InterestPaidHistoryDeleteDialog} />
  </>
);

export default Routes;
