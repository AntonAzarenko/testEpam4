var ajaxUrl = 'ajax/periodical/';
var datatableApi;



$(function () {
    datatableApi = $('#datatable').DataTable({
        paging: true,
        info: false,
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
                sDefaultContent: "edit",
                orderable: false
            },
            {
                sDefaultContent: "archive",
                orderable: false
            }
        ],
        aaSorting: [
            [0,
                "asc"
            ]
        ]
    });

    makeEditable();

});