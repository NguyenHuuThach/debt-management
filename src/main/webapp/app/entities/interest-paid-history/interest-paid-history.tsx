import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInterestPaidHistory } from 'app/shared/model/interest-paid-history.model';
import { getEntities } from './interest-paid-history.reducer';

export const InterestPaidHistory = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const interestPaidHistoryList = useAppSelector(state => state.interestPaidHistory.entities);
  const loading = useAppSelector(state => state.interestPaidHistory.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="interest-paid-history-heading" data-cy="InterestPaidHistoryHeading">
        Interest Paid Histories
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link
            to="/interest-paid-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Interest Paid History
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {interestPaidHistoryList && interestPaidHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Contract Id</th>
                <th>Interest Paid Date</th>
                <th>Payer Name</th>
                <th>Paid Amount</th>
                <th>Note</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {interestPaidHistoryList.map((interestPaidHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/interest-paid-history/${interestPaidHistory.id}`} color="link" size="sm">
                      {interestPaidHistory.id}
                    </Button>
                  </td>
                  <td>{interestPaidHistory.contractId}</td>
                  <td>
                    {interestPaidHistory.interestPaidDate ? (
                      <TextFormat type="date" value={interestPaidHistory.interestPaidDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{interestPaidHistory.payerName}</td>
                  <td>{interestPaidHistory.paidAmount}</td>
                  <td>{interestPaidHistory.note}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/interest-paid-history/${interestPaidHistory.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/interest-paid-history/${interestPaidHistory.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/interest-paid-history/${interestPaidHistory.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Interest Paid Histories found</div>
        )}
      </div>
    </div>
  );
};

export default InterestPaidHistory;
