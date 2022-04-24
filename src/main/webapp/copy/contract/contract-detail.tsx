import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract.reducer';

export const ContractDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractEntity = useAppSelector(state => state.contract.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractDetailsHeading">Contract</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{contractEntity.id}</dd>
          <dt>
            <span id="dateStart">Date Start</span>
          </dt>
          <dd>{contractEntity.dateStart ? <TextFormat value={contractEntity.dateStart} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="settlementDate">Settlement Date</span>
          </dt>
          <dd>
            {contractEntity.settlementDate ? (
              <TextFormat value={contractEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="totalLoanAmount">Total Loan Amount</span>
          </dt>
          <dd>{contractEntity.totalLoanAmount}</dd>
          <dt>
            <span id="interestPaymentPeriod">Interest Payment Period</span>
          </dt>
          <dd>{contractEntity.interestPaymentPeriod}</dd>
          <dt>
            <span id="interestRate">Interest Rate</span>
          </dt>
          <dd>{contractEntity.interestRate}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{contractEntity.customerName}</dd>
          <dt>
            <span id="customerAddress">Customer Address</span>
          </dt>
          <dd>{contractEntity.customerAddress}</dd>
          <dt>
            <span id="customerPhoneNumber">Customer Phone Number</span>
          </dt>
          <dd>{contractEntity.customerPhoneNumber}</dd>
          <dt>
            <span id="customerIdentityCard">Customer Identity Card</span>
          </dt>
          <dd>{contractEntity.customerIdentityCard}</dd>
          <dt>
            <span id="productName">Product Name</span>
          </dt>
          <dd>{contractEntity.productName}</dd>
          <dt>
            <span id="imei">Imei</span>
          </dt>
          <dd>{contractEntity.imei}</dd>
          <dt>
            <span id="icloud">Icloud</span>
          </dt>
          <dd>{contractEntity.icloud}</dd>
          <dt>
            <span id="userCreate">User Create</span>
          </dt>
          <dd>{contractEntity.userCreate}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{contractEntity.note}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{contractEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/contract" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract/${contractEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractDetail;
