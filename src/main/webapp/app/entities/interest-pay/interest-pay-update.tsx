import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInterestPay } from 'app/shared/model/interest-pay.model';
import { getEntity, updateEntity, createEntity, reset } from './interest-pay.reducer';

export const InterestPayUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const interestPayEntity = useAppSelector(state => state.interestPay.entity);
  const loading = useAppSelector(state => state.interestPay.loading);
  const updating = useAppSelector(state => state.interestPay.updating);
  const updateSuccess = useAppSelector(state => state.interestPay.updateSuccess);
  const handleClose = () => {
    props.history.push('/interest-pay');
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
    values.interestPayDate = convertDateTimeToServer(values.interestPayDate);

    const entity = {
      ...interestPayEntity,
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
          interestPayDate: displayDefaultDateTime(),
        }
      : {
          ...interestPayEntity,
          interestPayDate: convertDateTimeFromServer(interestPayEntity.interestPayDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.interestPay.home.createOrEditLabel" data-cy="InterestPayCreateUpdateHeading">
            Create or edit a InterestPay
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="interest-pay-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Contract Id"
                id="interest-pay-contractId"
                name="contractId"
                data-cy="contractId"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Interest Pay Date"
                id="interest-pay-interestPayDate"
                name="interestPayDate"
                data-cy="interestPayDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Interest Pay Amount"
                id="interest-pay-interestPayAmount"
                name="interestPayAmount"
                data-cy="interestPayAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Payer Name" id="interest-pay-payerName" name="payerName" data-cy="payerName" type="text" />
              <ValidatedField label="Note" id="interest-pay-note" name="note" data-cy="note" type="text" />
              <ValidatedField label="Status" id="interest-pay-status" name="status" data-cy="status" check type="checkbox" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/interest-pay" replace color="info">
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

export default InterestPayUpdate;
