import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './pay-down-principal.reducer';

export const PayDownPrincipalDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const payDownPrincipalEntity = useAppSelector(state => state.payDownPrincipal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="payDownPrincipalDetailsHeading">PayDownPrincipal</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{payDownPrincipalEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{payDownPrincipalEntity.contractId}</dd>
          <dt>
            <span id="payDownPrincipalDate">Pay Down Principal Date</span>
          </dt>
          <dd>
            {payDownPrincipalEntity.payDownPrincipalDate ? (
              <TextFormat value={payDownPrincipalEntity.payDownPrincipalDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="payDownPrincipalAmount">Pay Down Principal Amount</span>
          </dt>
          <dd>{payDownPrincipalEntity.payDownPrincipalAmount}</dd>
          <dt>
            <span id="payerName">Payer Name</span>
          </dt>
          <dd>{payDownPrincipalEntity.payerName}</dd>
          <dt>
            <span id="userCreate">User Create</span>
          </dt>
          <dd>{payDownPrincipalEntity.userCreate}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{payDownPrincipalEntity.note}</dd>
        </dl>
        <Button tag={Link} to="/pay-down-principal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pay-down-principal/${payDownPrincipalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PayDownPrincipalDetail;
