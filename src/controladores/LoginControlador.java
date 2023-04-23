package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.usuario;
import views.Login;
import views.MenuUsuario;

public class LoginControlador implements ActionListener {
	private Login vista;
	
	public LoginControlador(Login vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getNombre();
		String contrasenia = vista.getContrasenia();
		

        if(usuario.validarUsuario(nombre, contrasenia)){
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            vista.dispose();	 
        }else {
            JOptionPane.showMessageDialog(vista, "Usuario o Contraseña no válidos");
        }
	}

}
