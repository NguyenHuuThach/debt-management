import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPayDownPrincipal } from 'app/shared/model/pay-down-principal.model';
import { getEntities } from './pay-down-principal.reducer';

export const PayDownPrincipal = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const payDownPrincipalList = useAppSelector(state => state.payDownPrincipal.entities);
  const loading = useAppSelector(state => state.payDownPrincipal.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="pay-down-principal-heading" data-cy="PayDownPrincipalHeading">
        Pay Down Principals
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link
            to="/pay-down-principal/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Pay Down Principal
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {payDownPrincipalList && payDownPrincipalList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Contract Id</th>
                <th>Pay Down Principal Date</th>
                <th>Pay Down Principal Amount</th>
                <th>Payer Name</th>
                <th>User Create</th>
                <th>Note</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {payDownPrincipalList.map((payDownPrincipal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/pay-down-principal/${payDownPrincipal.id}`} color="link" size="sm">
                      {payDownPrincipal.id}
                    </Button>
                  </td>
                  <td>{payDownPrincipal.contractId}</td>
                  <td>
                    {payDownPrincipal.payDownPrincipalDate ? (
                      <TextFormat type="date" value={payDownPrincipal.payDownPrincipalDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{payDownPrincipal.payDownPrincipalAmount}</td>
                  <td>{payDownPrincipal.payerName}</td>
                  <td>{payDownPrincipal.userCreate}</td>
                  <td>{payDownPrincipal.note}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/pay-down-principal/${payDownPrincipal.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/pay-down-principal/${payDownPrincipal.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/pay-down-principal/${payDownPrincipal.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Pay Down Principals found</div>
        )}
      </div>
    </div>
  );
};

export default PayDownPrincipal;
