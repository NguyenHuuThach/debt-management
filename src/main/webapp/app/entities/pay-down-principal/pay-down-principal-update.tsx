import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPayDownPrincipal } from 'app/shared/model/pay-down-principal.model';
import { getEntity, updateEntity, createEntity, reset } from './pay-down-principal.reducer';

export const PayDownPrincipalUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const payDownPrincipalEntity = useAppSelector(state => state.payDownPrincipal.entity);
  const loading = useAppSelector(state => state.payDownPrincipal.loading);
  const updating = useAppSelector(state => state.payDownPrincipal.updating);
  const updateSuccess = useAppSelector(state => state.payDownPrincipal.updateSuccess);
  const handleClose = () => {
    props.history.push('/pay-down-principal');
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
    values.payDownPrincipalDate = convertDateTimeToServer(values.payDownPrincipalDate);

    const entity = {
      ...payDownPrincipalEntity,
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
      ? {
          payDownPrincipalDate: displayDefaultDateTime(),
        }
      : {
          ...payDownPrincipalEntity,
          payDownPrincipalDate: convertDateTimeFromServer(payDownPrincipalEntity.payDownPrincipalDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.payDownPrincipal.home.createOrEditLabel" data-cy="PayDownPrincipalCreateUpdateHeading">
            Create or edit a PayDownPrincipal
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
                <ValidatedField name="id" required readOnly id="pay-down-principal-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Contract Id"
                id="pay-down-principal-contractId"
                name="contractId"
                data-cy="contractId"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Pay Down Principal Date"
                id="pay-down-principal-payDownPrincipalDate"
                name="payDownPrincipalDate"
                data-cy="payDownPrincipalDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Pay Down Principal Amount"
                id="pay-down-principal-payDownPrincipalAmount"
                name="payDownPrincipalAmount"
                data-cy="payDownPrincipalAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Payer Name" id="pay-down-principal-payerName" name="payerName" data-cy="payerName" type="text" />
              <ValidatedField label="User Create" id="pay-down-principal-userCreate" name="userCreate" data-cy="userCreate" type="text" />
              <ValidatedField label="Note" id="pay-down-principal-note" name="note" data-cy="note" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/pay-down-principal" replace color="info">
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

export default PayDownPrincipalUpdate;
