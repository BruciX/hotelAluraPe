package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import CRUD.ReservaCrud;
import factory.conexionBase;
import modelo.reserva;

public class ReservaControlador {
	private ReservaCrud reservas;
	
	public ReservaControlador() {
		Connection con = new conexionBase().recuperarConexion();
		this.reservas = new ReservaCrud(con);	
	}
	
	public void guardar (reserva res) {
		this.reservas.guardar(res);
	}
	
	public List<reserva> buscar(){
		return this.reservas.buscar();
	}
	public List<reserva> buscarId(String id){
		return this.reservas.buscarId(id);
	}
		
	public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		this.reservas.Actualizar(fechaE, fechaS, valor,formaPago, id);
	}
	
	public void Eliminar(Integer id) {
		this.reservas.Eliminar(id);
	}
}
