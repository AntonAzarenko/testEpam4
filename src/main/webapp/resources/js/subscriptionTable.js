var ajaxUrlsub = 'subscription/';
var datatableApi;


$(function () {
    datatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrlsub,
        sAjaxDataProp: "",
        info: false,
        searching: false,
        "scrollY": "50vh",
        "scrollCollapse": true,
        "paging": false,

        columns: [
            {
                data: "index"
            },
            {
                data: "title"
            },
            {
                data: "start"
            },
            {
                data: "end"
            },
            {
                data: "countPer"
            }
        ],
        aaSorting: [
            [0,
                "asc"
            ]
        ],

        "fnInitComplete": makeEditable
    });


});