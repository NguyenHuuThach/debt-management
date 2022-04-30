import { APP_WHOLE_NUMBER_FORMAT } from 'app/config/constants';
import React from 'react';
import { TextFormat } from 'react-jhipster';

const InformationBox = (props: any) => {
  return (
    <div className="row">
      <div className="col-lg-3 col-6">
        <div className="small-box bg-warning">
          <div className="inner">
            <h3>
              <TextFormat type="number" value={props.data.totalAmountLent} format={APP_WHOLE_NUMBER_FORMAT} />
            </h3>
            <p>Tổng số tiền vay</p>
          </div>
          <div className="icon">
            <i className="ion ion-bag" />
          </div>
        </div>
      </div>
      <div className="col-lg-3 col-6">
        <div className="small-box bg-info">
          <div className="inner">
            <h3>
              <TextFormat type="number" value={props.data.totalProfitReceived} format={APP_WHOLE_NUMBER_FORMAT} />
            </h3>
            <p>Tổng Lãi</p>
          </div>
          <div className="icon">
            <i className="ion ion-stats-bars" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default InformationBox;
