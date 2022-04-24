import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-history.reducer';

export const ContractHistoryDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractHistoryEntity = useAppSelector(state => state.contractHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractHistoryDetailsHeading">ContractHistory</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{contractHistoryEntity.id}</dd>
          <dt>
            <span id="dateStart">Date Start</span>
          </dt>
          <dd>
            {contractHistoryEntity.dateStart ? (
              <TextFormat value={contractHistoryEntity.dateStart} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="settlementDate">Settlement Date</span>
          </dt>
          <dd>
            {contractHistoryEntity.settlementDate ? (
              <TextFormat value={contractHistoryEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="totalLoanAmount">Total Loan Amount</span>
          </dt>
          <dd>{contractHistoryEntity.totalLoanAmount}</dd>
          <dt>
            <span id="interestPaymentPeriod">Interest Payment Period</span>
          </dt>
          <dd>{contractHistoryEntity.interestPaymentPeriod}</dd>
          <dt>
            <span id="interestRate">Interest Rate</span>
          </dt>
          <dd>{contractHistoryEntity.interestRate}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{contractHistoryEntity.customerName}</dd>
          <dt>
            <span id="customerAddress">Customer Address</span>
          </dt>
          <dd>{contractHistoryEntity.customerAddress}</dd>
          <dt>
            <span id="customerPhoneNumber">Customer Phone Number</span>
          </dt>
          <dd>{contractHistoryEntity.customerPhoneNumber}</dd>
          <dt>
            <span id="customerIdentityCard">Customer Identity Card</span>
          </dt>
          <dd>{contractHistoryEntity.customerIdentityCard}</dd>
          <dt>
            <span id="productName">Product Name</span>
          </dt>
          <dd>{contractHistoryEntity.productName}</dd>
          <dt>
            <span id="imei">Imei</span>
          </dt>
          <dd>{contractHistoryEntity.imei}</dd>
          <dt>
            <span id="icloud">Icloud</span>
          </dt>
          <dd>{contractHistoryEntity.icloud}</dd>
          <dt>
            <span id="userCreate">User Create</span>
          </dt>
          <dd>{contractHistoryEntity.userCreate}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{contractHistoryEntity.note}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{contractHistoryEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/contract-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-history/${contractHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractHistoryDetail;
