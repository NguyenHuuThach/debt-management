$(function () {
  $('#data-table-debt-list')
    .DataTable({
      scroller: true,
      scrollX: true,
      // responsive: true,
      responsive: false,
      lengthChange: false,
      autoWidth: false,
      // autoWidth: true,
      // buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
      buttons: ['excel', 'colvis'],
      paging: true,
      searching: true,
      ordering: true,
      info: true,
    })
    .buttons()
    .container()
    .appendTo('#data-table-debt-list_wrapper .col-md-6:eq(0)');
  $('.buttons-colvis span:first-child').html('Lọc bớt');
  $('.buttons-excel span:first-child').html('Xuất excel');
  // $('#data-table-debt-list_filter label:first-child').contents().first()[0].textContent = 'Tìm kiếm:';
  $('#datatable-pay-interest-list').DataTable({
    paging: true,
    lengthChange: false,
    searching: false,
    ordering: true,
    info: true,
    autoWidth: false,
    responsive: true,
  });
});

// $(function () {
//   $("#datatable-pay-interest-list").DataTable({
//     paging: true,
//     lengthChange: false,
//     searching: false,
//     ordering: true,
//     info: true,
//     autoWidth: false,
//     responsive: true,
//   });
// });
