import React from "react";

const InformationBox = () => {
  return (
    <div className="row">
      <div className="col-lg-3 col-6">
        {/* small box */}
        <div className="small-box bg-warning">
          <div className="inner">
            <h3>80.000.000</h3>
            <p>Tổng số tiền vay</p>
          </div>
          <div className="icon">
            <i className="ion ion-bag" />
          </div>
          {/* <a href="#" className="small-box-footer">
            More info <i className="fas fa-arrow-circle-right" />
          </a> */}
        </div>
      </div>
      {/* ./col */}
      <div className="col-lg-3 col-6">
        {/* small box */}
        <div className="small-box bg-info">
          <div className="inner">
            <h3>53.000.000</h3>
            <p>Tổng Lãi</p>
          </div>
          <div className="icon">
            <i className="ion ion-stats-bars" />
          </div>
          {/* <a href="#" className="small-box-footer">
            More info <i className="fas fa-arrow-circle-right" />
          </a> */}
        </div>
      </div>
      {/* <div className="col-lg-3 col-6">
        <div className="small-box bg-warning">
          <div className="inner">
            <h3>44</h3>
            <p>User Registrations</p>
          </div>
          <div className="icon">
            <i className="ion ion-person-add" />
          </div>
          <a href="#" className="small-box-footer">
            More info <i className="fas fa-arrow-circle-right" />
          </a>
        </div>
      </div>
      <div className="col-lg-3 col-6">
        <div className="small-box bg-danger">
          <div className="inner">
            <h3>65</h3>
            <p>Unique Visitors</p>
          </div>
          <div className="icon">
            <i className="ion ion-pie-graph" />
          </div>
          <a href="#" className="small-box-footer">
            More info <i className="fas fa-arrow-circle-right" />
          </a>
        </div>
      </div> */}
    </div>
  );
};

export default InformationBox;
