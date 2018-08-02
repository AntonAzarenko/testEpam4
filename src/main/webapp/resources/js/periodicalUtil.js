function makeEditable() {
    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });
    $('#datailsForm').submit(function () {
        save();
        return false;
    });
    $(document).ajaxError(function (evant, jqXHR, options, jsExc) {
        fail(evant, jqXHR, options, jsExc)
    });
}

var form = $('#detailsForm');

function add() {
    $('#id').val(0);
    $('#editRow').modal();

}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + 'archive/' + id,
        type: 'POST',
        success: function () {
            updateTable();
            success('archived')
        }
    });
}

function edit(id) {
    $.get(ajaxUrl + 'get/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
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

    $.ajax({
        type: 'POST',
        url: ajaxUrl,
        data: form.serialize(),
        success: function (data) {
            $('#editRow').modal('hide');
            updateTable();
            success('saved')
        }
    });
}

var failedNote;

function closeNote() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function success(text) {
    closeNote();
    noty({
        type: 'success',
        text: text,
        layout: 'bottomRight',
        timeout: 1000
    });
}

function fail(evant, jqXHR, options, jsExc) {
    closeNote();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });

}

function renderEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-light"  onclick="edit(' + row.id + ')"><i class="fa fa-edit"></i></a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-light"  onclick="deleteRow(' + row.id + ')"><i class="fa fa-file-archive-o"></i></a>';
    }
    return data;
}


