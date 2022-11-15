import 'datatables.net-dt/css/jquery.datatables.css';
import 'datatables.net';
import 'datatables.net-dt';

$(document).ready(function () {
    var order = {
        sortBy       : 'name',
        sortDirection: 'asc'
    };
    var table = $('#users-table').DataTable({
        aLengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
        searching  : false,
        serverSide : true,
        order      : [[1, "asc"]],
        ajax       : {
            url : '/admin/users/datatables',
            data: function (d, settings) {
                setOrderParams();
                d.sortBy = order.sortBy;
                d.sortDirection = order.sortDirection;
            }
        },
        columns    : [
            {
                data     : "id",
                orderable: false,
                render   : function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                data: "name", className: 'text-align-center'
            },
            {
                data: "surname", className: 'text-align-center'
            },
            {
                data: "email", className: 'text-align-center'
            },
            {
                data: "role", className: 'text-align-center'
            },
            {
                data  : "active", className: 'text-align-center',
                render: function (data, type, row, meta) {
                    let id = row.id;
                    let checked = 'checked';
                    let label = 'Active';
                    if (data === false) {
                        checked = '';
                        label = 'Disabled';
                    }
                    return `
                        <div class="form-check form-switch">
                          <input data-active-id='${id}' class="form-check-input" value="${id}" type="checkbox" id="active-toggle-${id}" ${checked}>
                          <label data-active-id='${id}' class="form-check-label" for="active-toggle-${id}">${label}</label>
                        </div>
                    `;
                }
            },
            {
                data     : "id",
                orderable: false,
                render   : function (data, type, row, meta) {
                    return `<a href="/admin/user-info?id=${row.id}" target="_blank"><i class="fas fa-external-link-alt"></i></a>`;
                }
            }
        ],
    });

    function setOrderParams() {
        if (!table) {
            return
        }
        let orderTable = table.order();
        order.sortBy = table.column(orderTable[0][0]).dataSrc();
        order.sortDirection = orderTable[0][1];
    }

    $('#users-table tbody').on('click', '[id^=active-toggle-]', function (event) {
        event.preventDefault();
        let input = $(this);
        let inputInitialStateChecked = !input.is(":checked");
        let inputTargetStateChecked = input.is(":checked");
        let postData = {
            id    : input.val(),
            status: input.is(":checked")
        };
        console.info('[ACTION] user active status change: ' + JSON.stringify(postData));
        $.post('/admin/users/change-active-status', postData).done(function (data) {
            changeUserActiveStatusComponent(input, inputTargetStateChecked);
            console.info('User active status changed', data);
        }).fail(function (xhr, status, error) {
            changeUserActiveStatusComponent(input, inputInitialStateChecked);
            console.error('ErrorReason:', xhr.responseText);
            alert('Something went wrong please contact system admin.');
        });
    });

    function changeUserActiveStatusComponent(input, state) {
        input.prop('checked', state);
        let label = $('label[for="' + input.attr('id') + '"]');
        if (state === false) {
            label.text('Disabled');
        } else {
            label.text('Active');
        }
    }
});