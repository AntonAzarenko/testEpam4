function register() {
    $('#register').modal();
}

function enter() {
    $('#enter').modal();
}


function addUser() {
    var userAjaxUrl = 'ajax/admin/users/';
    var form =$('#registration');
    $.ajax({
        type: 'POST',
        url: userAjaxUrl,
        data: form.serialize(),
        success: function(data){
            $('#register').modal('hide')

        }
    })
}