function makeEditable() {
    $('.info').click(function () {
        infoRow($(this).attr("id"));
    });
    $('#datailsForm').submit(function () {
        save();
        return false;
    });
    $(document).ajaxError(function (evant, jqXHR, options, jsExc) {
        fail(evant, jqXHR, options, jsExc)
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        userDatatableApi.clear();
        $.each(data, function (key, item) {
            userDatatableApi.row.add(item);
        });
        userDatatableApi.draw();
    });
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