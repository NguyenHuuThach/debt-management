import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInterestPaidHistory } from 'app/shared/model/interest-paid-history.model';
import { getEntity, updateEntity, createEntity, reset } from './interest-paid-history.reducer';

export const InterestPaidHistoryUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const interestPaidHistoryEntity = useAppSelector(state => state.interestPaidHistory.entity);
  const loading = useAppSelector(state => state.interestPaidHistory.loading);
  const updating = useAppSelector(state => state.interestPaidHistory.updating);
  const updateSuccess = useAppSelector(state => state.interestPaidHistory.updateSuccess);
  const handleClose = () => {
    props.history.push('/interest-paid-history');
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
      ...interestPaidHistoryEntity,
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
          ...interestPaidHistoryEntity,
          interestPaidDate: convertDateTimeFromServer(interestPaidHistoryEntity.interestPaidDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.interestPaidHistory.home.createOrEditLabel" data-cy="InterestPaidHistoryCreateUpdateHeading">
            Create or edit a InterestPaidHistory
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
                <ValidatedField name="id" required readOnly id="interest-paid-history-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Contract Id"
                id="interest-paid-history-contractId"
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
                id="interest-paid-history-interestPaidDate"
                name="interestPaidDate"
                data-cy="interestPaidDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Payer Name" id="interest-paid-history-payerName" name="payerName" data-cy="payerName" type="text" />
              <ValidatedField
                label="Paid Amount"
                id="interest-paid-history-paidAmount"
                name="paidAmount"
                data-cy="paidAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Note" id="interest-paid-history-note" name="note" data-cy="note" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/interest-paid-history" replace color="info">
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

export default InterestPaidHistoryUpdate;
