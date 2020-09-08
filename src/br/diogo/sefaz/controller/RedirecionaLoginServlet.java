package br.diogo.sefaz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.diogo.sefaz.dao.GenericDAO;
import br.diogo.sefaz.entidades.Usuario;

@WebServlet(name = "RedirecionaLoginServlet", urlPatterns = {"/RedirecionaLogin"})
public class RedirecionaLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);
        
        String email = request.getParameter("email");
        
		if (email != null) {
		    Usuario usuario = (Usuario) new GenericDAO<Usuario>(new Usuario())
		    		.findByFieldEq("email", email);
		    
		    if (usuario != null) {
		        out.write("Usuario");
		    } else {
		        out.write("n");
		    }
		    out.close();
		} else {
		    out.write("Favor preencher o Email");
		    out.close();
		}
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            System.out.println("[ERRO]" + RedirecionaLoginServlet.class.getName() + ": " + ex);
        }
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
        	System.out.println("[ERRO]" + RedirecionaLoginServlet.class.getName() + ": " + ex);
        }
    }
	
	@Override
    public String getServletInfo() {
        return "Short description";
    }
}
