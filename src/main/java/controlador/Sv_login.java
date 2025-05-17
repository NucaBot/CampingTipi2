package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Sv_login
 */
@WebServlet("/Sv_login")
public class Sv_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_login() {
        super();
        // TODO Auto-generated constructor stub
        
                
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		//Pendiente de cifrar contraseñas
		if(user.equals("admin") && pass.equals("1234")) {
			
			HttpSession session = request.getSession(); // Crea una nueva sesión si no existe
			session.setAttribute("permisos",1);
			response.sendRedirect("indexAdmin.html");
						
		}
		
	
	
	
	}

}
