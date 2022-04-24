import React from "react";

const FinalSettlement = () => {
  return (
    <div className="modal fade" id="modal-xl-final-settlement">
      <div className="modal-dialog modal-xl">
        <div className="modal-content">
          <div className="modal-header bg-secondary">
            <h4 className="modal-title">Tất toán</h4>
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
            <p>Tất toán</p>
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

export default FinalSettlement;
