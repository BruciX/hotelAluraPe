package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import CRUD.HuespedesCRUD;
import factory.conexionBase;
import modelo.Huespedes;

public class huespedesControlador {
	private HuespedesCRUD huespedesCrud;
	
	
	public huespedesControlador() {
		Connection connection = new conexionBase().recuperarConexion();
		this.huespedesCrud = new HuespedesCRUD(connection);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesCrud.guardar(huespedes);
	}
	
	public  List<Huespedes> buscarHuespedes(){
		return this.huespedesCrud.buscar();
	}
	
	public  List<Huespedes> buscarHuespedesId(String id){
		return this.huespedesCrud.buscarId(id);
	}
	
	public void actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,Integer id_reserva) {
		this.huespedesCrud.Actualizar(nombre, apellido, fechaNacimiento, nacionalidad, telefono,id_reserva);
	}
	 
	public void Eliminar(Integer idReserva) {
		this.huespedesCrud.Eliminar(idReserva);
	}
	
	
}
