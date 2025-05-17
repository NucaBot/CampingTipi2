package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoMetodos {

	

	private Connection con = null;
	public static DaoMetodos instance = null;
	
	public DaoMetodos() throws SQLException {
		con = DBconexion.getConection();
	}
	
	
	//Singleton
	public static DaoMetodos getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoMetodos();	
		}
		return instance;
		
	}
	
	
public ArrayList<Metodo> listar() throws SQLException {
		
		String sql;
		PreparedStatement ps;
	
			
			sql = "SELECT * from metodos";
			ps = con.prepareStatement(sql);
		
			
			
		ResultSet rs = ps.executeQuery();
		
		
		ArrayList<Metodo> result = null;
		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
		result.add(new Metodo(rs.getInt(1),rs.getString(2)));
		}
		rs.close();
		ps.close();
		return result;
		
	}


public String listarEnJson() throws SQLException {
	
	Gson gson = new Gson();
	String JSON;
	
	JSON = gson.toJson(this.listar());
		
	return JSON; 			
}

public Metodo obtenerDatos(int id) throws SQLException {
	

	String sql;
	PreparedStatement ps;

		
		sql = "SELECT * from metodos WHERE idMetodo=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
	
		
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Metodo m = new Metodo(rs.getInt(1),rs.getString(2));
		
		return m;
	
}
	
}
