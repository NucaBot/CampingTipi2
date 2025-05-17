package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoEstados {



	private Connection con = null;
	public static DaoEstados instance = null;
	
	public DaoEstados() throws SQLException {
		con = DBconexion.getConection();
	}
	
	
	//Singleton
	public static DaoEstados getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoEstados();	
		}
		return instance;
		
	}
	
	
public ArrayList<Estado> listar() throws SQLException {
		
		String sql;
		PreparedStatement ps;
	
			
			sql = "SELECT * from estados";
			ps = con.prepareStatement(sql);
		
			
			
		ResultSet rs = ps.executeQuery();
		
		
		ArrayList<Estado> result = null;
		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
		result.add(new Estado(rs.getInt(1),rs.getString(2)));
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

public Estado obtenerDatos(int id) throws SQLException {
	
	String sql;
	PreparedStatement ps;

		
		sql = "SELECT * from estados WHERE idEstado=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Estado e = new Estado(rs.getInt(1),rs.getString(2));
	
		return e;
}
	
	
	
	
	
	
	
}
