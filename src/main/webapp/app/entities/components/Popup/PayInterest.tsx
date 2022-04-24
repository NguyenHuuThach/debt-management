import React from 'react';
import Helmet from 'react-helmet';
import DatatablePayInterestList from '../Datatable/DatatablePayInterestList';

const PayInterest = () => {
  return (
    <>
      <div className="modal fade" id="modal-xl-pay-interest">
        <div className="modal-dialog modal-xl">
          <div className="modal-content">
            <div className="modal-header bg-secondary">
              <h4 className="modal-title">Đóng lãi</h4>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div className="modal-body">
              <DatatablePayInterestList />
              <div className="card-body">
                <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Ngày thanh toán</label>
                  <div className="input-group date" id="reservationDatePayInterest" data-target-input="nearest">
                    <input type="text" className="form-control datetimepicker-input" data-target="#reservationDatePayInterest" />
                    <div className="input-group-append" data-target="#reservationDatePayInterest" data-toggle="datetimepicker">
                      <div className="input-group-text">
                        <i className="fa fa-calendar" />
                      </div>
                    </div>
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Người thanh toán</label>
                  <input
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    // placeholder="Password"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputFile">Tiền thanh toán</label>
                  <input
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    // placeholder="Password"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Phí khác</label>
                  <input
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    // placeholder="Password"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Ghi chú</label>
                  <input
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    // placeholder="Password"
                  />
                </div>
                <div className="form-check">
                  <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                  <label className="form-check-label" htmlFor="exampleCheck1">
                    Đổi kỳ thanh toán
                  </label>
                </div>
              </div>
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
          {/* /.modal-content */}
        </div>
        {/* /.modal-dialog */}
      </div>
    </>
  );
};

export default PayInterest;
