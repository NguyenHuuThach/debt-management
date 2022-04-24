import React from "react";

const RemoveDebt = () => {
  return (
    <div className="modal fade" id="modal-sm-remove-debt">
      <div className="modal-dialog modal-sm">
        <div className="modal-content">
          <div className="modal-header">
            <h4 className="modal-title">Small Modal</h4>
            <button
              type="button"
              className="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div className="modal-body">
            <p>RemoveDebt</p>
          </div>
          <div className="modal-footer justify-content-between">
            <button
              type="button"
              className="btn btn-default"
              data-dismiss="modal"
            >
              Close
            </button>
            <button type="button" className="btn btn-primary">
              Save changes
            </button>
          </div>
        </div>
        {/* /.modal-content */}
      </div>
      {/* /.modal-dialog */}
    </div>
  );
};

export default RemoveDebt;
