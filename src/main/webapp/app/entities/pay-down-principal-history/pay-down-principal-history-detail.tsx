import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './pay-down-principal-history.reducer';

export const PayDownPrincipalHistoryDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const payDownPrincipalHistoryEntity = useAppSelector(state => state.payDownPrincipalHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="payDownPrincipalHistoryDetailsHeading">PayDownPrincipalHistory</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{payDownPrincipalHistoryEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{payDownPrincipalHistoryEntity.contractId}</dd>
          <dt>
            <span id="payDownPrincipalAmount">Pay Down Principal Amount</span>
          </dt>
          <dd>{payDownPrincipalHistoryEntity.payDownPrincipalAmount}</dd>
          <dt>
            <span id="payerName">Payer Name</span>
          </dt>
          <dd>{payDownPrincipalHistoryEntity.payerName}</dd>
        </dl>
        <Button tag={Link} to="/pay-down-principal-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pay-down-principal-history/${payDownPrincipalHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PayDownPrincipalHistoryDetail;
