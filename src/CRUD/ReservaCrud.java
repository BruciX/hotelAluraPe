package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.reserva;

public class ReservaCrud {
	private Connection conexion;

	public ReservaCrud(Connection conexion) {
	this.conexion= conexion;
	}
	
	public void guardar (reserva res) {
		String sql = "INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_de_pago)"
				+ "VALUES(?,?,?,?)";
		try (PreparedStatement pstm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setDate(1, res.getFechaE());
			pstm.setDate(2, res.getFechaS());
			pstm.setString(3, res.getValor());
			pstm.setString(4, res.getFormaPago());
			
			pstm.executeUpdate();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while (rst.next()) {
					res.setId(rst.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public List<reserva> buscar(){
		List<reserva> reservas = new ArrayList<reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor,forma_de_pago FROM reservas";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.execute();
				
				transFormarResultSetEnReserva(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<reserva> buscarId(String id){
		List<reserva> reservas = new ArrayList<reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor,forma_de_pago FROM reservas WHERE  id= ?";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transFormarResultSetEnReserva(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public void Eliminar(Integer id) {
		try(PreparedStatement stm = conexion.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();  
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		try (PreparedStatement stm = conexion.prepareStatement("UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?,forma_de_pago=? WHERE  id= ?")) {
			stm.setDate(1, fechaE);
			stm.setDate(2, fechaS);
			stm.setString(3, valor);
			stm.setString(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transFormarResultSetEnReserva(List<reserva> reservas, PreparedStatement pstm) throws SQLException{
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				reserva producto = new reserva(rst.getInt(1),rst.getDate(2),rst.getDate(3),rst.getString(4), rst.getString(5));
				reservas.add(producto);
			}
		}
	}
	
}
