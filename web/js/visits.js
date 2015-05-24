$('button').click(function () {

    var nickname = $(this).closest('tr').children('td:eq(4)').text();
    var btext = $(this).html();

    var visits_td = $(this).closest('tr').children('td:eq(5)');

    if(visits_td.text() == '0' &&  btext == '-')
        return;

    $.ajax({
        type: "POST",
        url: "/visits",
        data: {nickname: nickname, todo: btext},
        dataType: "json",

        success: function (data, textStatus, jqXHR)
        {
            if (data.success)
            {
                visits_td.html(data.visits);
            }

            else {
                alert(data.error);
            }
        }
    });

});
