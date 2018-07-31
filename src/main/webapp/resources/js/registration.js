function register() {
    $('#register').modal();
}

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
            $('#register').modal('hide')


        }
    })
}

function inSystem() {
    var form = $('#enterForm');
    $.ajax({
        type: 'POST',
        url: userAjaxUrl + 'login/',
        data: form.serialize(),
        success: function (data) {
            $('#enter').modal('hide');
            window.location.href='start'
        }
    });
}

