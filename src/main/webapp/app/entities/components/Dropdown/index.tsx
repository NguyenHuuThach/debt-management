import React from "react";

const Dropdown = () => {
  return (
    <div className="dropdown-menu">
      <a
        className="dropdown-item"
        href="#"
        data-toggle="modal"
        data-target="#modal-sm-change-status-debt"
      >
        <i className="fas fa-exchange-alt"></i>&nbsp; Đổi tình trạng nợ
      </a>
      <a
        className="dropdown-item"
        href="#"
        data-toggle="modal"
        data-target="#modal-sm-remove-debt"
      >
        <i className="fas fa-trash-alt"></i>&nbsp; Xóa
      </a>
    </div>
  );
};

export default Dropdown;
