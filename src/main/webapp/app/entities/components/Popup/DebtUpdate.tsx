import React, { useState, useEffect } from 'react';
import { Button } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeToServer, displayDefaultDate } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { StatusContract } from 'app/shared/model/enumerations/status-contract.model';
import { updateEntity, getEntities } from '../../contract/contract.reducer';
import { formatNumber } from 'app/shared/util/string-utils';
import './DebtUpdate.scss';
import DatePicker, { registerLocale } from 'react-datepicker';
import vi from 'date-fns/locale/vi';
registerLocale('vi', vi);
import 'react-datepicker/dist/react-datepicker.css';
import { useForm, SubmitHandler } from 'react-hook-form';
import { useMemo } from 'react';

type Inputs = {
  id?: number;
  dateStart?: any;
  numberInterestPayments?: number;
  totalLoanAmount?: number;
  interestPaymentPeriod?: number;
  interestRate?: number;
  customerName?: string;
  customerAddress?: string;
  customerPhoneNumber?: string;
  customerIdentityCard?: string;
  productName?: string;
  imei?: string | null;
  icloud?: string | null;
  userCreate?: string | null;
  note?: string | null;
  status?: StatusContract | null;
  isDeleted?: boolean | null;
};

export const DebtUpdate = (props: any) => {
  const [editValues, setEditValues] = useState({
    dateStart: props.data.dateStart,
  });

  useEffect(() => {
    setEditValues({ dateStart: props.data.dateStart });
    reset({
      ...props.data,
      totalLoanAmount: formatNumber(props.data.totalLoanAmount),
      dateStart: displayDefaultDate(props.data.dateStart),
    });
  }, [props.data]);

  const dispatch = useAppDispatch();
  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const statusContractValues = Object.keys(StatusContract);
  const handleClose = () => {
    handleSyncList();
  };

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const handleDateStartInputChange = (value, event) => {
    event.preventDefault();
    setEditValues(values => ({
      ...values,
      dateStart: new Date(convertDateTimeToServer(value)).toLocaleDateString(),
    }));
  };

  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>({
    defaultValues: useMemo(() => {
      return props.data;
    }, [props]),
  });
  const onSubmit: SubmitHandler<Inputs> = data => {
    data.dateStart = convertDateTimeToServer(editValues.dateStart);
    data.totalLoanAmount = parseFloat(`${data.totalLoanAmount}`.replace(/,/g, ''));

    const entity = {
      ...props.data,
      ...data,
    };

    dispatch(updateEntity(entity));
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
              <form onSubmit={handleSubmit(onSubmit)}>
                <h5 className="text-center">THÔNG TIN KHÁCH VAY</h5>
                <div className="row mb-3">
                  <div className="col-sm-12 col-md-6">
                    <div className="d-flex flex-column">
                      <div className="mb-2">
                        <label htmlFor="customer-name-debt-create" className="label-debt-create">
                          Họ tên:
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="customer-name-debt-create"
                          placeholder="Nhập họ tên"
                          {...register('customerName', { required: true, minLength: 5, maxLength: 50 })}
                          // onChange={handleCustomerNameInputChange}
                        />
                        {Object.keys(errors).length > 0 && errors.customerName && (
                          <span className="warn-mess-input">Mời bạn nhập tên khách hàng!</span>
                        )}
                      </div>
                    </div>
                    <div className="mb-2">
                      <label htmlFor="phone-debt-create" className="label-debt-create">
                        SĐT:
                      </label>
                      <input
                        type="tel"
                        className="form-control"
                        id="phone-debt-create"
                        placeholder="Nhập sđt"
                        {...register('customerPhoneNumber', {
                          required: true,
                          minLength: 10,
                          maxLength: 20,
                          pattern: /^(0|[1-9]\d*)(\.\d+)?$/,
                        })}
                        // onChange={handleCustomerPhoneNumberInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.customerPhoneNumber?.type === 'required' ? (
                          <span className="warn-mess-input">Mời bạn nhập số điện thoại khách hàng!</span>
                        ) : errors.customerPhoneNumber?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : errors.customerPhoneNumber?.type === 'minLength' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="cmnd-debt-create" className="label-debt-create">
                        Căn cước/CMND:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="cmnd-debt-create"
                        placeholder="Nhập CMND/CCCD"
                        {...register('customerIdentityCard', { required: true })}
                        // onChange={handleCustomerIdentityCardInputChange}
                      />
                      {Object.keys(errors).length > 0 && errors.customerIdentityCard && (
                        <span className="warn-mess-input">Mời bạn nhập căn cước/CMND khách hàng!</span>
                      )}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="address-debt-create" className="label-debt-create">
                        Địa chỉ:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="address-debt-create"
                        placeholder="Nhập địa chỉ"
                        {...register('customerAddress', { required: true })}
                        // onChange={handleCustomerAddressInputChange}
                      />
                      {Object.keys(errors).length > 0 && errors.customerAddress && (
                        <span className="warn-mess-input">Mời bạn nhập địa chỉ khách hàng!</span>
                      )}
                    </div>
                  </div>
                  <div className="col-sm-12 col-md-6"></div>
                </div>
                <h5 className="text-center">THÔNG TIN TÀI SẢN</h5>
                <div className="row mb-3">
                  <div className="col-sm-12 col-md-6">
                    <div className="d-flex flex-column">
                      <div className="mb-2">
                        <label htmlFor="product-name-debt-create" className="label-debt-create">
                          Tên tài sản:
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="product-name-debt-create"
                          placeholder="Nhập tên tài sản"
                          {...register('productName', { required: true })}
                          // onChange={handleProductNameInputChange}
                        />
                        {Object.keys(errors).length > 0 && errors.productName && (
                          <span className="warn-mess-input">Mời bạn nhập tên tài sản!</span>
                        )}
                      </div>
                    </div>
                    <div className="mb-2">
                      <label htmlFor="imei-debt-create" className="label-debt-create">
                        IMEI:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="imei-debt-create"
                        placeholder="Nhập IMEI"
                        {...register('imei')}
                        // onChange={handleImeiInputChange}
                      />
                    </div>
                    <div className="mb-2">
                      <label htmlFor="icloud-debt-create" className="label-debt-create">
                        Icloud:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="icloud-debt-create"
                        placeholder="Nhập Icloud"
                        {...register('icloud')}
                        // onChange={handleIcloudInputChange}
                      />
                    </div>
                  </div>
                  <div className="col-sm-12 col-md-6"></div>
                </div>
                <h5 className="text-center">THÔNG TIN CHO VAY</h5>
                <div className="row mb-3">
                  <div className="col-12">
                    <div className="mb-2">
                      <label htmlFor="date-debt-create" className="label-debt-create">
                        Ngày vay:
                      </label>
                      <DatePicker
                        dateFormat="dd-MM-yyyy"
                        id="date-debt-create"
                        locale="vi"
                        className="form-control"
                        selected={Date.parse(editValues.dateStart)}
                        onChange={handleDateStartInputChange}
                      />
                    </div>
                    <div className="mb-2">
                      <label htmlFor="total-loan-debt-create" className="label-debt-create">
                        Tổng tiền vay:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="contract-totalLoanAmount"
                        placeholder="Nhập tổng tiền vay"
                        {...register('totalLoanAmount', { required: true, minLength: 7, maxLength: 10 })}
                        // onChange={handleTotalLoanAmountInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.totalLoanAmount?.type === 'required' ? (
                          <span className="warn-mess-input">Mời bạn nhập số tiền cho vay!</span>
                        ) : errors.totalLoanAmount?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : errors.totalLoanAmount?.type === 'minLength' ? (
                          <span className="warn-mess-input">Số tiền quá nhỏ!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="interest-payment-period-debt-create" className="label-debt-create">
                        Kỳ đóng lãi(ngày):
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="interest-payment-period-debt-create"
                        placeholder="Nhập kỳ đóng lãi"
                        {...register('interestPaymentPeriod', { required: true, maxLength: 2, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleInterestPaymentPeriodInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.interestPaymentPeriod?.type === 'required' ? (
                          <span className="warn-mess-input">Mời bạn nhập kỳ đóng lãi!</span>
                        ) : errors.interestPaymentPeriod?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : errors.interestPaymentPeriod?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="interest-rate-debt-create" className="label-debt-create">
                        Lãi suất(%):
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="interest-rate-debt-create"
                        placeholder="Nhập lãi suất"
                        {...register('interestRate', { required: true, maxLength: 3, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleInterestRateInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.interestRate?.type === 'required' ? (
                          <span className="warn-mess-input">Mời bạn nhập lãi suất!</span>
                        ) : errors.interestRate?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : errors.interestRate?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="number-interest-payments-debt-create" className="label-debt-create">
                        Số lần đóng lãi:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="number-interest-payments-debt-create"
                        placeholder="Nhập số lần trả"
                        {...register('numberInterestPayments', { required: true, maxLength: 3, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleNumberInterestPaymentsInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.numberInterestPayments?.type === 'required' ? (
                          <span className="warn-mess-input">Mời bạn nhập số lần đóng lãi!</span>
                        ) : errors.numberInterestPayments?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : errors.numberInterestPayments?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui lòng nhập đàng hoàng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="user-create-debt-create" className="label-debt-create">
                        Người tạo:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="user-create-debt-create"
                        placeholder="Nhập tên người tạo"
                        {...register('userCreate')}
                        // onChange={handleUserCreateInputChange}
                      />
                    </div>
                    <div className="mb-2">
                      <label htmlFor="note-debt-create" className="label-debt-create">
                        Ghi chú:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="note-debt-create"
                        placeholder="Ghi chú"
                        {...register('note')}
                        // onChange={handleNoteInputChange}
                      />
                    </div>
                  </div>
                </div>
                <div className="d-flex justify-content-end">
                  <Button id="close-modal-xl-update-debt" data-dismiss="modal" color="info">
                    <FontAwesomeIcon icon="arrow-left" />
                    &nbsp; Đóng
                  </Button>
                  &nbsp;
                  <Button color="primary" type="submit" disabled={updating}>
                    <FontAwesomeIcon icon="save" />
                    &nbsp; Gửi
                  </Button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default DebtUpdate;
