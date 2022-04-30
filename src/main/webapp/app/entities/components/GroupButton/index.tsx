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
        }}
      />

      <i title="Đóng lãi" className="fas fa-money-bill-wave btn-debt-function" data-toggle="modal" data-target="#modal-xl-pay-interest" />
      {/* <i title="Tất toán" className="fas fa-dollar-sign btn-debt-function" data-toggle="modal" data-target="#modal-xl-final-settlement" /> */}
      <i title="Trả gốc" className="fas fa-university btn-debt-function" data-toggle="modal" data-target="#modal-xl-pay-down-root" />
      <i title="Vay thêm" className="fas fa-hand-holding-medical btn-debt-function" data-toggle="modal" data-target="#modal-xl-more-debt" />
      <i title="Khác" className="fas fa-arrow-circle-down btn-debt-function" data-toggle="dropdown" aria-expanded="false" />
      <Dropdown />
    </div>
  );
};

export default GroupButton;
