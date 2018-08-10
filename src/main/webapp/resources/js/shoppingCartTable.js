var ajaxUrl = 'user/shoppingCart/';
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
            },
            {
                data: "fullPrice"
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