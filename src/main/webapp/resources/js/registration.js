function register() {
    $('#register').modal();
}

$(document).ajaxError(function (evant, jqXHR, options, jsExc) {
    fail(evant, jqXHR, options, jsExc)
});

function enter() {
    $('#enter').modal();
    return false;
}

var userAjaxUrl = 'ajax/admin/users/';

function addUser() {
    var form = $('#registration');
    $.ajax({
        type: 'POST',
        url: userAjaxUrl,
        data: form.serialize(),
        success: function (data) {
            $('#register').modal('hide');
            success('add user');
        }
    });
}

function inSystem() {
    var form = $('#enterForm');
    $.ajax({
        type: 'POST',
        url: 'spring_security_check',
        success: function (data) {
            $('#enter').modal('hide');
            window.location.href = 'start';
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

