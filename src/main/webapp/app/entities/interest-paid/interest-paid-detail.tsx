import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './interest-paid.reducer';

export const InterestPaidDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const interestPaidEntity = useAppSelector(state => state.interestPaid.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="interestPaidDetailsHeading">InterestPaid</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{interestPaidEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{interestPaidEntity.contractId}</dd>
          <dt>
            <span id="interestPaidDate">Interest Paid Date</span>
          </dt>
          <dd>
            {interestPaidEntity.interestPaidDate ? (
              <TextFormat value={interestPaidEntity.interestPaidDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="payerName">Payer Name</span>
          </dt>
          <dd>{interestPaidEntity.payerName}</dd>
          <dt>
            <span id="paidAmount">Paid Amount</span>
          </dt>
          <dd>{interestPaidEntity.paidAmount}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{interestPaidEntity.note}</dd>
        </dl>
        <Button tag={Link} to="/interest-paid" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interest-paid/${interestPaidEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InterestPaidDetail;
