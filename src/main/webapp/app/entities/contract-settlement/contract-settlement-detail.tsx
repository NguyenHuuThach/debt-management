import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-settlement.reducer';

export const ContractSettlementDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractSettlementEntity = useAppSelector(state => state.contractSettlement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractSettlementDetailsHeading">ContractSettlement</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{contractSettlementEntity.id}</dd>
          <dt>
            <span id="contractId">Contract Id</span>
          </dt>
          <dd>{contractSettlementEntity.contractId}</dd>
          <dt>
            <span id="settlerName">Settler Name</span>
          </dt>
          <dd>{contractSettlementEntity.settlerName}</dd>
        </dl>
        <Button tag={Link} to="/contract-settlement" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-settlement/${contractSettlementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractSettlementDetail;
