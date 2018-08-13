var ajaxUrl = 'rest/admin/periodical/';
var datatableApi;



$(function () {
    datatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrl,

        sAjaxDataProp: "",
        info: true,
        "search": true,
        "scrollY":        "50vh",
        "scrollCollapse": true,
        "paging":         true,

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