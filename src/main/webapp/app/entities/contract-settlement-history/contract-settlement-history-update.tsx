import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractSettlementHistory } from 'app/shared/model/contract-settlement-history.model';
import { getEntity, updateEntity, createEntity, reset } from './contract-settlement-history.reducer';

export const ContractSettlementHistoryUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contractSettlementHistoryEntity = useAppSelector(state => state.contractSettlementHistory.entity);
  const loading = useAppSelector(state => state.contractSettlementHistory.loading);
  const updating = useAppSelector(state => state.contractSettlementHistory.updating);
  const updateSuccess = useAppSelector(state => state.contractSettlementHistory.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract-settlement-history');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...contractSettlementHistoryEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...contractSettlementHistoryEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="debtManagementApp.contractSettlementHistory.home.createOrEditLabel"
            data-cy="ContractSettlementHistoryCreateUpdateHeading"
          >
            Create or edit a ContractSettlementHistory
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="contract-settlement-history-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Contract Id"
                id="contract-settlement-history-contractId"
                name="contractId"
                data-cy="contractId"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Settler Name"
                id="contract-settlement-history-settlerName"
                name="settlerName"
                data-cy="settlerName"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-settlement-history" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ContractSettlementHistoryUpdate;
