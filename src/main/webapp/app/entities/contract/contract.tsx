// import React, { useState, useEffect } from 'react';
// import { Link, RouteComponentProps } from 'react-router-dom';
// import { Button, Table } from 'reactstrap';
// import { Translate, TextFormat } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
// import { useAppDispatch, useAppSelector } from 'app/config/store';

// import { IContract } from 'app/shared/model/contract.model';
// import { getEntities } from './contract.reducer';

// export const Contract = (props: RouteComponentProps<{ url: string }>) => {
//   const dispatch = useAppDispatch();

//   const contractList = useAppSelector(state => state.contract.entities);
//   const loading = useAppSelector(state => state.contract.loading);

//   useEffect(() => {
//     dispatch(getEntities({}));
//   }, []);

//   const handleSyncList = () => {
//     dispatch(getEntities({}));
//   };

//   const { match } = props;

//   return (
//     <div>
//       <h2 id="contract-heading" data-cy="ContractHeading">
//         Contracts
//         <div className="d-flex justify-content-end">
//           <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
//             <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
//           </Button>
//           <Link to="/contract/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
//             <FontAwesomeIcon icon="plus" />
//             &nbsp; Create new Contract
//           </Link>
//         </div>
//       </h2>
//       <div className="table-responsive">
//         {contractList && contractList.length > 0 ? (
//           <Table responsive>
//             <thead>
//               <tr>
//                 <th>ID</th>
//                 <th>Date Start</th>
//                 <th>Number Interest Payments</th>
//                 <th>Total Loan Amount</th>
//                 <th>Interest Payment Period</th>
//                 <th>Interest Rate</th>
//                 <th>Customer Name</th>
//                 <th>Customer Address</th>
//                 <th>Customer Phone Number</th>
//                 <th>Customer Identity Card</th>
//                 <th>Product Name</th>
//                 <th>Imei</th>
//                 <th>Icloud</th>
//                 <th>User Create</th>
//                 <th>Note</th>
//                 <th>Status</th>
//                 <th>Is Deleted</th>
//                 <th />
//               </tr>
//             </thead>
//             <tbody>
//               {contractList.map((contract, i) => (
//                 <tr key={`entity-${i}`} data-cy="entityTable">
//                   <td>
//                     <Button tag={Link} to={`/contract/${contract.id}`} color="link" size="sm">
//                       {contract.id}
//                     </Button>
//                   </td>
//                   <td>{contract.dateStart ? <TextFormat type="date" value={contract.dateStart} format={APP_DATE_FORMAT} /> : null}</td>
//                   <td>{contract.numberInterestPayments}</td>
//                   <td>{contract.totalLoanAmount}</td>
//                   <td>{contract.interestPaymentPeriod}</td>
//                   <td>{contract.interestRate}</td>
//                   <td>{contract.customerName}</td>
//                   <td>{contract.customerAddress}</td>
//                   <td>{contract.customerPhoneNumber}</td>
//                   <td>{contract.customerIdentityCard}</td>
//                   <td>{contract.productName}</td>
//                   <td>{contract.imei}</td>
//                   <td>{contract.icloud}</td>
//                   <td>{contract.userCreate}</td>
//                   <td>{contract.note}</td>
//                   <td>{contract.status}</td>
//                   <td>{contract.isDeleted ? 'true' : 'false'}</td>
//                   <td className="text-end">
//                     <div className="btn-group flex-btn-group-container">
//                       <Button tag={Link} to={`/contract/${contract.id}`} color="info" size="sm" data-cy="entityDetailsButton">
//                         <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
//                       </Button>
//                       <Button tag={Link} to={`/contract/${contract.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
//                         <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
//                       </Button>
//                       <Button tag={Link} to={`/contract/${contract.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
//                         <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
//                       </Button>
//                     </div>
//                   </td>
//                 </tr>
//               ))}
//             </tbody>
//           </Table>
//         ) : (
//           !loading && <div className="alert alert-warning">No Contracts found</div>
//         )}
//       </div>
//     </div>
//   );
// };

// export default Contract;

import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities } from './contract.reducer';

import '../components/Debt/index.scss';
import InformationBox from '../components/InformationBox';
import Helmet from 'react-helmet';
import DebtCreate from '../components/Popup/DebtCreate';
import FinalSettlement from '../components/Popup/FinalSettlement';
import MoreDebt from '../components/Popup/MoreDebt';
import PayDownRoot from '../components/Popup/PayDownRoot';
import PayInterest from '../components/Popup/PayInterest';
import ChangeStatusDebt from '../components/Popup/ChangeStatusDebt';
import RemoveDebt from '../components/Popup/RemoveDebt';
import DatatableDeptList from '../components/Datatable/DatatableDebtList';
import DebtUpdate from '../components/Popup/DebtUpdate';

export const Contract = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  // const contractList = useAppSelector(state => state.contract.entities);
  // const loading = useAppSelector(state => state.contract.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  // const handleSyncList = () => {
  //   dispatch(getEntities({}));
  // };

  // const { match } = props;

  return (
    <>
      {/* Content Header (Page header) */}
      <section className="content-header">
        <div className="container-fluid">
          <div className="row mb-2">
            <div className="col-sm-6">
              <h1>Hợp đồng</h1>
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
      </section>
      <section className="content">
        <div className="container-fluid">
          <div className="row">
            <div className="col-12">
              <DatatableDeptList />
            </div>
          </div>
        </div>
        <DebtCreate />
        <DebtUpdate />
        <PayInterest />
        <FinalSettlement />
        <PayDownRoot />
        <MoreDebt />
        <ChangeStatusDebt />
        <RemoveDebt />
      </section>
      {/* /.content */}

      <Helmet>
        <script defer src="content/js/datatable.js" />
        <script defer src="content/js/reservationDate.js" />
        <script defer src="content/js/formatInputCurrency.js" />
      </Helmet>
    </>
  );
};

export default Contract;
