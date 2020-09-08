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
		<title>Desafio Sefaz - Erro</title>
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	</head>
	<body>
		<div id="textoErro">
	        <h2>Erro!</h2>	
	        <span id="err"></span>
	        <br>
	        <div>
	            <a style="cursor:pointer;" onclick="redirectHome();">Acesse a P&aacute;gina Inicial</a> 
	            <br>
	            <span> Voc&ecirc; ser&aacute; redirecionado para a P&aacute;gina Inicial</span>				
	        </div>
	    </div>
	    <script>
	        function redirectHome() {
	            window.location = "../index.jsp";
	        }
       	</script>
	</body>
</html>