function keys(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla === 13)
        return false;
    else
        return true;
}

$('email').bind('keypress', function () {
    if (window.event.keyCode == 13){
        return false;
    }
});

$('#btn-submit').click(function () {
	if ($('input[name=email]').val()){
	    $.ajax({
	        type: "POST",
	        url: "./RedirecionaLogin",
	        cache: false,
	        data: {'email': $('input[name=email]').val()},
	        timeout: 10000,
	        success: function (data) {
	            if (data === 'Usuario') {
	            	$('#formLogin').attr("action", "./acesso.jsp");
	                $('#formLogin').submit();
	            } else {
	            	$('#mens').css({"visibility": "visible"});
	            	$('#mens').text("Usuário não cadastrado!");
	                $('#wait').hide();
	                $('#btn-submit').show();
	            }
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            $('#mens').text(textStatus + ' - ' + errorThrown);
	            $('#mens').css({"visibility": "visible"});
	            $('#btn-submit').show();
	        },
	        beforeSend: function (jqXHR, settings) {
	            $('#wait').show();
	            $('#btn-submit').hide();
	        },
	        complete: function (jqXHR, textStatus) {
	            $('#wait').hide();
	        }
	    });
	}
    return false;
});