import React from 'react';
import './DebtUpdate.scss';

const DebtUpdate = () => {
  return (
    <>
      <div className="modal fade" id="modal-xl-update-debt">
        <div className="modal-dialog modal-xl">
          <div className="modal-content">
            <div className="modal-header bg-secondary">
              <h4 className="modal-title">Sửa hợp đồng</h4>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div className="modal-body">
              <h5>Thông tin khách vay</h5>
              <div className="row mb-3">
                <div className="col-6">
                  <div className="d-flex flex-column">
                    <div className="d-flex mb-2 align-items-end">
                      <label htmlFor="customer-name-debt-update" className="label-debt-update">
                        Họ tên:
                      </label>
                      <input type="text" className="form-control" id="customer-name-debt-update" placeholder="Nhập họ tên" />
                    </div>
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="phone-debt-update" className="label-debt-update">
                      SĐT:
                    </label>
                    <input type="number" className="form-control" id="phone-debt-update" placeholder="Nhập sđt" />
                  </div>
                  <div className="d-flex align-items-end">
                    <label htmlFor="cmnd-debt-update" className="label-debt-update">
                      Căn cước/CMND:
                    </label>
                    <input type="text" className="form-control" id="cmnd-debt-update" placeholder="Nhập CMND/CCCD" />
                  </div>
                  <div className="d-flex align-items-end">
                    <label htmlFor="address-debt-update" className="label-debt-update">
                      Địa chỉ:
                    </label>
                    <input type="text" className="form-control" id="address-debt-update" placeholder="Nhập địa chỉ" />
                  </div>
                </div>
                <div className="col-6">{/* Khúc này thêm hình */}</div>
              </div>
              <h5>Thông tin cho vay</h5>
              <div className="row mb-3">
                <div className="col-6">
                  {/* <div className="d-flex flex-column">
                    <div className="d-flex mb-2 align-items-end">
                      <label
                        htmlFor="name-debt-update"
                        className="label-debt-update"
                      >
                        Tên tài sản:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="name-debt-update"
                        placeholder="Nhập tên tài sản"
                      />
                    </div>
                  </div> */}
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="date-debt-update" className="label-debt-update">
                      Ngày vay:
                    </label>
                    <div className="input-group date" id="reservationDateDebtUpdate" data-target-input="nearest">
                      <input
                        type="text"
                        id="date-debt-update"
                        className="form-control datetimepicker-input"
                        data-target="#reservationDateDebtUpdate"
                      />
                      <div className="input-group-append" data-target="#reservationDateDebtUpdate" data-toggle="datetimepicker">
                        <div className="input-group-text">
                          <i className="fa fa-calendar" />
                        </div>
                      </div>
                    </div>

                    {/* <input
                    type="number"
                    className="form-control"
                    id="phone-debt-update"
                    placeholder=""
                  /> */}
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="total-loan-debt-update" className="label-debt-update">
                      Tổng tiền vay:
                    </label>
                    <input type="text" className="form-control" id="total-loan-debt-update" placeholder="Nhập tổng tiền vay" />
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="interest-payment-period-debt-update" className="label-debt-update">
                      Kỳ đóng lãi(ngày):
                    </label>
                    <input type="text" className="form-control" id="interest-payment-period-debt-update" placeholder="Nhập kỳ đóng lãi" />
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="interest-rate-debt-update" className="label-debt-update">
                      Lãi suất(%):
                    </label>
                    <input type="text" className="form-control" id="interest-rate-debt-update" placeholder="Nhập lãi suất" />
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="number-interest-payments-debt-update" className="label-debt-update">
                      Số lần trả:
                    </label>
                    <input type="text" className="form-control" id="number-interest-payments-debt-update" placeholder="Nhập số lần trả" />
                  </div>
                  {/* <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="cmnd-debt-update" className="label-debt-update">
                      Kiểu thu lãi:
                    </label>
                    <input type="text" className="form-control" id="cmnd-debt-update" placeholder="Nhập kiểu thu lãi" />
                  </div> */}
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="user-update-debt-update" className="label-debt-update">
                      Người tạo:
                    </label>
                    <input type="text" className="form-control" id="user-update-debt-update" placeholder="Nhập tên người tạo" />
                  </div>
                  <div className="d-flex align-items-end">
                    <label htmlFor="note-debt-update" className="label-debt-update">
                      Ghi chú:
                    </label>
                    <input type="text" className="form-control" id="note-debt-update" placeholder="Ghi chú" />
                  </div>
                </div>
              </div>
              {/* <h5>Các loại phí</h5>
              <div className="row mb-3"></div> */}
              <h5>Thông tin tài sản thế chấp</h5>
              <div className="row mb-3">
                <div className="col-6">
                  <div className="d-flex flex-column">
                    <div className="d-flex mb-2 align-items-end">
                      <label htmlFor="product-name-debt-update" className="label-debt-update">
                        Tên tài sản:
                      </label>
                      <input type="text" className="form-control" id="product-name-debt-update" placeholder="Nhập tên tài sản" />
                    </div>
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="imei-debt-update" className="label-debt-update">
                      IMEI:
                    </label>
                    <input type="text" className="form-control" id="imei-debt-update" placeholder="Nhập IMEI" />
                  </div>
                  <div className="d-flex mb-2 align-items-end">
                    <label htmlFor="icloud-debt-update" className="label-debt-update">
                      Icloud:
                    </label>
                    <input type="text" className="form-control" id="icloud-debt-update" placeholder="Nhập Icloud" />
                  </div>
                </div>
              </div>
              {/* <h5>Đính kèm file</h5>
              <div className="row"></div> */}
            </div>
            <div className="modal-footer justify-content-flex-end">
              <button type="button" className="btn btn-default" data-dismiss="modal">
                Đóng
              </button>
              <button type="button" className="btn btn-primary">
                Gửi
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default DebtUpdate;
