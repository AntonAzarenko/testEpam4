var ajaxUrl = 'user/payments/';
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
                data: "date",
               /* mRender: function (date, type, row) {
                    if(type == 'display'){
                        var dateObject = new Data(date);
                        return '<span>' + dateObject.toISOString().substring(0,10) + '<span>'
                    }
                    return date;
                }*/
            },
            {
                data: "count"
            },
            {
                data: "cost"
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