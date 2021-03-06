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
              <h4 className="modal-title">T???o h???p ?????ng</h4>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">??</span>
              </button>
            </div>
            <div className="modal-body">
              <form onSubmit={handleSubmit(onSubmit)}>
                <h5 className="text-center">TH??NG TIN KH??CH VAY</h5>
                <div className="row mb-3">
                  <div className="col-sm-12 col-md-6">
                    <div className="d-flex flex-column">
                      <div className="mb-2">
                        <label htmlFor="customer-name-debt-create" className="label-debt-create">
                          H??? t??n:
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="customer-name-debt-create"
                          placeholder="Nh???p h??? t??n"
                          {...register('customerName', { required: true, minLength: 5, maxLength: 50 })}
                          // onChange={handleCustomerNameInputChange}
                        />
                        {Object.keys(errors).length > 0 && errors.customerName && (
                          <span className="warn-mess-input">M???i b???n nh???p t??n kh??ch h??ng!</span>
                        )}
                      </div>
                    </div>
                    <div className="mb-2">
                      <label htmlFor="phone-debt-create" className="label-debt-create">
                        S??T:
                      </label>
                      <input
                        type="tel"
                        className="form-control"
                        id="phone-debt-create"
                        placeholder="Nh???p s??t"
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
                          <span className="warn-mess-input">M???i b???n nh???p s??? ??i???n tho???i kh??ch h??ng!</span>
                        ) : errors.customerPhoneNumber?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : errors.customerPhoneNumber?.type === 'minLength' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="cmnd-debt-create" className="label-debt-create">
                        C??n c?????c/CMND:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="cmnd-debt-create"
                        placeholder="Nh???p CMND/CCCD"
                        {...register('customerIdentityCard', { required: true })}
                        // onChange={handleCustomerIdentityCardInputChange}
                      />
                      {Object.keys(errors).length > 0 && errors.customerIdentityCard && (
                        <span className="warn-mess-input">M???i b???n nh???p c??n c?????c/CMND kh??ch h??ng!</span>
                      )}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="address-debt-create" className="label-debt-create">
                        ?????a ch???:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="address-debt-create"
                        placeholder="Nh???p ?????a ch???"
                        {...register('customerAddress', { required: true })}
                        // onChange={handleCustomerAddressInputChange}
                      />
                      {Object.keys(errors).length > 0 && errors.customerAddress && (
                        <span className="warn-mess-input">M???i b???n nh???p ?????a ch??? kh??ch h??ng!</span>
                      )}
                    </div>
                  </div>
                  <div className="col-sm-12 col-md-6"></div>
                </div>
                <h5 className="text-center">TH??NG TIN T??I S???N</h5>
                <div className="row mb-3">
                  <div className="col-sm-12 col-md-6">
                    <div className="d-flex flex-column">
                      <div className="mb-2">
                        <label htmlFor="product-name-debt-create" className="label-debt-create">
                          T??n t??i s???n:
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="product-name-debt-create"
                          placeholder="Nh???p t??n t??i s???n"
                          {...register('productName', { required: true })}
                          // onChange={handleProductNameInputChange}
                        />
                        {Object.keys(errors).length > 0 && errors.productName && (
                          <span className="warn-mess-input">M???i b???n nh???p t??n t??i s???n!</span>
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
                        placeholder="Nh???p IMEI"
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
                        placeholder="Nh???p Icloud"
                        {...register('icloud')}
                        // onChange={handleIcloudInputChange}
                      />
                    </div>
                  </div>
                  <div className="col-sm-12 col-md-6"></div>
                </div>
                <h5 className="text-center">TH??NG TIN CHO VAY</h5>
                <div className="row mb-3">
                  <div className="col-12">
                    <div className="mb-2">
                      <label htmlFor="date-debt-create" className="label-debt-create">
                        Ng??y vay:
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
                        T???ng ti???n vay:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="contract-totalLoanAmount"
                        placeholder="Nh???p t???ng ti???n vay"
                        {...register('totalLoanAmount', { required: true, minLength: 7, maxLength: 10 })}
                        // onChange={handleTotalLoanAmountInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.totalLoanAmount?.type === 'required' ? (
                          <span className="warn-mess-input">M???i b???n nh???p s??? ti???n cho vay!</span>
                        ) : errors.totalLoanAmount?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : errors.totalLoanAmount?.type === 'minLength' ? (
                          <span className="warn-mess-input">S??? ti???n qu?? nh???!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="interest-payment-period-debt-create" className="label-debt-create">
                        K??? ????ng l??i(ng??y):
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="interest-payment-period-debt-create"
                        placeholder="Nh???p k??? ????ng l??i"
                        {...register('interestPaymentPeriod', { required: true, maxLength: 2, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleInterestPaymentPeriodInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.interestPaymentPeriod?.type === 'required' ? (
                          <span className="warn-mess-input">M???i b???n nh???p k??? ????ng l??i!</span>
                        ) : errors.interestPaymentPeriod?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : errors.interestPaymentPeriod?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="interest-rate-debt-create" className="label-debt-create">
                        L??i su???t(%):
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="interest-rate-debt-create"
                        placeholder="Nh???p l??i su???t"
                        {...register('interestRate', { required: true, maxLength: 3, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleInterestRateInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.interestRate?.type === 'required' ? (
                          <span className="warn-mess-input">M???i b???n nh???p l??i su???t!</span>
                        ) : errors.interestRate?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : errors.interestRate?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="number-interest-payments-debt-create" className="label-debt-create">
                        S??? l???n ????ng l??i:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="number-interest-payments-debt-create"
                        placeholder="Nh???p s??? l???n tr???"
                        {...register('numberInterestPayments', { required: true, maxLength: 3, pattern: /^(0|[1-9]\d*)(\.\d+)?$/ })}
                        // onChange={handleNumberInterestPaymentsInputChange}
                      />
                      {Object.keys(errors).length > 0 &&
                        (errors.numberInterestPayments?.type === 'required' ? (
                          <span className="warn-mess-input">M???i b???n nh???p s??? l???n ????ng l??i!</span>
                        ) : errors.numberInterestPayments?.type === 'pattern' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : errors.numberInterestPayments?.type === 'maxLength' ? (
                          <span className="warn-mess-input">Vui l??ng nh???p ????ng ho??ng!</span>
                        ) : (
                          <span></span>
                        ))}
                    </div>
                    <div className="mb-2">
                      <label htmlFor="user-create-debt-create" className="label-debt-create">
                        Ng?????i t???o:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="user-create-debt-create"
                        placeholder="Nh???p t??n ng?????i t???o"
                        {...register('userCreate')}
                        // onChange={handleUserCreateInputChange}
                      />
                    </div>
                    <div className="mb-2">
                      <label htmlFor="note-debt-create" className="label-debt-create">
                        Ghi ch??:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="note-debt-create"
                        placeholder="Ghi ch??"
                        {...register('note')}
                        // onChange={handleNoteInputChange}
                      />
                    </div>
                  </div>
                </div>
                <div className="d-flex justify-content-end">
                  <Button id="close-modal-xl-update-debt" data-dismiss="modal" color="info">
                    <FontAwesomeIcon icon="arrow-left" />
                    &nbsp; ????ng
                  </Button>
                  &nbsp;
                  <Button color="primary" type="submit" disabled={updating}>
                    <FontAwesomeIcon icon="save" />
                    &nbsp; G???i
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
