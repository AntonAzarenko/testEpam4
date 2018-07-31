var ajaxUrl = 'ajax/periodical/';
var datatableApi;



$(function () {
    datatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrl,
        sAjaxDataProp: "",
        info: false,
        "searching": false,
        "scrollY":        "50vh",
        "scrollCollapse": true,
        "paging":         false,
        columns: [
            {
                data : "index"
            },
            {
                data: "title"
            },
            {
                data: "discription"
            },
            {
                data: "publisher"
            },
            {
                data: "outputFrequency"
            },
            {
                data: "ageLimit"
            },
            {
                data: "price"
            },
            {
                sDefaultContent: "",
                orderable: false,
                'mRender': renderEditBtn
            },
            {
                sDefaultContent: "",
                orderable: false,
                'mRender': renderDeleteBtn
            }
        ],
        aaSorting: [
            [0,
                "asc"
            ]
        ],
        "fnCreatedRow": function (row, data, dataIndex) {
            if(data.archive){
               $(row).css("text-decoration", "line-through");
            }
        },
        "fnInitComplete": makeEditable
    });



});