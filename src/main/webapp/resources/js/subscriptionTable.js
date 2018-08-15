var ajaxUrl = 'subscription/';
var datatableApi;


$(function () {
    datatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrl,
        sAjaxDataProp: "",
        info: false,
        searching: false,
        "scrollY": "50vh",
        "scrollCollapse": true,
        "paging": false,

        columns: [
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
            },
            {
                data: "countInterToEnd"
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