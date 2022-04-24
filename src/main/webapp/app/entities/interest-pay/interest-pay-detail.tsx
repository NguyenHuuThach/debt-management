import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './interest-pay.reducer';

export const InterestPayDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const interestPayEntity = useAppSelector(state => state.interestPay.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="interestPayDetailsHeading">InterestPay</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{interestPayEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{interestPayEntity.contractId}</dd>
          <dt>
            <span id="interestPayDate">Interest Pay Date</span>
          </dt>
          <dd>
            {interestPayEntity.interestPayDate ? (
              <TextFormat value={interestPayEntity.interestPayDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="interestPayAmount">Interest Pay Amount</span>
          </dt>
          <dd>{interestPayEntity.interestPayAmount}</dd>
          <dt>
            <span id="payerName">Payer Name</span>
          </dt>
          <dd>{interestPayEntity.payerName}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{interestPayEntity.note}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{interestPayEntity.status ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/interest-pay" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interest-pay/${interestPayEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InterestPayDetail;
