import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInterestPaid } from 'app/shared/model/interest-paid.model';
import { getEntity, updateEntity, createEntity, reset } from './interest-paid.reducer';

export const InterestPaidUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const interestPaidEntity = useAppSelector(state => state.interestPaid.entity);
  const loading = useAppSelector(state => state.interestPaid.loading);
  const updating = useAppSelector(state => state.interestPaid.updating);
  const updateSuccess = useAppSelector(state => state.interestPaid.updateSuccess);
  const handleClose = () => {
    props.history.push('/interest-paid');
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
    values.interestPaidDate = convertDateTimeToServer(values.interestPaidDate);

    const entity = {
      ...interestPaidEntity,
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
          interestPaidDate: displayDefaultDateTime(),
        }
      : {
          ...interestPaidEntity,
          interestPaidDate: convertDateTimeFromServer(interestPaidEntity.interestPaidDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.interestPaid.home.createOrEditLabel" data-cy="InterestPaidCreateUpdateHeading">
            Create or edit a InterestPaid
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
                <ValidatedField name="id" required readOnly id="interest-paid-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Contract Id"
                id="interest-paid-contractId"
                name="contractId"
                data-cy="contractId"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Interest Paid Date"
                id="interest-paid-interestPaidDate"
                name="interestPaidDate"
                data-cy="interestPaidDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Payer Name" id="interest-paid-payerName" name="payerName" data-cy="payerName" type="text" />
              <ValidatedField
                label="Paid Amount"
                id="interest-paid-paidAmount"
                name="paidAmount"
                data-cy="paidAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Note" id="interest-paid-note" name="note" data-cy="note" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/interest-paid" replace color="info">
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

export default InterestPaidUpdate;
