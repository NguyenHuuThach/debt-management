import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPayDownPrincipalHistory } from 'app/shared/model/pay-down-principal-history.model';
import { getEntity, updateEntity, createEntity, reset } from './pay-down-principal-history.reducer';

export const PayDownPrincipalHistoryUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const payDownPrincipalHistoryEntity = useAppSelector(state => state.payDownPrincipalHistory.entity);
  const loading = useAppSelector(state => state.payDownPrincipalHistory.loading);
  const updating = useAppSelector(state => state.payDownPrincipalHistory.updating);
  const updateSuccess = useAppSelector(state => state.payDownPrincipalHistory.updateSuccess);
  const handleClose = () => {
    props.history.push('/pay-down-principal-history');
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
      ...payDownPrincipalHistoryEntity,
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
          ...payDownPrincipalHistoryEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.payDownPrincipalHistory.home.createOrEditLabel" data-cy="PayDownPrincipalHistoryCreateUpdateHeading">
            Create or edit a PayDownPrincipalHistory
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
                <ValidatedField name="id" required readOnly id="pay-down-principal-history-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Contract Id"
                id="pay-down-principal-history-contractId"
                name="contractId"
                data-cy="contractId"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Pay Down Principal Amount"
                id="pay-down-principal-history-payDownPrincipalAmount"
                name="payDownPrincipalAmount"
                data-cy="payDownPrincipalAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Payer Name"
                id="pay-down-principal-history-payerName"
                name="payerName"
                data-cy="payerName"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/pay-down-principal-history" replace color="info">
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

export default PayDownPrincipalHistoryUpdate;
