import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-settlement-history.reducer';

export const ContractSettlementHistoryDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractSettlementHistoryEntity = useAppSelector(state => state.contractSettlementHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractSettlementHistoryDetailsHeading">ContractSettlementHistory</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{contractSettlementHistoryEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{contractSettlementHistoryEntity.contractId}</dd>
          <dt>
            <span id="settlerName">Settler Name</span>
          </dt>
          <dd>{contractSettlementHistoryEntity.settlerName}</dd>
        </dl>
        <Button tag={Link} to="/contract-settlement-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-settlement-history/${contractSettlementHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractSettlementHistoryDetail;
