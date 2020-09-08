<%--
project name: Desafio Sefaz
Author......: Diogo Azevedo
Version.....: 1.0 | Setembro de 2020
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="CRUD de Usuario Java">
        <meta name="keywords" content="CRUD, usuario">
		<title>Desafio Sefaz - Login</title>
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	</head>
	<body>
		<h4>Acesse o Sistema</h4>
	    <form name="formLogin" id="formLogin" method="post" action="./RedirecionaLogin">
	        <input id="email" name="email" type="text" placeholder=" Email" style="padding-left: 5px;" required >
			<br>
			<br>
			
	        <div>
	            <button id="btn-submit">Entrar</button>
	            &nbsp;&nbsp;
	            <a href="<c:url value="/cadastroUsuario.jsp"/>">Cadastrar usu&aacute;rio</a>
	            <img id="wait" style=" display: none;" src="<c:url value="/images/loading.gif"/>">
	            <br>
	            <span style="color: red;" id="mens"><c:out value="${param.mens}" default=""/></span>
	        </div>
	    </form>
	    
	    <span style="color: green;" id="redirect_txt"><c:out value="${redirect_txt}" default=""/></span>
	    
	    <script src="<c:url value="/js/login.js"/>"></script>
	</body>
</html>