import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT, APP_LOCAL_DATE_FORMAT_2, APP_WHOLE_NUMBER_FORMAT, status } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities } from '../../../entities/contract/contract.reducer';
import DebtUpdate from '../../components/Popup/DebtUpdate';
import PayInterest from '../../components/Popup/PayInterest';

import GroupButton from '../GroupButton';
import './Datatable.scss';
import FinalSettlement from '../Popup/FinalSettlement';
import PayDownRoot from '../Popup/PayDownRoot';
import MoreDebt from '../Popup/MoreDebt';
import ChangeStatusDebt from '../Popup/ChangeStatusDebt';
import RemoveDebt from '../Popup/RemoveDebt';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

const DatatableDeptList = () => {
  const contractList = useAppSelector(state => state.contract.entities);
  const loading = useAppSelector(state => state.contract.loading);
  const [dataEdit, setDataEdit] = useState({});

  const dispatch = useAppDispatch();

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const handleOnEdit = e => {
    // console.warn(e);
    setDataEdit(e);
  };
  return (
    <>
      <div className="card">
        <div className="card-body">
          <div className="d-flex gap-1">
            <a className="btn btn-app bg-green btn-create-debt" data-toggle="modal" data-target="#modal-xl-create-debt">
              <i className="fas fa-users"></i> Thêm mới
            </a>
            <button
              className="btn btn-app bg-info"
              // data-toggle="modal"
              // data-target="#modal-xl-create-dept"
              onClick={handleSyncList}
              disabled={loading}
              color="info"
            >
              <FontAwesomeIcon icon="sync" spin={loading} /> Làm mới
            </button>
          </div>

          {contractList && contractList.length > 0 ? (
            <table id="data-table-debt-list" className="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>Khách hàng</th>
                  <th>Ngày tạo</th>
                  <th>Tổng tiền vay(VND)</th>
                  <th>Thế chấp</th>
                  <th>Người tạo</th>
                  <th>Ghi chú</th>
                  <th>Tình trạng</th>
                  <th className="thead-debt-function">Chức năng</th>
                </tr>
              </thead>
              <tbody className="tbody-debt">
                {contractList.map(contract => (
                  <tr key={contract.id}>
                    <td>{contract.customerName}</td>
                    {/* <td>{contract.dateStart}</td> */}
                    <td>
                      {contract.dateStart ? <TextFormat type="date" value={contract.dateStart} format={APP_LOCAL_DATE_FORMAT_2} /> : null}
                    </td>
                    {/* <td>{contract.totalLoanAmount}</td> */}
                    <td>
                      {contract.totalLoanAmount ? (
                        <TextFormat type="number" value={contract.totalLoanAmount} format={APP_WHOLE_NUMBER_FORMAT} />
                      ) : null}
                    </td>
                    <td>{contract.productName}</td>
                    <td>{contract.userCreate}</td>
                    <td>{contract.note}</td>
                    <td>{status[contract.status]}</td>
                    <td>
                      <GroupButton onEdit={handleOnEdit} data={contract} />
                    </td>
                  </tr>
                ))}
              </tbody>
              <tfoot>
                <tr>
                  <th>Khách hàng</th>
                  <th>Ngày tạo</th>
                  <th>Tổng tiền vay(VND)</th>
                  <th>Thế chấp</th>
                  <th>Người tạo</th>
                  <th>Ghi chú</th>
                  <th>Tình trạng</th>
                  <th className="tfoot-debt-function">Chức năng</th>
                </tr>
              </tfoot>
            </table>
          ) : (
            !loading && <div className="alert alert-warning">Chưa có hợp đồng nào!</div>
          )}
        </div>
      </div>

      {dataEdit && <DebtUpdate data={dataEdit} />}
      <PayInterest />
      <FinalSettlement />
      <PayDownRoot />
      <MoreDebt />
      <ChangeStatusDebt />
      <RemoveDebt />
    </>
  );
};

export default DatatableDeptList;
