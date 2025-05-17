package modelo;

import java.sql.SQLException;

public class Cliente {

	// Atributos de la clase Cliente.

	private int idCliente;
	private String nombre;
	private String apellidos;
	private String password;
	private String dni;
	private String direccion;
	private String telefono;
	private String fechaNacimiento;
	private String correoElectronico;
	private String preferenciasCliente;

	// Constructor sin parámetros.

	
	
	public Cliente() {

	}

	// Constructor con parámetros.

	public Cliente(int idCliente, String nombre, String apellidos, String password, String dni, String direccion,
		String telefono, String fechaNacimiento, String correoElectronico, String preferenciasCliente){
			
			this.idCliente= idCliente;
			this.nombre=nombre;
			this.apellidos=apellidos;
			this.password=password;
			this.dni=dni;
			this.direccion=direccion;
			this.telefono=telefono;
			this.fechaNacimiento=fechaNacimiento;
			this.correoElectronico=correoElectronico;
			this.preferenciasCliente=preferenciasCliente;
			
		}
	
	public Cliente(String nombre, String apellidos, String password, String dni, String direccion,
			String telefono, String fechaNacimiento, String correoElectronico, String preferenciasCliente){
				
		
				this.nombre=nombre;
				this.apellidos=apellidos;
				this.password=password;
				this.dni=dni;
				this.direccion=direccion;
				this.telefono=telefono;
				this.fechaNacimiento=fechaNacimiento;
				this.correoElectronico=correoElectronico;
				this.preferenciasCliente=preferenciasCliente;
				
			}

	//Aplicamos los getter and setter para cada atributo.

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPreferenciasCliente() {
		return preferenciasCliente;
	}

	public void setPreferenciasCliente(String preferenciasCliente) {
		this.preferenciasCliente = preferenciasCliente;
	}
	
	// Método para insertar el objeto actual en la BD usando la instancia DaoCliente.
	
	public void insertar() throws SQLException {
		
		DaoCliente.getInstance().insertar(this);
	}
	
	// Método para actualizar un cliente existente en la BD.
	
	public void actualizar(int id) throws SQLException {
		
		this.setIdCliente(id); // Asigna el id al objeto actual.
		int filas = DaoCliente.getInstance().actualizar(this); // Actualiza el cliente y devuelve cuántas filas fueron afectadas.
		System.out.println("Campos actualizados: " + filas);
		
	}
	
	// Método para obtener los datos de un cliente en formato JSON.
	
	public String toJson(int id) throws SQLException {
		
		return  DaoCliente.getInstance().toJson(id); // Devuelve una representación JSON del cliente con ese id.
		
	}
	
	 // Método para borrar un cliente de la BD.
	 
	public void borrar(int id) throws SQLException {
		
		 DaoCliente.getInstance().borrar(id); // Elimina el cliente en la BD por id.
				
	}
	
	// Método para obtener datos de un cliente y copiar esos datos en el objeto actual.
	
	public void obtenerDatos(int id) throws SQLException {
		
		Cliente aux  = DaoCliente.getInstance().obtenerDatos(id); // Busca el cliente con ese id en la base de datos
		
    // Copia todos los atributos del cliente encontrado al objeto actual.
		
		this.idCliente= aux.getIdCliente();
		this.nombre= aux.getNombre();
		this.apellidos= aux.getApellidos();
		this.password= aux.getPassword();
		this.dni= aux.getDni();
		this.direccion=aux.getDireccion();
		this.telefono=aux.getTelefono();
		this.fechaNacimiento=aux.getFechaNacimiento();
		this.correoElectronico=aux.getCorreoElectronico();
		this.preferenciasCliente=aux.getPreferenciasCliente();	
		
	}
	
	// Copia todos los atributos del cliente encontrado, al objeto actual.
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", password="
				+ password + ", dni=" + dni + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", fechaNacimiento=" + fechaNacimiento + ", correoElectronico=" + correoElectronico
				+ ", preferenciasCliente=" + preferenciasCliente + "]";
	}
	
	
	
	}


