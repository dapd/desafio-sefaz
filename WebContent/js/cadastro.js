function validarSenha() {

    senha = document.formUsuario.senha.value;
    senhaRepetida = document.formUsuario.senhaRepetida.value;
    if (senha != senhaRepetida) {
        $("#senhaRepetida").css("background-color", "#EEB4B4");
        $("#senhaRepetida").css("opacity", "0.8");

        $("#senha").css("background-color", "#EEB4B4");
        $("#senha").css("opacity", "0.8");
        //alert("Senhas diferentes");
        validator = false;
    } else {
        $("#senhaRepetida").css("background-color", "white");
        $("#senhaRepetida").css("opacity", "0.8");
        $("#senha").css("background-color", "white");
        $("#senha").css("opacity", "0.8");
        validator = true;
    }

}

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

$("#senhaRepetida").change(function () {
    validarSenha();
});

$('#senha').bind('copy', function (e) {
    e.preventDefault();
    //alert('Não é permitido copiar o campo senha.');
});
$('#senha').bind('paste', function (e) {
    e.preventDefault();
    //alert('Não é permitido colar no campo de senha.');
});
$('#senha').bind('cut', function (e) {
    e.preventDefault();
    //alert('Não é permitido recortar no campo senha.');
});

$('#senhaRepetida').bind('copy', function (e) {
    e.preventDefault();
    //alert('Não é permitido copiar o campo confirmação de senha.');
});
$('#senhaRepetida').bind('paste', function (e) {
    e.preventDefault();
    //alert('Não é permitido colar no campo de confirmação de senha.');
});
$('#senhaRepetida').bind('cut', function (e) {
    e.preventDefault();
    //alert('Não é permitido recortar no campo de confirmação de senha.');
});

$("#senha").change(function () {
    validarSenha();
});

$("#email").focusout(function () {
    var ehemail = isEmail($(this).val().toString());
    if (ehemail) {
        $(this).css("background-color", "white");
        $(this).css("opacity", "0.8");
    } else {
        $(this).css("background-color", "#EEB4B4");
        $(this).css("opacity", "0.8");
        alert("E-mail inválido");
    }
});

function campoValidado() {
    
	var validado = isEmail($("#email").val());
    
	if (validado == true) {
        validarSenha();
        return validator;
    }
    return false;
}

$("#btn-cancelar").click(function () {
	window.location.href = "./index.jsp";
});

$("#btn-cancelar-edit").click(function () {
	window.location.href = "./UsuarioController?acao=";
});