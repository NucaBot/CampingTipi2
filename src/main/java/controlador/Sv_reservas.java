package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.DaoReserva;
import modelo.Estado;
import modelo.Metodo;
import modelo.Parcela;
import modelo.Reserva;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class Sv_reservas
 */
@WebServlet("/Sv_reservas")
public class Sv_reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_reservas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false); // no crea nueva sesión
		PrintWriter out = response.getWriter();
		
		
			
		
if (session != null && session.getAttribute("permisos") != null) {
		
			
			String opcion = request.getParameter("op");

			if (opcion != null) {

				if (opcion.equals("1")) {
		
					try {
						out.print(DaoReserva.getInstance().listarEnJson());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
					
			}
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int idCliente = Integer.parseInt(request.getParameter("clienteId"));
		int idParcela = Integer.parseInt(request.getParameter("parcela"));
		int adultos = Integer.parseInt(request.getParameter("adulto"));
		int menores = Integer.parseInt(request.getParameter("menor"));
		int vehiculo = Integer.parseInt(request.getParameter("vehiculo"));
		int luz = Integer.parseInt(request.getParameter("tomaLuz"));
		int agua = Integer.parseInt(request.getParameter("tomaAgua"));
		String fechaEntrada = request.getParameter("fechaEntrada");
		String fechaSalida = request.getParameter("fechaSalida");
		int idmetodo = Integer.parseInt(request.getParameter("metodo"));
		int idestado = Integer.parseInt(request.getParameter("estado"));

		try {

			Cliente c = new Cliente();
			c.obtenerDatos(idCliente);

			Parcela p = new Parcela();
			p.obtenerDatos(idParcela);

			Estado e = new Estado();
			e.obtenerDatos(idestado);

			Metodo m = new Metodo();
			m.obtenerDatos(idmetodo);

			Reserva r = new Reserva(c, p, adultos, menores, vehiculo, luz, agua, fechaEntrada, fechaSalida, m, e);
			r.insertar();

			// System.out.println(r.toString());

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
