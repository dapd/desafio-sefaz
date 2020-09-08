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
		<title>Desafio Sefaz - Cadastro</title>
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	</head>
	<body>
    	<h2 class="title container">Preencha os dados de Cadastro</h2>
    	
    	<form id="formUsuario" name="formUsuario" method="post" onsubmit="return campoValidado();" action="<c:url value="/UsuarioController"/>">
    		<p style="color: red">
              	<c:out value="${param.mens}" default=""/>
          	</p>
          	
          	<input type="hidden" name="acao" id="acao" value="cadastrar">
          	
	    	<label for="nomeUsuario">Nome </label>
	    	<input type="text" id="nomeUsuario" value="<c:out value="${txt_nomeUsuario}" default=""/>"
	        	name="nomeUsuario" placeholder="  Nome (*)" maxlength="100" required>
	    	
	    	<br>
	    	<br>
	    	<label for="email">Email </label>
	    	<input type="text" id="email" value="<c:out value="${txt_email}" default=""/>"
	        	name="email" placeholder="  Email (*)" maxlength="50" required>
	    	
	    	<br>
	    	<br>
	    	Telefone celular
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddCelular" id="dddCelular" placeholder="  DDD (*)" maxlength="3">
	                </td>
	                <td>
	                    <input type="text" name="numCelular" id="numCelular" placeholder="  Numero (*)" maxlength="9">
	                </td>
	            </tr>
	        </table>
	        
	    	<br>
	        Telefone fixo
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddFixo" id="dddFixo" placeholder="  DDD (*)" maxlength="3">
	                </td>
	                <td>
	                    <input type="text" name="numFixo" id="numFixo" placeholder="  Numero (*)" maxlength="9">
	                </td>
	            </tr>
	        </table>
	        
	    	<br>
	        Telefone comercial
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddComercial" id="dddComercial" placeholder="  DDD (*)" maxlength="3">
	                </td>
	                <td>
	                    <input type="text" name="numComercial" id="numComercial" placeholder="  Numero (*)" maxlength="9">
	                </td>
	            </tr>
	        </table>
	    	
	    	<br>
	    	Senha
	    	<table>
	            <tr>
	                <td>
	                    <input type="password" name="senha" id="senha" placeholder="  Senha (*)" maxlength="32" required>
	                </td>
	                <td>
	                    <input type="password" name="senhaRepetida" id="senhaRepetida" placeholder="  Confirmar Senha (*)" maxlength="32" required>
	                </td>
	            </tr>
	        </table>

	       	
	       	<input type="submit" id="submit" value="Finalizar Cadastro">
	    </form>
	    
	    <br>
	    <button id="btn-cancelar">Cancelar</button>
    	
    	<script src="<c:url value="/js/cadastro.js"/>" charset="UTF-8"></script>
	</body>
</html>