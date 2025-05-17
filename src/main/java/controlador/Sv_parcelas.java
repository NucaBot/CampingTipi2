package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Cliente;
import modelo.DaoCliente;
import modelo.DaoParcela;
import modelo.Parcela;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class Sv_parcelas
 */
@WebServlet("/Sv_parcelas")
@MultipartConfig

public class Sv_parcelas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pathFiles = "C:\\Users\\Usuario\\DEV\\Eclipse-Nuria\\campingTipiMio\\src\\main\\webapp\\fotos";
	private File uploads = new File(pathFiles);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_parcelas() {
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
		
		
		if (session == null || session.getAttribute("permisos") == null) {
		    response.setContentType("application/json");
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().write("{\"redirect\":\"login.html\"}");
		    return;
		}
		
		if (session != null && session.getAttribute("permisos") != null) {
		
			
			String opcion = request.getParameter("op");

			if (opcion != null) {

				if (opcion.equals("1")) {

					// LISTAR
					try {
						System.out.println(DaoParcela.getInstance().listarEnJson(null));
						out.print(DaoParcela.getInstance().listarEnJson(null));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (opcion.equals("2")) {

					// llenar formulario

					

				} else if (opcion.equals("3")) {

					
				} else if (opcion.equals("4")) {
					
					try {
						System.out.println("llenando Select");

						System.out.println(DaoParcela.getInstance().listarSelect());
						out.print(DaoParcela.getInstance().listarSelect());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			}
			
			
			
		} else {
			// No hay sesión: redirige al login
			System.out.println("No hay sesion");
			out.print("{'sesion':0}");
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipoParcela = request.getParameter("tipoParcela");
		String descripcion = request.getParameter("descripcion");
		double precioNoche = Double.parseDouble(request.getParameter("precioNoche"));
		
		
		//Tratamiento imagen
		
		
		//recupero la foto del formulario
		Part part = request.getPart("foto");
		//obtengo nombre del archivo (existen varios metodos)

		Path path = Paths.get(part.getSubmittedFileName());
		String fileName = path.getFileName().toString();
		//preparo el buffer para el guardado
		InputStream input = part.getInputStream();
		
		String ruta ="";
		if(input != null) {
			//creo el archivo
			File file = new File(uploads, fileName);
			//obtengo la ruta para la bd (ojo, es buena idea solo guardar el nombre del archvio)
			ruta = file.getAbsolutePath();
			System.out.println("La ruta de la foto es: " + ruta);
			//Copio la foto
			try {
				Files.copy(input, file.toPath());

			} catch (Exception e) {
				// TODO: handle exception
				PrintWriter respuesta = response.getWriter();
				respuesta.print("Error al copiar la foto");
				System.out.println("Error al copiar la foto");
			}
		}

		//---------------------------------------------------------
				

		Parcela p = new Parcela(tipoParcela, descripcion,fileName , precioNoche);
		
		
		
		System.out.println(p.toString());

	
				try {
					p.insertar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		
		
		
		
		response.sendRedirect("newParcela.html");
	}

}
