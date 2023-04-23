package modelo;

import java.sql.Date;

public class reserva {
private Integer id;
private Date fechaE;
private Date fechaS;
private String valor;
private String formaPago;


public reserva(Date fechaE, Date fechaS, String valor, String formaPago) {
	super();
	this.fechaE = fechaE;
	this.fechaS = fechaS;
	this.valor = valor;
	this.formaPago = formaPago;
}


public reserva(Integer id,Date fechaE, Date fechaS, String valor, String formaPago) {
	super();
	this.id = id;
	this.fechaE = fechaE;
	this.fechaS = fechaS;
	this.valor = valor;
	this.formaPago = formaPago;
}
 


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Date getFechaE() {
	return fechaE;
}
public void setFechaE(Date fechaE) {
	this.fechaE = fechaE;
}
public Date getFechaS() {
	return fechaS;
}
public void setFechaS(Date fechaS) {
	this.fechaS = fechaS;
}
public String getValor() {
	return valor;
}
public void setValor(String valor) {
	this.valor = valor;
}
public String getFormaPago() {
	return formaPago;
}
public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}


}

