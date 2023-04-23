package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class pruebaConexion {
	
	public static void main(String[] args) throws SQLException {
		conexionBase conexion = new conexionBase();
		Connection con = conexion.recuperarConexion();
		
		System.out.println("Probando conexion");
		
		con.close();
		System.out.println("cerrando conexion");
	}

}
