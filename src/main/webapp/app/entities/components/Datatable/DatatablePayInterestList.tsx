import React from 'react';

const DatatablePayInterestList = () => {
  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">Chi tiết đóng lãi</h3>
      </div>
      <div className="card-body">
        <div id="datatable-pay-interest-list_wrapper" className="dataTables_wrapper dt-bootstrap4">
          <div className="row">
            <div className="col-sm-12 col-md-6" />
            <div className="col-sm-12 col-md-6" />
          </div>
          <div className="row">
            <div className="col-sm-12">
              <table
                id="datatable-pay-interest-list"
                className="table table-bordered table-hover dataTable dtr-inline"
                aria-describedby="datatable-pay-interest-list_info"
              >
                <thead>
                  <tr>
                    <th
                      className="sorting sorting_asc"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-sort="ascending"
                      aria-label="Rendering engine: activate to sort column descending"
                    >
                      ID
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="Browser: activate to sort column ascending"
                    >
                      Ngày trả lãi
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="Platform(s): activate to sort column ascending"
                    >
                      Tiền lãi
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="Engine version: activate to sort column ascending"
                    >
                      Tiền gốc
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="CSS grade: activate to sort column ascending"
                    >
                      Phí khác
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="CSS grade: activate to sort column ascending"
                    >
                      Cần thanh toán
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="CSS grade: activate to sort column ascending"
                    >
                      Đã thanh toán
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="CSS grade: activate to sort column ascending"
                    >
                      Các lần đóng
                    </th>
                    <th
                      className="sorting"
                      tabIndex={0}
                      aria-controls="datatable-pay-interest-list"
                      rowSpan={1}
                      colSpan={1}
                      aria-label="CSS grade: activate to sort column ascending"
                    >
                      Thao tác
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr className="odd">
                    <td className="dtr-control sorting_1" tabIndex={0}>
                      Gecko
                    </td>
                    <td>Firefox 1.0</td>
                    <td>Win 98+ / OSX.2+</td>
                    <td>1.7</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="even">
                    <td className="dtr-control sorting_1" tabIndex={0}>
                      Gecko
                    </td>
                    <td>Firefox 1.5</td>
                    <td>Win 98+ / OSX.2+</td>
                    <td>1.8</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="odd">
                    <td className="dtr-control sorting_1" tabIndex={0}>
                      Gecko
                    </td>
                    <td>Firefox 2.0</td>
                    <td>Win 98+ / OSX.2+</td>
                    <td>1.8</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="even">
                    <td className="dtr-control sorting_1" tabIndex={0}>
                      Gecko
                    </td>
                    <td>Firefox 3.0</td>
                    <td>Win 2k+ / OSX.3+</td>
                    <td>1.9</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="odd">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Camino 1.0</td>
                    <td>OSX.2+</td>
                    <td>1.8</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="even">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Camino 1.5</td>
                    <td>OSX.3+</td>
                    <td>1.8</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="odd">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Netscape 7.2</td>
                    <td>Win 95+ / Mac OS 8.6-9.2</td>
                    <td>1.7</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="even">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Netscape Browser 8</td>
                    <td>Win 98SE+</td>
                    <td>1.7</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="odd">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Netscape Navigator 9</td>
                    <td>Win 98+ / OSX.2+</td>
                    <td>1.8</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="even">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Mozilla 1.0</td>
                    <td>Win 95+ / OSX.1+</td>
                    <td>1</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                  <tr className="odd">
                    <td className="sorting_1 dtr-control">Gecko</td>
                    <td>Mozilla 1.0</td>
                    <td>Win 95+ / OSX.1+</td>
                    <td>1</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                    <td>A</td>
                  </tr>
                </tbody>
                <tfoot>
                  <tr>
                    <th rowSpan={1} colSpan={1}>
                      ID
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Ngày trả lãi
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Tiền lãi
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Tiền gốc
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Phí khác
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Cần thanh toán
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Đã thanh toán
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Các lần đóng
                    </th>
                    <th rowSpan={1} colSpan={1}>
                      Thao tác
                    </th>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DatatablePayInterestList;
