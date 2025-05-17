package controlador; // Paquete donde se encuentra este servlet.

// Importación necesaria.

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.DaoCliente;
import modelo.DaoParcela;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


 // Servlet implementation class Sv_clientes.
 
@WebServlet("/Sv_clientes") // Mapea este servlet a la URL /Sv_clientes.
public class Sv_clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_clientes() {
		super();
		// TODO Auto-generated constructor stub
		// Constructor vacío (se llama automáticamente).
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false); // Obtiene la sesión existente, no crea una nueva.
		PrintWriter out = response.getWriter(); // Objeto para escribir la respuesta al navegador.
		
		
		if (session == null || session.getAttribute("permisos") == null) {
			
			// No hay sesión o permisos -> responde con un JSON para redireccionar al login.
			
		    response.setContentType("application/json");
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().write("{\"redirect\":\"login.html\"}");
		    return;
		}
		
		if (session != null && session.getAttribute("permisos") != null) {
		
			
			String opcion = request.getParameter("op"); // Se obtiene la operación que quiere hacer.
			


			if (opcion != null) {

				if (opcion.equals("1")) {

					// LISTAR
					try {
						System.out.println(DaoCliente.getInstance().listarEnJson(null));
						out.print(DaoCliente.getInstance().listarEnJson(null));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (opcion.equals("2")) {

					// Llenar el formulario.

					try {
						int id = Integer.parseInt(request.getParameter("id"));
						Cliente c = new Cliente();
						System.out.println("Editar: " + c.toJson(id));
						out.print(c.toJson(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (opcion.equals("3")) {

					try {
						int id = Integer.parseInt(request.getParameter("id"));
						Cliente c = new Cliente();
						c.borrar(id);
						response.sendRedirect("newCliente.html"); // Redirige después de borrar.

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			
			
			
		} else {
			// No hay sesión activa: redirige al login.
			System.out.println("No hay sesion");
			out.print("{'sesion':0}");
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Recoge los parámetros enviados desde un formulario.
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String password = request.getParameter("password");
		String dni = request.getParameter("dni");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String correoElectronico = request.getParameter("correoElectronico");
		String preferenciasCliente = request.getParameter("preferenciasCliente");

		// Crea un objeto Cliente con los datos.
		
		Cliente c = new Cliente(nombre, apellidos, password, dni, direccion, telefono, fechaNacimiento,
				correoElectronico, preferenciasCliente);
		System.out.println(c.toString());

		int id = Integer.parseInt(request.getParameter("id")); // Toma el ID que viene (0 si es nuevo).

		if (id != 0) {

			try {
				c.actualizar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// Inserción en la base de datos.

			try {
				c.insertar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		response.sendRedirect("newCliente.html"); // Siempre redirige después de guardar.
		
	}
}