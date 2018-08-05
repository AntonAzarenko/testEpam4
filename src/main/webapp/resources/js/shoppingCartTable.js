var ajaxUrl = 'user/shoppingCart/';
var datatableApi;


$(function () {
    datatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrl,
        sAjaxDataProp: "",
        info: true,
        "search": true,
        "scrollY": "50vh",
        "scrollCollapse": true,
        "paging": true,

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