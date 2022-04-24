import React, { useEffect } from 'react';
import './index.scss';
import InformationBox from '../InformationBox';
import Helmet from 'react-helmet';
import DebtCreate from '../Popup/DebtCreate';
import FinalSettlement from '../Popup/FinalSettlement';
import MoreDebt from '../Popup/MoreDebt';
import PayDownRoot from '../Popup/PayDownRoot';
import PayInterest from '../Popup/PayInterest';
import ChangeStatusDebt from '../Popup/ChangeStatusDebt';
import RemoveDebt from '../Popup/RemoveDebt';
import DatatableDeptList from '../Datatable/DatatableDebtList';

const Debt = () => {
  // useEffect(() => {
  //   const script = document.createElement("script");
  //   script.src = "js/datatable.js";
  //   script.async = true;

  //   document.body.appendChild(script);
  // }, []);

  return (
    <>
      <div className="content-wrapper">
        {/* Content Header (Page header) */}
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Cầm đồ</h1>
              </div>
              {/* <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item">
                    <a href="#">Home</a>
                  </li>
                  <li className="breadcrumb-item active">DataTables</li>
                </ol>
              </div> */}
            </div>
            <InformationBox />
          </div>
          {/* /.container-fluid */}
        </section>
        {/* Main content */}
        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-12">
                {/* /.card */}
                {/* <div className="card">
                  <div className="card-body">
                    <a
                      class="btn btn-app bg-green btn-create-debt"
                      data-toggle="modal"
                      data-target="#modal-xl-create-or-update-dept"
                    >
                      <i class="fas fa-users"></i> Thêm mới
                    </a>
                    <table
                      id="example1"
                      className="table table-bordered table-striped"
                    >
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>Tên khách hàng</th>
                          <th>Icloud</th>
                          <th>Thế chấp</th>
                          <th>Số tiền vay</th>
                          <th>Số tiền đã trả</th>
                          <th>Lãi đến hôm nay</th>
                          <th>Trạng thái</th>
                          <th className="thead-debt-function">Chức năng</th>
                        </tr>
                      </thead>
                      <tbody className="tbody-debt">
                        <tr className="tr-debt">
                          <td>1</td>
                          <td>Internet Explorer 4.0</td>
                          <td>Win 95+</td>
                          <td> 4</td>
                          <td>X</td>
                          <td>X</td>
                          <td>X</td>
                          <td>X</td>
                          <td className="td-debt-function">
                            <GroupButton />
                          </td>
                        </tr>
                        <tr className="tr-debt">
                          <td>2</td>
                          <td>Internet Explorer 5.0</td>
                          <td>Win 95+</td>
                          <td>5</td>
                          <td>C</td>
                          <td>C</td>
                          <td>C</td>
                          <td>C</td>
                          <td className="td-debt-function">
                            <GroupButton />
                          </td>
                        </tr>
                      </tbody>
                      <tfoot>
                        <tr>
                          <th>ID</th>
                          <th>Tên khách hàng</th>
                          <th>Icloud</th>
                          <th>Thế chấp</th>
                          <th>Số tiền vay</th>
                          <th>Số tiền đã trả</th>
                          <th>Lãi đến hôm nay</th>
                          <th>Trạng thái</th>
                          <th className="tfoot-debt-function">Chức năng</th>
                        </tr>
                      </tfoot>
                    </table>
                  </div>
                </div> */}
                <DatatableDeptList />
              </div>
              {/* /.col */}
            </div>
            {/* /.row */}
          </div>
          {/* /.container-fluid */}
          <DebtCreate />
          <PayInterest />
          <FinalSettlement />
          <PayDownRoot />
          <MoreDebt />
          <ChangeStatusDebt />
          <RemoveDebt />
        </section>
        {/* /.content */}
      </div>

      <Helmet>
        <script defer src="content/js/datatable.js" />
        <script defer src="content/js/reservationDate.js" />
      </Helmet>
    </>
  );
};

export default Debt;
