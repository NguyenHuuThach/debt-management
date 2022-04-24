import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractSettlementHistory } from 'app/shared/model/contract-settlement-history.model';
import { getEntities } from './contract-settlement-history.reducer';

export const ContractSettlementHistory = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const contractSettlementHistoryList = useAppSelector(state => state.contractSettlementHistory.entities);
  const loading = useAppSelector(state => state.contractSettlementHistory.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contract-settlement-history-heading" data-cy="ContractSettlementHistoryHeading">
        Contract Settlement Histories
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link
            to="/contract-settlement-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Contract Settlement History
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractSettlementHistoryList && contractSettlementHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Contract Id</th>
                <th>Settler Name</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractSettlementHistoryList.map((contractSettlementHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/contract-settlement-history/${contractSettlementHistory.id}`} color="link" size="sm">
                      {contractSettlementHistory.id}
                    </Button>
                  </td>
                  <td>{contractSettlementHistory.contractId}</td>
                  <td>{contractSettlementHistory.settlerName}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/contract-settlement-history/${contractSettlementHistory.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-settlement-history/${contractSettlementHistory.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-settlement-history/${contractSettlementHistory.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Contract Settlement Histories found</div>
        )}
      </div>
    </div>
  );
};

export default ContractSettlementHistory;
