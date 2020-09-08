package br.diogo.sefaz.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.diogo.sefaz.dao.GenericDAO;
import br.diogo.sefaz.entidades.Usuario;

@WebServlet(name = "LoginUsuarioServlet", urlPatterns = {"/LoginUsuario"})
public class LoginUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);

        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if (email != null && senha != null) {
            	Usuario usuario = (Usuario) new GenericDAO<Usuario>(new Usuario())
        	    		.findByFieldEq("email", email);
            	Boolean loginPermitido = isLoginPermitido(usuario, senha);
            	
            	if (loginPermitido){
            		// configura variaveis de sessão
                    httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGIN", email);
                    httpSession.setAttribute("SESSION_IDENTIFICADOR_NOME", usuario.getNome());
                    // página redirecionada
                    response.sendRedirect("./UsuarioController?acao=");
                    
            	} else {
            		response.sendRedirect("acesso.jsp?mens=" + URLEncoder.encode("Usuário ou senha inválidos.", "UTF-8")
                		+ "&email=" + request.getParameter("email"));
            	}
            } 
            else {
            	response.sendRedirect("acesso.jsp?mens=" + URLEncoder.encode("Campos obrigatórios vazios", "UTF-8"));
            }
        } catch (NullPointerException nullPoint) {
            response.sendRedirect("erro/erro.jsp?err=" + nullPoint.toString());
        }
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        	System.out.println("[ERRO]" + LoginUsuarioServlet.class.getName() + ": " + ex);
        }
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("[ERRO]" + LoginUsuarioServlet.class.getName() + ": " + ex);
        } catch (InvalidKeySpecException ex) {
        	System.out.println("[ERRO]" + LoginUsuarioServlet.class.getName() + ": " + ex);
        }
    }
	
	@Override
    public String getServletInfo() {
        return "Short description";
    }
	
	private Boolean isLoginPermitido(Usuario usuario, String senha) {
		
		if (usuario != null){
			if (usuario.getSenha().equals(senha)){
				return true;
			}
		}
		return false;
	}
}
