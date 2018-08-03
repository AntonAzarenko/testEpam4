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

function infoRow(id) {
    $.ajax({
        url: ajaxUrl + 'archive/' + id,
        type: 'POST',
        success: function () {
            updateTable();
            success('archived')
        }
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
    var form = $('#subscriptionForm')
    $.post('user/shoppingCart/periodical/get/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value).attr('checked', value);
            var s = document.forms.subscriptionForm,
                d = s.querySelectorAll('[type="checkbox"]:checked');
            for (var i = 0; i < d.length; i++) // чтобы не было написано NaN, убираем в disabled пункты, где не прописаны значения
                if(d[i] == 'checked')
                d[i].disabled = true;
            });
        $('#addSub').modal();
    });
}

function save() {
    $.ajax({
        type: 'POST',
        url: 'user/shoppingCart/periodical/save/',
        data: form.serialize(),
        success: function (data) {
            $('#editRow').modal('hide');

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
