import React from 'react';
import Dropdown from '../Dropdown';
import './index.scss';

const GroupButton = props => {
  return (
    <div className="btn-group btn-debt-function-group">
      <i
        title="Sửa"
        className="fas fa-edit btn-debt-function"
        data-toggle="modal"
        data-target="#modal-xl-update-debt"
        onClick={e => {
          props.onEdit(props.data);
          console.warn(e);
        }}
      />
      <i title="Đóng lãi" className="fas fa-money-bill-wave btn-debt-function" data-toggle="modal" data-target="#modal-xl-pay-interest" />
      {/* <i title="Tất toán" className="fas fa-dollar-sign btn-debt-function" data-toggle="modal" data-target="#modal-xl-final-settlement" /> */}
      <i title="Trả gốc" className="fas fa-university btn-debt-function" data-toggle="modal" data-target="#modal-xl-pay-down-root" />
      <i title="Vay thêm" className="fas fa-hand-holding-medical btn-debt-function" data-toggle="modal" data-target="#modal-xl-more-debt" />
      <i title="Khác" className="fas fa-arrow-circle-down btn-debt-function" data-toggle="dropdown" aria-expanded="false" />
      <Dropdown />
      {/* <div className="btn-group">
        <button
          type="button"
          className="btn btn-default dropdown-toggle dropdown-icon"
          data-toggle="dropdown"
          aria-expanded="false"
        />
        <div className="dropdown-menu" style={{}}>
          <a className="dropdown-item" href="#">
            Dropdown link
          </a>
          <a className="dropdown-item" href="#">
            Dropdown link
          </a>
        </div>
      </div> */}

      {/* <button type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-edit"></i> Sửa
        </a>
      </button>
      <button placeholder="xxx" type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-money-bill-wave"></i> Đóng lãi
        </a>
      </button>
      <button type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-dollar-sign"></i> Tất toán
        </a>
      </button>
      <button type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-university"></i> Trả gốc
        </a>
      </button>
      <button type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-hand-holding-medical"></i> Vay thêm
        </a>
      </button>
      <button type="button" className="btn btn-small">
        <a class="btn btn-app">
          <i class="fas fa-arrow-circle-down"></i> Khác
        </a>
      </button> */}
    </div>
  );
};

export default GroupButton;
