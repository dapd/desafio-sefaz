package br.diogo.sefaz.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.diogo.sefaz.dao.GenericDAO;
import br.diogo.sefaz.entidades.Telefone;
import br.diogo.sefaz.entidades.Usuario;

@WebServlet(name = "UsuarioControllerServlet", urlPatterns = {"/UsuarioController"})
public class UsuarioControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);
        System.out.println("carregando cadastro");
        try {
            
            String nome = request.getParameter("nomeUsuario");
            request.setAttribute("txt_nomeUsuario", nome);

            String senha = request.getParameter("senha");
            String senhaRepetida = request.getParameter("senhaRepetida");

            String email = request.getParameter("email");
            request.setAttribute("txt_email", email);
            
            String dddCelular = request.getParameter("dddCelular");
            String numCelular = request.getParameter("numCelular");
            request.setAttribute("txt_dddCelular", dddCelular);
            request.setAttribute("txt_numCelular", numCelular);
            
            String dddFixo = request.getParameter("dddFixo");
            String numFixo = request.getParameter("numFixo");
            request.setAttribute("txt_dddFixo", dddFixo);
            request.setAttribute("txt_numFixo", numFixo);
            
            String dddComercial = request.getParameter("dddComercial");
            String numComercial = request.getParameter("numComercial");
            request.setAttribute("txt_dddComercial", dddComercial);
            request.setAttribute("txt_numComercial", numComercial);
            
            System.out.println("validando dados");

            RequestDispatcher rd = null;
            
            if (!senha.equals(senhaRepetida)) {
                rd = request.getRequestDispatcher("cadastroUsuario.jsp?mens=" + URLEncoder.encode("Senhas nao conferem","UTF-8"));
                rd.forward(request, response);
                return;
            }
            
            Usuario usuario = (Usuario) new GenericDAO<Usuario>(new Usuario())
    	    		.findByFieldEq("email", email);

            if (usuario != null) {
                System.out.println("USUARIO JA EXISTE NA BASE");
                response.sendRedirect("cadastroUsuario.jsp?mens=" + URLEncoder.encode("Usuario ja esta cadastrado","UTF-8"));
                return;
            } 
            else {
                // Cadastrar Usuario
                usuario = new Usuario(nome, email, senha);
                Integer codUsuario = (Integer) new GenericDAO<Usuario>(usuario).create();
                usuario.setCodUsuario(codUsuario);

                // Cadastrar Telefone
                Telefone telefone;
                if(dddCelular != null && numCelular != null && !dddCelular.equals("") && !numCelular.equals("")){
                	telefone = new Telefone(dddCelular, numCelular, "M", usuario);
                    new GenericDAO<Telefone>(telefone).create();
                }
                
                if(dddFixo != null && numFixo != null && !dddFixo.equals("") && !numFixo.equals("")){
                	telefone = new Telefone(dddFixo, numFixo, "F", usuario);
                    new GenericDAO<Telefone>(telefone).create();
                }
                
                if(dddComercial != null && numComercial != null && !dddComercial.equals("") && !numComercial.equals("")){
                	telefone = new Telefone(dddComercial, numComercial, "C", usuario);
                    new GenericDAO<Telefone>(telefone).create();
                }
                
                request.setAttribute("redirect_txt", "Usuario cadastrado");
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } catch (IllegalStateException ex1) {
            System.out.println("Exception UsuarioControllerServlet ---> " + URLEncoder.encode(ex1.toString(),"UTF-8"));
            response.sendRedirect("erro/erro.jsp?err=" + URLEncoder.encode(ex1.toString(),"UTF-8"));
        } catch(Exception ex){
            System.out.println("Exception UsuarioControllerServlet ---> " + URLEncoder.encode(ex.toString(),"UTF-8"));
            response.sendRedirect("erro/erro.jsp?err=" + URLEncoder.encode(ex.toString(),"UTF-8"));
        }
    }
	
	/*
	 * 
	 */
	private void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");

        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);
        
	    String codUsuario = request.getParameter("codUsuario");
	    String nome = request.getParameter("nomeUsuario");

        String senha = request.getParameter("senha");
        String senhaRepetida = request.getParameter("senhaRepetida");

        String email = request.getParameter("email");
        request.setAttribute("txt_email", email);
        
        String dddCelular = request.getParameter("dddCelular");
        String numCelular = request.getParameter("numCelular");
        
        String dddFixo = request.getParameter("dddFixo");
        String numFixo = request.getParameter("numFixo");
        
        String dddComercial = request.getParameter("dddComercial");
        String numComercial = request.getParameter("numComercial");
        
        String codTelCelular = request.getParameter("codTelCelular");
        String codTelFixo = request.getParameter("codTelFixo");
        String codTelComercial = request.getParameter("codTelComercial");
        
        RequestDispatcher rd = null;
        
        if (!senha.equals(senhaRepetida)) {
            rd = request.getRequestDispatcher("alterarUsuario.jsp?mens=" + URLEncoder.encode("Senhas nao conferem","UTF-8"));
            rd.forward(request, response);
            return;
        }
        
        Usuario usuario = (Usuario) new GenericDAO<Usuario>(new Usuario())
	    		.findByFieldEq("codUsuario", Integer.parseUnsignedInt(codUsuario));

        if (usuario != null) {
        	usuario.setNome(nome);
        	usuario.setEmail(email);
        	usuario.setSenha(senha);
            
            // Alterar Usuario
            new GenericDAO<Usuario>(new Usuario()).saveOrUpdate(usuario);
            
            atualizarTelefone(codTelCelular, dddCelular, numCelular, "M", usuario);
            atualizarTelefone(codTelFixo, dddFixo, numFixo, "F", usuario);
            atualizarTelefone(codTelComercial, dddComercial, numComercial, "C", usuario);
            
            request.setAttribute("redirect_txt", "Usuario editado");
            listarUsuarios(request, response);
            //rd = request.getRequestDispatcher("index.jsp");
            //rd.forward(request, response);
        }
	}
	
	/*
	 * 
	 */
	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	    List<Usuario> listaUsuarios = new GenericDAO<Usuario>(new Usuario()).findALL();
	    request.setAttribute("listaUsuarios", listaUsuarios);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios.jsp");
	    dispatcher.forward(request, response);
	}
	
	/*
	 * 
	 */
	private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	    String codUsuario = request.getParameter("codUsuario");
	    
	    Usuario usuario = (Usuario) new GenericDAO<Usuario>(new Usuario())
	    		.findByFieldEq("codUsuario", Integer.parseUnsignedInt(codUsuario));
	    
	    List<Telefone> listaTelefone = new GenericDAO<Telefone>(new Telefone())
	    		.findListByFieldEq("usuario", usuario);
	    
	    for (Telefone telefone : listaTelefone){
	    	switch (telefone.getTipo()){
	    	case "M":
	    		request.setAttribute("telCelular", telefone);
	    		break;
	    	case "C":
	    		request.setAttribute("telComercial", telefone);
	    		break;
    		default:
    			request.setAttribute("telFixo", telefone);
	    	}
	    }
	    
	    request.setAttribute("usuario", usuario);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("alterarUsuario.jsp");
	    dispatcher.forward(request, response);
	}
	
	/*
	 * 
	 */
	private void removerUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	    String codUsuario = request.getParameter("codUsuario");
	    Usuario usuario = new Usuario();
	    usuario.setCodUsuario(Integer.parseUnsignedInt(codUsuario));
	    
	    List<Telefone> listaTelefone = new GenericDAO<Telefone>(new Telefone())
	    		.findListByFieldEq("usuario", usuario);
	    
	    for(Telefone telefone : listaTelefone){
	    	new GenericDAO<Telefone>(new Telefone()).delete(telefone);
	    }
	    
	    new GenericDAO<Usuario>(new Usuario()).delete(usuario);
	    
	    request.setAttribute("redirect_txt", "Usuario excluido");
	    listarUsuarios(request, response);
	    
	    //RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    //dispatcher.forward(request, response);
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		String acao = request.getParameter("acao");

		switch(acao){
		case "alterar":
			mostrarUsuario(request, response);
			break;
		case "remover":
			removerUsuario(request, response);
			break;
		default:
			listarUsuarios(request, response);
		}
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
        try {
        	switch(acao){
        	case "cadastrar":
        		cadastrarUsuario(request, response);
        		break;
        	case "alterar":
        		alterarUsuario(request, response);
        		break;
        	default:
        		System.out.println(acao);
        		break;
        	}
            
        } catch (NoSuchAlgorithmException ex) {
        	System.out.println("[ERRO]" + UsuarioControllerServlet.class.getName() + ": " + ex);
        } catch (InvalidKeySpecException ex) {
        	System.out.println("[ERRO]" + UsuarioControllerServlet.class.getName() + ": " + ex);
        }
    }
	
	@Override
    public String getServletInfo() {
        return "Short description";
    }
	
	private void armazenarTelefone(Telefone telefone, String ddd, String numero, String tipo, Usuario usuario){
		if(ddd != null && numero != null && !ddd.equals("") && !numero.equals("")){
    		telefone.setDdd(ddd);
    		telefone.setNumero(numero);
    		telefone.setTipo(tipo);
    		telefone.setUsuario(usuario);
    		
    		// Alterar Telefone
            new GenericDAO<Telefone>(new Telefone()).saveOrUpdate(telefone);
    	}
	}
	
	private void atualizarTelefone(String codTelefone, String ddd, String numero, String tipo, Usuario usuario){
		Telefone telefone = new Telefone();
        if(codTelefone != null){
        	telefone = (Telefone) new GenericDAO<Telefone>(new Telefone())
    	    		.findByFieldEq("codTelefone", Integer.parseUnsignedInt(codTelefone));
        	
        	if(ddd != null && numero != null && !ddd.equals("") && !numero.equals("")){
        		armazenarTelefone(telefone, ddd, numero, tipo, usuario);
        	}
        	else {
        		// Deletar Telefone
        		new GenericDAO<Telefone>(new Telefone()).delete(telefone);
        	}
        }
        else {
        	armazenarTelefone(telefone, ddd, numero, tipo, usuario);
        }
	}
}
