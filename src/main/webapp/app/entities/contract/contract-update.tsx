import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContract } from 'app/shared/model/contract.model';
import { StatusContract } from 'app/shared/model/enumerations/status-contract.model';
import { getEntity, updateEntity, createEntity, reset } from './contract.reducer';

export const ContractUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contractEntity = useAppSelector(state => state.contract.entity);
  const loading = useAppSelector(state => state.contract.loading);
  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const statusContractValues = Object.keys(StatusContract);
  const handleClose = () => {
    props.history.push('/contract');
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

    const entity = {
      ...contractEntity,
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
        }
      : {
          status: 'NORMAL',
          ...contractEntity,
          dateStart: convertDateTimeFromServer(contractEntity.dateStart),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="debtManagementApp.contract.home.createOrEditLabel" data-cy="ContractCreateUpdateHeading">
            Create or edit a Contract
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="contract-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Date Start"
                id="contract-dateStart"
                name="dateStart"
                data-cy="dateStart"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Number Interest Payments"
                id="contract-numberInterestPayments"
                name="numberInterestPayments"
                data-cy="numberInterestPayments"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Total Loan Amount"
                id="contract-totalLoanAmount"
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
                id="contract-interestPaymentPeriod"
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
                id="contract-interestRate"
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
                id="contract-customerName"
                name="customerName"
                data-cy="customerName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Address"
                id="contract-customerAddress"
                name="customerAddress"
                data-cy="customerAddress"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Phone Number"
                id="contract-customerPhoneNumber"
                name="customerPhoneNumber"
                data-cy="customerPhoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Customer Identity Card"
                id="contract-customerIdentityCard"
                name="customerIdentityCard"
                data-cy="customerIdentityCard"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Product Name"
                id="contract-productName"
                name="productName"
                data-cy="productName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Imei" id="contract-imei" name="imei" data-cy="imei" type="text" />
              <ValidatedField label="Icloud" id="contract-icloud" name="icloud" data-cy="icloud" type="text" />
              <ValidatedField label="User Create" id="contract-userCreate" name="userCreate" data-cy="userCreate" type="text" />
              <ValidatedField label="Note" id="contract-note" name="note" data-cy="note" type="text" />
              <ValidatedField label="Status" id="contract-status" name="status" data-cy="status" type="select">
                {statusContractValues.map(statusContract => (
                  <option value={statusContract} key={statusContract}>
                    {statusContract}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Is Deleted" id="contract-isDeleted" name="isDeleted" data-cy="isDeleted" check type="checkbox" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract" replace color="info">
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

export default ContractUpdate;
