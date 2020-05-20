package server_src;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import users_src.FlujosUsuario;
import users_src.Usuario;
import java.util.concurrent.Semaphore;

public class Server extends Thread {
	
	
	private int PUERTO;
	private String ip_server;
	private MonitorServer monitor;
	
	
	public Server(int puerto,String ip_server,MonitorServer monitor) {
		this.PUERTO= puerto;
		this.monitor = monitor;
		this.ip_server = ip_server;
	}
	
	
	
	public void run(){
		ServerSocket s;
		try {
			s = new ServerSocket(PUERTO);
			while(true) {
				System.out.println("Esperando nuevas conexiones...");
				new OyenteClient(s.accept(),this).start(); // detenemos al servidor hasta que llega un cliente
				System.out.println("Se ha establecido una nueva conexión con el cliente");
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String getIpServer() {
		return this.ip_server;
	}
	public boolean userAlreadyExists(String id_user) {
		return monitor.userAlreadyExists(id_user);
	}
	public void añadirUsuario(Usuario u) {
		monitor.addUser(u);
	}
	
	
	public void añadirFlujosUsuario(FlujosUsuario fu) {
		monitor.añadirFlujosUsuario(fu);		
	}
	
	
	
	public ArrayList<Usuario> getUsersInfo(){
		return monitor.getUsersInfo();
	}
	
	public  boolean addFile(String filename,String ruta_filename,String id_usuario) {
		return monitor.addFile(filename,ruta_filename,id_usuario);
	}
	
	
	
	public ArrayList<FlujosUsuario> getFlujosUsuarios(){
		return monitor. getFlujosUsuarios();
	}
	
	
	public void deleteInfoUsuario(String id_usuario) {
		monitor.deleteInfoUsuario(id_usuario);
	}
	
	
	public void deleteFlujosUsuario(String id_usuario) {
		monitor.deleteFlujosUsuario(id_usuario);
	}
	
	public String getOwnerFile(String filename) {
		return monitor.getOwnerFile(filename);
	}
	public ObjectOutputStream getOutputStreamOC(String id_usuario) {
		return monitor.getOutputStreamOC(id_usuario);
	}



	


}
