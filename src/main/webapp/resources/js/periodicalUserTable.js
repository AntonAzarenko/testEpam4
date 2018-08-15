var ajaxUrl = 'user/periodical/';
var userDatatableApi;



$(function () {
    userDatatableApi = $('#datatable').DataTable({
        sAjaxSource: ajaxUrl,
        sAjaxDataProp: "",
        info: false,
        searching: true,
        paging: true,
        columns: [
            {
                data : "index"
            },
            {
                data: "title"
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
                'asc'
            ]
        ],

        "fnCreatedRow": function (row, data, dataIndex) {
          /*  if(data.archive){
                $(row).css("text-decoration", "line-through");
            }*/
        },
        "fnInitComplete": makeEditable
    });



});