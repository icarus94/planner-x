$(document).ready(function () {
    console.info("Regular_User Script loaded");

    $(".task-check").on("click", function (event) {
        event.preventDefault();
        let checkboxElement = $(this);
        let id = $(this).data('id');
        let isDone = true;
        if ($(this).prop('checked') === false) {
            isDone = false;
        }

        $.ajax({
            method: "POST",
            url   : "/task/check-task",
            data  : {id: id, done: isDone},
            // dataType   : "json",
            // contentType: " application/json"
        }).done(function (html) {
            if (isDone) {
                $(checkboxElement).prop('checked', true);
            } else {
                $(checkboxElement).prop('checked', false);
            }
        }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });


    $('.task-status-filter').on('click', function () {
        let value = $(this).val();
        let queryParams = new URLSearchParams(location.search);
        queryParams.set('taskStatus', value);
        location.search = queryParams.toString();
    });

    $('.task-order-filter').on('click', function () {
        let value = $(this).val();
        let queryParams = new URLSearchParams(location.search);
        queryParams.set('orderBy', value);
        location.search = queryParams.toString();
    });

    $('#collapse-expand-all-btn').click(function () {
        let expanded = $(this).attr('aria-expanded');
        expanded = (expanded === "true");
        $('.task-table-expand-btn').each(function (index, element) {
            let expandedTable = $(this).attr('aria-expanded');
            expandedTable = (expandedTable === 'true');
            console.info(expandedTable, expanded);
            if (expandedTable === expanded) {
                $('.task-table-expand-btn')[index].click();
            }
        });
        $(this).attr('aria-expanded', !expanded);
    });
});