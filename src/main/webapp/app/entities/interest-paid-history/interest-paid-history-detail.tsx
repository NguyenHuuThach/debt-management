import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './interest-paid-history.reducer';

export const InterestPaidHistoryDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const interestPaidHistoryEntity = useAppSelector(state => state.interestPaidHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="interestPaidHistoryDetailsHeading">InterestPaidHistory</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{interestPaidHistoryEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{interestPaidHistoryEntity.contractId}</dd>
          <dt>
            <span id="interestPaidDate">Interest Paid Date</span>
          </dt>
          <dd>
            {interestPaidHistoryEntity.interestPaidDate ? (
              <TextFormat value={interestPaidHistoryEntity.interestPaidDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="payerName">Payer Name</span>
          </dt>
          <dd>{interestPaidHistoryEntity.payerName}</dd>
          <dt>
            <span id="paidAmount">Paid Amount</span>
          </dt>
          <dd>{interestPaidHistoryEntity.paidAmount}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{interestPaidHistoryEntity.note}</dd>
        </dl>
        <Button tag={Link} to="/interest-paid-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interest-paid-history/${interestPaidHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InterestPaidHistoryDetail;
