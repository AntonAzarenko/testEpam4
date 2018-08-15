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

function add() {
    $('#editRow').modal()

}

var form = $('#detailsForm');

function infoRow(id) {
    $.get(ajaxUrl + 'get/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#info').modal();
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

function addToShop(id) {
    var form = $('#subscriptionForm');
    $.post('user/shoppingCart/periodical/get/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value).attr('checked', value);
            $('#addSub').modal();
        });
    });
}

function checkCart() {
    $.ajax({
        type: 'GET',
        url: 'user/shoppingCart/check/get/',
        success: function (data) {
            window.location.href = 'cartAll';
        },
        error: function (data) {
            $('#error').modal();
        }

    });
}

function showSubscription() {
    $.ajax({
        type: 'GET',
        url: 'subscription/',
        success: function (data) {
            window.location.href = 'subscriptions';
        }
    });
}

function showCart() {

}

function save() {
    var form = $('#subscriptionForm');
    $.ajax({
        type: 'POST',
        url: 'user/shoppingCart/periodical/save/',
        data: form.serialize(),
        success: function (data) {
            $('#addSub').modal('hide');
            success('added')
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
        timeout: 2000
    });
}

function fail(evant, jqXHR, options, jsExc) {
    closeNote();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight',
        timeout: 2000
    });

}

function renderEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn"  onclick="infoRow(' + row.id + ')"><i class="fa fa-eye"></i></a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-light"  onclick="addToShop(' + row.id + ')"><i class="fa fa-shopping-cart"></i></a>';
    }
    return data;
}
