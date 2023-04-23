package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;



public class HuespedesCRUD {

	private Connection conexion;
	
	public HuespedesCRUD(Connection conexion) {
		this.conexion= conexion;
	}
	
	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,"
					+ "nacionalidad,telefono,id_reserva) VALUES(?,?,?,?,?,?)";
			try(PreparedStatement pstm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setDate(3, huespedes.getFechaNacimiento());
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdReserva());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while (rst.next()) {
						huespedes.setId(rst.getInt(1));
					}
				}
				
			}
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Huespedes> buscar(){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre , apellido, fecha_nacimiento, nacionalidad,telefono, id_reserva FROM huespedes";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
			
				pstm.execute();
				
				transFormarResultSetEnReserva(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huespedes> buscarId(String id){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre , apellido, fecha_nacimiento,nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva=?";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transFormarResultSetEnReserva(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public void Eliminar(Integer id) {
		try(PreparedStatement stm = conexion.prepareStatement("DELETE FROM huespedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	} 
	
	public void Actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer id_reserva,Integer id) {
		try (PreparedStatement stm = conexion.prepareStatement("UPDATE huespedes SET nombre=? , apellido=?, fecha_nacimiento=?, nacionalidad telefono=?, id_reserva=? WHERE  id= ?")) {
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaNacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, id_reserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transFormarResultSetEnReserva(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException{
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				Huespedes producto = new Huespedes(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4), rst.getString(5),rst.getString(6),rst.getInt(7));
				huespedes.add(producto);
			}
		}
	}
	
}
