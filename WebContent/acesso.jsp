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

        <h1>Login</h1>

        <div>
            <c:if test="${param.mens != null}">
                <p style="color:red;"><c:out value="${param.mens}" default=""/></p>
            </c:if>
            <div>
                <form id="formAcesso" name="formAcesso" method="post" action="LoginUsuario">
                    <input type="text" name="email" id="email" placeholder="  Inserir Email" required 
                           value="<c:out value="${param.email}" default=""/>">
                    <input type="password" name="senha" placeholder="  Senha do usuário" required> 
					<br>
					<br>
                    <input type="submit" value="Entrar" id="bbton">
                 </form>
             </div>
         </div>
	</body>
</html>