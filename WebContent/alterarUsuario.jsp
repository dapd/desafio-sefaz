<%--
project name: Desafio Sefaz
Author......: Diogo Azevedo
Version.....: 1.0 | Setembro de 2020
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.SESSION_IDENTIFICADOR_NOME == null || sessionScope.SESSION_IDENTIFICADOR_LOGIN == null}">
    <jsp:forward page="./index.jsp"/>
</c:if>

<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="CRUD de Usuario Java">
        <meta name="keywords" content="CRUD, usuario">
		<title>Desafio Sefaz - Alterar</title>
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	</head>
	<body>
		
    	<h2>Altera&ccedil;&atilde;o de Cadastro</h2>
    	
    	<form id="formUsuario" name="formUsuario" method="post" onsubmit="return campoValidado();" action="<c:url value="./UsuarioController"/>">
    		<p style="color: red">
              	<c:out value="${param.mens}" default=""/>
          	</p>
          	
          	<input type="hidden" name="acao" id="acao" value="alterar">
          	
          	<c:if test="${usuario != null}">
                <input type="hidden" name="codUsuario" id="codUsuario" value="<c:out value='${usuario.codUsuario}' />" />
            </c:if>
          	
	    	<label for="nomeUsuario">Nome </label>
	    	<input type="text" id="nomeUsuario" value="<c:out value="${usuario.nome}" default=""/>"
	        	name="nomeUsuario" placeholder="  Nome (*)" maxlength="100" required>
	    	
	    	<br>
	    	<br>
	    	<label for="email">Email </label>
	    	<input type="text" id="email" value="<c:out value="${usuario.email}" default=""/>"
	        	name="email" placeholder="  Email (*)" maxlength="50" required>
	    	
	    	<br>
	    	<br>
	    	<c:if test="${telCelular != null}">
                <input type="hidden" name="codTelCelular" id="codTelCelular" value="<c:out value='${telCelular.codTelefone}' />" />
            </c:if>
	    	Telefone celular
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddCelular" id="dddCelular" placeholder="  DDD (*)" maxlength="3"
	                    	value="<c:out value="${telCelular.ddd}" default=""/>">
	                </td>
	                <td>
	                    <input type="text" name="numCelular" id="numCelular" placeholder="  Numero (*)" maxlength="9"
	                    	value="<c:out value="${telCelular.numero}" default=""/>">
	                </td>
	            </tr>
	        </table>
	        
	    	<br>
	    	<c:if test="${telFixo != null}">
                <input type="hidden" name="codTelFixo" id="codTelFixo" value="<c:out value='${telFixo.codTelefone}' />" />
            </c:if>
	        Telefone fixo
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddFixo" id="dddFixo" placeholder="  DDD (*)" maxlength="3"
	                    	value="<c:out value="${telFixo.ddd}" default=""/>">
	                </td>
	                <td>
	                    <input type="text" name="numFixo" id="numFixo" placeholder="  Numero (*)" maxlength="9"
	                    	value="<c:out value="${telFixo.numero}" default=""/>">
	                </td>
	            </tr>
	        </table>
	        
	    	<br>
	    	<c:if test="${telComercial != null}">
                <input type="hidden" name="codTelComercial" id="codTelComercial" value="<c:out value='${telComercial.codTelefone}' />" />
            </c:if>
	        Telefone comercial
	    	<table>
	            <tr>
	                <td>
	                    <input type="text" name="dddComercial" id="dddComercial" placeholder="  DDD (*)" maxlength="3"
	                    	value="<c:out value="${telComercial.ddd}" default=""/>">
	                </td>
	                <td>
	                    <input type="text" name="numComercial" id="numComercial" placeholder="  Numero (*)" maxlength="9"
	                    	value="<c:out value="${telComercial.numero}" default=""/>">
	                </td>
	            </tr>
	        </table>
	    	
	    	<br>
	    	Senha
	    	<table>
	            <tr>
	                <td>
	                    <input type="password" name="senha" id="senha" value="<c:out value="${usuario.senha}" default=""/>"
	                    	placeholder="  Senha (*)" maxlength="32" required>
	                </td>
	                <td>
	                    <input type="password" name="senhaRepetida" id="senhaRepetida" value="<c:out value="${usuario.senha}" default=""/>"
	                    	placeholder="  Confirmar Senha (*)" maxlength="32" required>
	                </td>
	            </tr>
	        </table>
			
			<br>
	       	<input type="submit" id="submit" value="Finalizar Alteração">
	    </form>
	    
	    <br>
	    <button id="btn-cancelar-edit">Cancelar</button>
    	
    	<script src="<c:url value="/js/cadastro.js"/>" charset="UTF-8"></script>
	</body>
</html>