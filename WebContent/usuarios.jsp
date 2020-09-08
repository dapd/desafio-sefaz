<%--
project name: Desafio Sefaz
Author......: Diogo Azevedo
Version.....: 1.0 | Setembro de 2020
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.SESSION_IDENTIFICADOR_NOME == null || sessionScope.SESSION_IDENTIFICADOR_LOGIN == null}">
    <jsp:forward page="./index.jsp?mens=Sessao expirada. Realize o login."/>
</c:if>

<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="CRUD de Usuario Java">
        <meta name="keywords" content="CRUD, usuario">
		<title>Desafio Sefaz - Usu&aacute;rios</title>
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	</head>
	<body>
		<h2>Olá <c:out value="${sessionScope.SESSION_IDENTIFICADOR_NOME}" default="Usuário"/></h2>
		<a style="display:inline-block; float:right;" href="<c:url value="/logout.jsp"/>">Sair</a>
	    <span style="color: green;" id="redirect_txt"><c:out value="${redirect_txt}" default=""/></span>
	    
	    <div align="center">
	        <table border="1">
	            <caption>Lista de Usu&aacute;rios</caption>
	            <tr>
	                <th>ID</th>
	                <th>Nome</th>
	                <th>Email</th>
	                <th>A&ccedil;&otilde;es</th>
	            </tr>
	            <c:forEach var="usuario" items="${listaUsuarios}">
	                <tr>
	                    <td><c:out value="${usuario.codUsuario}" /></td>
	                    <td><c:out value="${usuario.nome}" /></td>
	                    <td><c:out value="${usuario.email}" /></td>
	                    <td>
	                        <a href="./UsuarioController?acao=alterar&codUsuario=<c:out value='${usuario.codUsuario}' />">Alterar</a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;
	                        <a href="./UsuarioController?acao=remover&codUsuario=<c:out value='${usuario.codUsuario}' />">Remover</a>                     
	                    </td>
	                </tr>
	            </c:forEach>
	        </table>
	    </div>
	</body>
</html>