import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractHistory } from 'app/shared/model/contract-history.model';
import { StatusContract } from 'app/shared/model/enumerations/status-contract.model';
import { getEntity, updateEntity, createEntity, reset } from './contract-history.reducer';

export const ContractHistoryUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contractHistoryEntity = useAppSelector(state => state.contractHistory.entity);
  const loading = useAppSelector(state => state.contractHistory.loading);
  const updating = useAppSelector(state => state.contractHistory.updating);
  const updateSuccess = useAppSelector(state => state.contractHistory.updateSuccess);
  const statusContractValues = Object.keys(StatusContract);
  const handleClose = () => {
    props.history.push('/contract-history');
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
    values.dateStart = convertDateTimeToServer(values.dateStart);
    values.settlementDate = convertDateTimeToServer(values.settlementDate);

    const entity = {
      ...contractHistoryEntity,
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
          dateStart: displayDefaultDateTime(),
          settlementDate: displayDefaultDateTime(),
        }
      : {
          status: 'NORMAL',
          ...contractHistoryEntity,
          dateStart: convertDateTimeFromServer(contractHistoryEntity.dateStart),
          settlementDate: convertDateTimeFromServer(contractHistoryEntity.settlementDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.contractHistory.home.createOrEditLabel" data-cy="ContractHistoryCreateUpdateHeading">
            Create or edit a ContractHistory
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
                <ValidatedField name="id" required readOnly id="contract-history-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Date Start"
                id="contract-history-dateStart"
                name="dateStart"
                data-cy="dateStart"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Settlement Date"
                id="contract-history-settlementDate"
                name="settlementDate"
                data-cy="settlementDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Total Loan Amount"
                id="contract-history-totalLoanAmount"
                name="totalLoanAmount"
                data-cy="totalLoanAmount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Interest Payment Period"
                id="contract-history-interestPaymentPeriod"
                name="interestPaymentPeriod"
                data-cy="interestPaymentPeriod"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Interest Rate"
                id="contract-history-interestRate"
                name="interestRate"
                data-cy="interestRate"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Customer Name"
                id="contract-history-customerName"
                name="customerName"
                data-cy="customerName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Address"
                id="contract-history-customerAddress"
                name="customerAddress"
                data-cy="customerAddress"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Phone Number"
                id="contract-history-customerPhoneNumber"
                name="customerPhoneNumber"
                data-cy="customerPhoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Identity Card"
                id="contract-history-customerIdentityCard"
                name="customerIdentityCard"
                data-cy="customerIdentityCard"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Product Name"
                id="contract-history-productName"
                name="productName"
                data-cy="productName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Imei" id="contract-history-imei" name="imei" data-cy="imei" type="text" />
              <ValidatedField label="Icloud" id="contract-history-icloud" name="icloud" data-cy="icloud" type="text" />
              <ValidatedField label="User Create" id="contract-history-userCreate" name="userCreate" data-cy="userCreate" type="text" />
              <ValidatedField label="Note" id="contract-history-note" name="note" data-cy="note" type="text" />
              <ValidatedField label="Status" id="contract-history-status" name="status" data-cy="status" type="select">
                {statusContractValues.map(statusContract => (
                  <option value={statusContract} key={statusContract}>
                    {statusContract}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-history" replace color="info">
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

export default ContractHistoryUpdate;
