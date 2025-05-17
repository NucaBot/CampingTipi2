package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// La clase DBconexion gestiona la conexión a la BD de forma centralizada.
public class DBconexion {

	/*
	 * URL de la base de datos MySQL a la que se conectará. Contiene: Servidor:
	 * vl27314.dinaserver.com Puerto: 3306 Base de datos: devel_tipi java Copiar
	 * Editar
	 */

	public static final String JDBC_URL = "jdbc:mysql://vl27314.dinaserver.com:3306/devel_tipi";

	// Representa la única conexión a la BD (patrón Singleton).

	public static Connection instance = null;

	// Constructor privado: impide crear instancias de esta clase desde fuera.
	// Esto obliga a usar siempre los métodos estáticos.

	private DBconexion() {

	}

	public static Connection getConection() throws SQLException {

		if (instance == null) {

			// Crea un objeto Properties con las credenciales de la base de datos:

			Properties props = new Properties();
			props.put("user", "devel_tipi");
			props.put("password", "Proyectos_2025");

			// Crea una nueva conexión usando la URL y las credenciales.

			instance = DriverManager.getConnection(JDBC_URL, props);
		}

		return instance;
	}
}
