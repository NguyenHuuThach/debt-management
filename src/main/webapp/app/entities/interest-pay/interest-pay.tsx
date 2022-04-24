import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInterestPay } from 'app/shared/model/interest-pay.model';
import { getEntities } from './interest-pay.reducer';

export const InterestPay = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const interestPayList = useAppSelector(state => state.interestPay.entities);
  const loading = useAppSelector(state => state.interestPay.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="interest-pay-heading" data-cy="InterestPayHeading">
        Interest Pays
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to="/interest-pay/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Interest Pay
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {interestPayList && interestPayList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Contract Id</th>
                <th>Interest Pay Date</th>
                <th>Interest Pay Amount</th>
                <th>Payer Name</th>
                <th>Note</th>
                <th>Status</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {interestPayList.map((interestPay, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/interest-pay/${interestPay.id}`} color="link" size="sm">
                      {interestPay.id}
                    </Button>
                  </td>
                  <td>{interestPay.contractId}</td>
                  <td>
                    {interestPay.interestPayDate ? (
                      <TextFormat type="date" value={interestPay.interestPayDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{interestPay.interestPayAmount}</td>
                  <td>{interestPay.payerName}</td>
                  <td>{interestPay.note}</td>
                  <td>{interestPay.status ? 'true' : 'false'}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/interest-pay/${interestPay.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/interest-pay/${interestPay.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/interest-pay/${interestPay.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Interest Pays found</div>
        )}
      </div>
    </div>
  );
};

export default InterestPay;
