// import React from 'react';
// import './DebtCreate.scss';

// const DebtCreate = () => {
//   return (
//     <>
//       <div className="modal fade" id="modal-xl-create-debt">
//         <div className="modal-dialog modal-xl">
//           <div className="modal-content">
//             <div className="modal-header bg-secondary">
//               <h4 className="modal-title">Tạo hợp đồng</h4>
//               <button type="button" className="close" data-dismiss="modal" aria-label="Close">
//                 <span aria-hidden="true">×</span>
//               </button>
//             </div>
//             <div className="modal-body">
//               <h5>Thông tin khách vay</h5>
//               <div className="row mb-3">
//                 <div className="col-6">
//                   <div className="d-flex flex-column">
//                     <div className="d-flex mb-2 align-items-end">
//                       <label htmlFor="customer-name-debt-create" className="label-debt-create">
//                         Họ tên:
//                       </label>
//                       <input type="text" className="form-control" id="customer-name-debt-create" placeholder="Nhập họ tên" />
//                     </div>
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="phone-debt-create" className="label-debt-create">
//                       SĐT:
//                     </label>
//                     <input type="number" className="form-control" id="phone-debt-create" placeholder="Nhập sđt" />
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="cmnd-debt-create" className="label-debt-create">
//                       Căn cước/CMND:
//                     </label>
//                     <input type="text" className="form-control" id="cmnd-debt-create" placeholder="Nhập CMND/CCCD" />
//                   </div>
//                   <div className="d-flex align-items-end">
//                     <label htmlFor="address-debt-create" className="label-debt-create">
//                       Địa chỉ:
//                     </label>
//                     <input type="text" className="form-control" id="address-debt-create" placeholder="Nhập địa chỉ" />
//                   </div>
//                 </div>
//                 <div className="col-6">{/* Khúc này thêm hình */}</div>
//               </div>
//               <h5>Thông tin cho vay</h5>
//               <div className="row mb-3">
//                 <div className="col-6">
//                   {/* <div className="d-flex flex-column">
//                     <div className="d-flex mb-2 align-items-end">
//                       <label
//                         htmlFor="name-debt-create"
//                         className="label-debt-create"
//                       >
//                         Tên tài sản:
//                       </label>
//                       <input
//                         type="text"
//                         className="form-control"
//                         id="name-debt-create"
//                         placeholder="Nhập tên tài sản"
//                       />
//                     </div>
//                   </div> */}
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="date-debt-create" className="label-debt-create">
//                       Ngày vay:
//                     </label>
//                     <div className="input-group date" id="reservationDateDebtCreate" data-target-input="nearest">
//                       <input
//                         type="text"
//                         id="date-debt-create"
//                         className="form-control datetimepicker-input"
//                         data-target="#reservationDateDebtCreate"
//                       />
//                       <div className="input-group-append" data-target="#reservationDateDebtCreate" data-toggle="datetimepicker">
//                         <div className="input-group-text">
//                           <i className="fa fa-calendar" />
//                         </div>
//                       </div>
//                     </div>

//                     {/* <input
//                     type="number"
//                     className="form-control"
//                     id="phone-debt-create"
//                     placeholder=""
//                   /> */}
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="total-loan-debt-create" className="label-debt-create">
//                       Tổng tiền vay:
//                     </label>
//                     <input type="text" className="form-control" id="total-loan-debt-create" placeholder="Nhập tổng tiền vay" />
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="interest-payment-period-debt-create" className="label-debt-create">
//                       Kỳ đóng lãi(ngày):
//                     </label>
//                     <input type="text" className="form-control" id="interest-payment-period-debt-create" placeholder="Nhập kỳ đóng lãi" />
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="interest-rate-debt-create" className="label-debt-create">
//                       Lãi suất(%):
//                     </label>
//                     <input type="text" className="form-control" id="interest-rate-debt-create" placeholder="Nhập lãi suất" />
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="number-interest-payments-debt-create" className="label-debt-create">
//                       Số lần trả:
//                     </label>
//                     <input type="text" className="form-control" id="number-interest-payments-debt-create" placeholder="Nhập số lần trả" />
//                   </div>
//                   {/* <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="cmnd-debt-create" className="label-debt-create">
//                       Kiểu thu lãi:
//                     </label>
//                     <input type="text" className="form-control" id="cmnd-debt-create" placeholder="Nhập kiểu thu lãi" />
//                   </div> */}
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="user-create-debt-create" className="label-debt-create">
//                       Người tạo:
//                     </label>
//                     <input type="text" className="form-control" id="user-create-debt-create" placeholder="Nhập tên người tạo" />
//                   </div>
//                   <div className="d-flex align-items-end">
//                     <label htmlFor="note-debt-create" className="label-debt-create">
//                       Ghi chú:
//                     </label>
//                     <input type="text" className="form-control" id="note-debt-create" placeholder="Ghi chú" />
//                   </div>
//                 </div>
//               </div>
//               {/* <h5>Các loại phí</h5>
//               <div className="row mb-3"></div> */}
//               <h5>Thông tin tài sản thế chấp</h5>
//               <div className="row mb-3">
//                 <div className="col-6">
//                   <div className="d-flex flex-column">
//                     <div className="d-flex mb-2 align-items-end">
//                       <label htmlFor="product-name-debt-create" className="label-debt-create">
//                         Tên tài sản:
//                       </label>
//                       <input type="text" className="form-control" id="product-name-debt-create" placeholder="Nhập tên tài sản" />
//                     </div>
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="imei-debt-create" className="label-debt-create">
//                       IMEI:
//                     </label>
//                     <input type="text" className="form-control" id="imei-debt-create" placeholder="Nhập IMEI" />
//                   </div>
//                   <div className="d-flex mb-2 align-items-end">
//                     <label htmlFor="icloud-debt-create" className="label-debt-create">
//                       Icloud:
//                     </label>
//                     <input type="text" className="form-control" id="icloud-debt-create" placeholder="Nhập Icloud" />
//                   </div>
//                 </div>
//               </div>
//               {/* <h5>Đính kèm file</h5>
//               <div className="row"></div> */}
//             </div>
//             <div className="modal-footer justify-content-flex-end">
//               <button type="button" className="btn btn-default" data-dismiss="modal">
//                 Đóng
//               </button>
//               <button type="button" className="btn btn-primary">
//                 Gửi
//               </button>
//             </div>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// };

// export default DebtCreate;

import './DebtCreate.scss';
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

export const DebtCreate = () => {
  const dispatch = useAppDispatch();
  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const contractEntity = useAppSelector(state => state.contract.entity);
  const loading = useAppSelector(state => state.contract.loading);
  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const statusContractValues = Object.keys(StatusContract);
  const handleClose = () => {
    // document.history.push('/contract');
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

  const saveEntity = values => {
    values.dateStart = convertDateTimeToServer(values.dateStart);
    values.totalLoanAmount = parseFloat(values.totalLoanAmount.replace(/,/g, ''));

    const entity = {
      ...contractEntity,
      ...values,
    };

    dispatch(createEntity(entity));
    // document.getElementById('close-modal-xl-create-debt').click();
  };

  const defaultValues = () => {
    return { dateStart: displayDefaultDateTime(), status: 'NORMAL', isDeleted: false };
  };

  return (
    <>
      <div className="modal fade" id="modal-xl-create-debt">
        <div className="modal-dialog modal-xl">
          <div className="modal-content">
            <div className="modal-header bg-secondary">
              <h4 className="modal-title">Tạo hợp đồng</h4>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div className="modal-body">
              <Row className="justify-content-center">
                <Col md="8">
                  {loading ? (
                    <p>Loading...</p>
                  ) : (
                    <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
                      {/* {!isNew ? (
                        <ValidatedField name="id" required readOnly id="contract-id" label="ID" validate={{ required: true }} />
                      ) : null} */}
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
                      {/* <div className="mb-3">
                        <label htmlFor="total-loan-debt-create">Tổng tiền vay:</label>
                        <input
                          id="contract-totalLoanAmount"
                          name="totalLoanAmount"
                          data-cy="totalLoanAmount"
                          data-type="currency"
                          type="text"
                          className="form-control"
                          placeholder="Nhập tổng tiền vay"
                        />
                      </div> */}

                      <ValidatedField
                        label="Tổng Tiền Vay(VND)"
                        id="contract-totalLoanAmount"
                        name="totalLoanAmount"
                        data-cy="totalLoanAmount"
                        data-type="currency"
                        type="text"
                        validate={{
                          required: { value: true, message: 'This field is required.' },
                          // validate: v => isNumber(v) || 'This field should be a number.',
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
                      <ValidatedField
                        label="Người Tạo Hợp Đồng"
                        id="contract-userCreate"
                        name="userCreate"
                        data-cy="userCreate"
                        type="text"
                      />
                      <ValidatedField label="Ghi Chú" id="contract-note" name="note" data-cy="note" type="text" />
                      {/* <ValidatedField label="Status" id="contract-status" name="status" data-cy="status" type="select">
                        {statusContractValues.map(statusContract => (
                          <option value={statusContract} key={statusContract}>
                            {statusContract}
                          </option>
                        ))}
                      </ValidatedField> */}
                      {/* <ValidatedField
                        label="Is Deleted"
                        id="contract-isDeleted"
                        name="isDeleted"
                        data-cy="isDeleted"
                        check
                        type="checkbox"
                      /> */}
                      {/* <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract" replace color="info">
                        <FontAwesomeIcon icon="arrow-left" />
                        &nbsp;
                        <span className="d-none d-md-inline">Back</span>
                      </Button> */}
                      <div className="d-flex justify-content-end">
                        <Button id="close-modal-xl-create-debt" data-dismiss="modal" color="info">
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
              </Row>
            </div>
            {/* <div className="modal-footer justify-content-flex-end">
              <button type="button" className="btn btn-default" data-dismiss="modal">
                Đóng
              </button>
              <button type="button" className="btn btn-primary">
                Gửi
              </button>
            </div> */}
          </div>
        </div>
      </div>
    </>
  );
};

export default DebtCreate;
