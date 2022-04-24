import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractSettlement } from 'app/shared/model/contract-settlement.model';
import { getEntities } from './contract-settlement.reducer';

export const ContractSettlement = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const contractSettlementList = useAppSelector(state => state.contractSettlement.entities);
  const loading = useAppSelector(state => state.contractSettlement.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contract-settlement-heading" data-cy="ContractSettlementHeading">
        Contract Settlements
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link
            to="/contract-settlement/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Contract Settlement
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractSettlementList && contractSettlementList.length > 0 ? (
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
              {contractSettlementList.map((contractSettlement, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/contract-settlement/${contractSettlement.id}`} color="link" size="sm">
                      {contractSettlement.id}
                    </Button>
                  </td>
                  <td>{contractSettlement.contractId}</td>
                  <td>{contractSettlement.settlerName}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/contract-settlement/${contractSettlement.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-settlement/${contractSettlement.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-settlement/${contractSettlement.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Contract Settlements found</div>
        )}
      </div>
    </div>
  );
};

export default ContractSettlement;
