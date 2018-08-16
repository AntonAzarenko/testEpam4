function makeEditable() {

}

var failedNote;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        userDatatableApi.clear();
        $.each(data, function (key, item) {
            userDatatableApi.row.add(item);
        });
        userDatatableApi.draw();
    });
}

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
        layout: 'bottomRight',
        timeout: 1000
    });

}