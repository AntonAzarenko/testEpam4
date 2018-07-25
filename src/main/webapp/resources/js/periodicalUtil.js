function makeEditable(){
    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });
}
function add() {
    $('#editRow').modal()

}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        sucсess: function () {
            updateTable();
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
        });
        datatableApi.draw()
    });
}

function save() {
    var from = $('#detailsForm')
        $.ajax({
            type: 'POST',
            url: ajaxUrl,
            data: from.serialize(),
            success:function (data) {
                $('#editRow').modal('hide');
                updateTable();
                
            }
        });

    
}


