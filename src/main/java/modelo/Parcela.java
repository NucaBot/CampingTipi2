package modelo;

// Importa SQLException para trabajar con operaciones de BD que puedan fallar.

import java.sql.SQLException;

public class Parcela {

	// Atributos de la clase Parcela.

	private int idParcela;
	private String tipoParcela;
	private String descripcion;
	private String foto;
	private double precioNoche;

	// Constructor sin parámetros, para crear una parcela sin inicializar valores.

	public Parcela() {

	}
	// Constructor con parámetros, con todos los atributos especificados.

	public Parcela(int idParcela, String tipoParcela, String descripcion, String foto, double precioNoche) {
		this.idParcela = idParcela;
		this.tipoParcela = tipoParcela;
		this.descripcion = descripcion;
		this.foto = foto;
		this.precioNoche = precioNoche;

	}

	// Constructor sin ID (para insertar nuevas parcelas donde el ID es automático).

	public Parcela(String tipoParcela, String descripcion, String foto, double precioNoche) {

		this.tipoParcela = tipoParcela;
		this.descripcion = descripcion;
		this.foto = foto;
		this.precioNoche = precioNoche;

	}

	// Constructor mínimo (solo ID y tipo de parcela), para listados básicos o
	// referencias rápidas.

	public Parcela(int id, String tipoParcela) {

		this.idParcela = id;
		this.tipoParcela = tipoParcela;

	}

	// Aplicamos los getter (para acceder) and setter (para modificar) los
	// atributos.

	public int getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(int idParcela) {
		this.idParcela = idParcela;
	}

	public String getTipoParcela() {
		return tipoParcela;
	}

	public void setTipoParcela(String tipoParcela) {
		this.tipoParcela = tipoParcela;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	// Método para insertar esta Parcela en la BD.

	public void insertar() throws SQLException {
		DaoParcela.getInstance().insertar(this);
	}

	// Método para obtener datos de una parcela desde la BD dado su Id.

	public void obtenerDatos(int id) throws SQLException {

		Parcela aux = DaoParcela.getInstance().obtenerDatos(id);

		// Copia todos los datos obtenidos en este objeto actual.

		this.idParcela = aux.getIdParcela();
		this.tipoParcela = aux.getTipoParcela();
		this.descripcion = aux.getDescripcion();
		this.foto = aux.getFoto();
		this.precioNoche = aux.getPrecioNoche();

	}

	// Devuelve una representación en forma de String de la parcela.

	@Override
	public String toString() {
		return "Parcela [idParcela=" + idParcela + ", tipoParcela=" + tipoParcela + ", descripcion=" + descripcion
				+ ", foto=" + foto + ", precioNoche=" + precioNoche + "]";
	}

}
