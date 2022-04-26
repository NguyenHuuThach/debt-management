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
import { getEntity, updateEntity, createEntity, reset, getEntities } from '../../contract/contract.reducer';
import { APP_WHOLE_NUMBER_FORMAT } from 'app/config/constants';
import { formatNumber } from 'app/shared/util/string-utils';
import './DebtUpdate.scss';

export const DebtUpdate = (props: any) => {
  const [editValues, setEditValues] = useState({
    id: props.data.id,
    dateStart: props.data.dateStart,
    numberInterestPayments: props.data.numberInterestPayments,
    totalLoanAmount: props.data.totalLoanAmount,
    interestPaymentPeriod: props.data.interestPaymentPeriod,
    interestRate: props.data.interestRate,
    customerName: props.data.customerName,
    customerAddress: props.data.customerAddress,
    customerPhoneNumber: props.data.customerPhoneNumber,
    customerIdentityCard: props.data.customerIdentityCard,
    productName: props.data.productName,
    imei: props.data.imei,
    icloud: props.data.icloud,
    userCreate: props.data.userCreate,
    note: props.data.note,
    status: props.data.status,
  });
  useEffect(() => {
    setEditValues(props.data);
    // console.warn(props.data);
  }, [props.data]);

  const dispatch = useAppDispatch();
  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const loading = useAppSelector(state => state.contract.loading);
  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const statusContractValues = Object.keys(StatusContract);
  const handleClose = () => {
    handleSyncList();
  };

  useEffect(() => {
    dispatch(reset());
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // const saveEntity = values => {
  //   values.dateStart = convertDateTimeToServer(values.dateStart);
  //   values.totalLoanAmount = parseFloat(values.totalLoanAmount.replace(/,/g, ''));

  //   const entity = {
  //     ...props.data,
  //     ...values,
  //   };
  //   console.warn(entity);

  //   // dispatch(updateEntity(entity));
  // };

  const handleSubmit = e => {
    e.preventDefault();
    editValues.dateStart = convertDateTimeToServer(editValues.dateStart);
    // editValues.totalLoanAmount = parseFloat(editValues.totalLoanAmount.replace(/,/g, ''));

    const entity = {
      ...props.data,
      ...editValues,
    };
    // console.warn(entity);

    dispatch(updateEntity(entity));
  };
  const handleDateStartInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      dateStart: event.target.value,
    }));
  };

  const handleNumberInterestPaymentsInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      numberInterestPayments: event.target.value,
    }));
  };
  const handleTotalLoanAmountInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      totalLoanAmount: event.target.value,
    }));
  };

  const handleInterestPaymentPeriodInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      interestPaymentPeriod: event.target.value,
    }));
  };
  const handleInterestRateInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      interestRate: event.target.value,
    }));
  };

  const handleCustomerNameInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      customerName: event.target.value,
    }));
  };
  const handleCustomerAddressInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      customerAddress: event.target.value,
    }));
  };

  const handleCustomerPhoneNumberInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      customerPhoneNumber: event.target.value,
    }));
  };
  const handleCustomerIdentityCardInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      customerIdentityCard: event.target.value,
    }));
  };

  const handleProductNameInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      productName: event.target.value,
    }));
  };
  const handleImeiInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      imei: event.target.value,
    }));
  };

  const handleIcloudInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      icloud: event.target.value,
    }));
  };

  const handleUserCreateInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      userCreate: event.target.value,
    }));
  };
  const handleNoteInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      note: event.target.value,
    }));
  };

  const handleStatusInputChange = event => {
    event.persist();
    setEditValues(values => ({
      ...values,
      status: event.target.value,
    }));
  };

  const defaultValues = () => {
    return {
      ...props.data,
      dateStart: convertDateTimeFromServer(props.data.dateStart),
      totalLoanAmount: formatNumber(props.data.totalLoanAmount),
    };
  };

  return (
    <>
      <div className="modal fade" id="modal-xl-update-debt">
        <div className="modal-dialog modal-xl">
          <div className="modal-content">
            <div className="modal-header bg-secondary">
              <h4 className="modal-title">Tạo hợp đồng</h4>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div className="modal-body">
              <form onSubmit={handleSubmit}>
                <input
                  id="dateStart"
                  className="form-field"
                  type="datetime-local"
                  placeholder="First Name"
                  name="firstName"
                  defaultValue={editValues.dateStart}
                  onChange={handleDateStartInputChange}
                />
                <input
                  id="numberInterestPayments"
                  className="form-field"
                  type="number"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.numberInterestPayments}
                  onChange={handleNumberInterestPaymentsInputChange}
                />
                <input
                  id="totalLoanAmount"
                  className="form-field"
                  type="number"
                  placeholder="First Name"
                  name="firstName"
                  defaultValue={editValues.totalLoanAmount}
                  onChange={handleTotalLoanAmountInputChange}
                />
                <input
                  id="interestPaymentPeriod"
                  className="form-field"
                  type="number"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.interestPaymentPeriod}
                  onChange={handleInterestPaymentPeriodInputChange}
                />
                <input
                  id="interestRate"
                  className="form-field"
                  type="number"
                  placeholder="First Name"
                  name="firstName"
                  defaultValue={editValues.interestRate}
                  onChange={handleInterestRateInputChange}
                />
                <input
                  id="customerName"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.customerName}
                  onChange={handleCustomerNameInputChange}
                />
                <input
                  id="customerAddress"
                  className="form-field"
                  type="text"
                  placeholder="First Name"
                  name="firstName"
                  defaultValue={editValues.customerAddress}
                  onChange={handleCustomerAddressInputChange}
                />
                <input
                  id="customerPhoneNumber"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.customerPhoneNumber}
                  onChange={handleCustomerPhoneNumberInputChange}
                />
                <input
                  id="customerIdentityCard"
                  className="form-field"
                  type="text"
                  placeholder="First Name"
                  name="firstName"
                  defaultValue={editValues.customerIdentityCard}
                  onChange={handleCustomerIdentityCardInputChange}
                />
                <input
                  id="productName"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.productName}
                  onChange={handleProductNameInputChange}
                />
                <input
                  id="imei"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.imei}
                  onChange={handleImeiInputChange}
                />
                <input
                  id="icloud"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.icloud}
                  onChange={handleIcloudInputChange}
                />
                <input
                  id="userCreate"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.userCreate}
                  onChange={handleUserCreateInputChange}
                />
                <input
                  id="note"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.note}
                  onChange={handleNoteInputChange}
                />
                {/* <input
                  id="status"
                  className="form-field"
                  type="text"
                  placeholder="Last Name"
                  name="lastName"
                  defaultValue={editValues.status}
                  onChange={handleStatusInputChange}
                /> */}
                <button type="submit">SEND</button>
              </form>

              {/* <Row className="justify-content-center">
                <Col md="8">
                  {loading ? (
                    <p>Loading...</p>
                  ) : (
                    <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
                      <h5 className="text-center">THÔNG TIN KHÁCH VAY</h5>
                      <ValidatedField
                        label="Họ Tên Khách Hàng"
                        id="contract-customerName"
                        name="customerName"
                        data-cy="customerName"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                        }}
                      />
                      <ValidatedField
                        label="Số Điện Thoại"
                        id="contract-customerPhoneNumber"
                        name="customerPhoneNumber"
                        data-cy="customerPhoneNumber"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                        }}
                      />
                      <ValidatedField
                        label="Căn Cước/CMND"
                        id="contract-customerIdentityCard"
                        name="customerIdentityCard"
                        data-cy="customerIdentityCard"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                        }}
                      />
                      <ValidatedField
                        label="Địa Chỉ"
                        id="contract-customerAddress"
                        name="customerAddress"
                        data-cy="customerAddress"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                        }}
                      />
                      <h5 className="text-center">THÔNG TIN CHO VAY</h5>
                      <ValidatedField
                        label="Ngày Vay"
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
                        label="Tổng Tiền Vay(VND)"
                        id="contract-totalLoanAmount"
                        name="totalLoanAmount"
                        data-cy="totalLoanAmount"
                        data-type="currency"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                        }}
                      />
                      <ValidatedField
                        label="Kỳ Đóng Lãi(ngày)"
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
                        label="Số Lần Đóng Lãi"
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
                        label="Lãi Suất(%)"
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
                        label="Người Tạo Hợp Đồng"
                        id="contract-userCreate"
                        name="userCreate"
                        data-cy="userCreate"
                        type="text"
                      />
                      <h5 className="text-center">THÔNG TIN TÀI SẢN</h5>
                      <ValidatedField
                        label="Sản Phẩm"
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

                      <ValidatedField label="Ghi Chú" id="contract-note" name="note" data-cy="note" type="text" />
                      <ValidatedField label="Status" id="contract-status" name="status" data-cy="status" type="select">
                        {statusContractValues.map(statusContract => (
                          <option value={statusContract} key={statusContract}>
                            {statusContract}
                          </option>
                        ))}
                      </ValidatedField>

                      <div className="d-flex justify-content-end">
                        <Button id="close-modal-xl-update-debt" data-dismiss="modal" color="info">
                          <FontAwesomeIcon icon="arrow-left" />
                          &nbsp; Đóng
                        </Button>
                        &nbsp;
                        <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                          <FontAwesomeIcon icon="save" />
                          &nbsp; Gửi
                        </Button>
                      </div>
                    </ValidatedForm>
                  )}
                </Col>
              </Row> */}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default DebtUpdate;
