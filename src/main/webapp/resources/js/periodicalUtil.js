function makeEditable() {
    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });
    $('#datailsForm').submit(function () {
        save();
        return false;
    })
}

function add() {
    $('#editRow').modal()

}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + 'archive/' + id,
        type: 'POST',
        success: function () {
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
        datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: 'POST',
        url: ajaxUrl,
        data: form.serialize(),
        success: function (data) {
            $('#editRow').modal('hide');
            updateTable();
        }
    });


}


