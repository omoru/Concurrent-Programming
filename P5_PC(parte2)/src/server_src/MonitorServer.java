package server_src;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import users_src.FlujosUsuario;
import users_src.Usuario;

public class MonitorServer {
	//Contiene id, ip y ficheros de los usuarios
	private ArrayList<Usuario> tabla_informacion_usuarios = new ArrayList<Usuario>();
	//Contiene id, flujo de entrada y flujo de salida de los usuarios
	private ArrayList<FlujosUsuario> tabla_flujos_usuarios = new ArrayList<FlujosUsuario>();
	final Lock lockTablaFlujos = new ReentrantLock();
	final Lock lockTablaInfo = new ReentrantLock();
	
	
	public MonitorServer() {
		
	}
	
	
	public String getOwnerFile(String filename) {
		String id_owner=null;
		boolean encontrado= false;
		for(int i=0; i < tabla_informacion_usuarios.size() && !encontrado;i++){
			for(String s:tabla_informacion_usuarios.get(i).getFicheros()) {
				if(s.equals(filename)) {
					id_owner= tabla_informacion_usuarios.get(i).getIdUsuario();
					encontrado=true;
					break;					
				}
			}
		}
		return id_owner;
	}
	
	 public ObjectOutputStream getOutputStreamOC(String id_usuario) {
		lockTablaFlujos.lock();
		ObjectOutputStream fout=null;
		boolean encontrado=false;
		for(int i=0; i < tabla_flujos_usuarios.size() && !encontrado;i++){
			if(id_usuario.equals(tabla_flujos_usuarios.get(i).getIdUsuario())) {
				encontrado=true;
				fout = tabla_flujos_usuarios.get(i).getFout();
			}
		}
		lockTablaFlujos.unlock();
		return fout;
	}
	
	 public void addUser(Usuario u) {
		lockTablaInfo.lock();
		tabla_informacion_usuarios.add(u);
		lockTablaInfo.unlock();
	}
	
	
	
	 public  void añadirFlujosUsuario(FlujosUsuario fu) {
		lockTablaFlujos.lock();
		tabla_flujos_usuarios.add(fu);
		lockTablaFlujos.unlock();
	}
	
	 public ArrayList<Usuario> getUsersInfo(){
		return new ArrayList<Usuario>(tabla_informacion_usuarios);
	}
	
	 public void deleteInfoUsuario(String id_usuario) {
		lockTablaInfo.lock();
		for(int i=0; i < tabla_informacion_usuarios.size();i++){
			if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_informacion_usuarios.remove(i);
				lockTablaInfo.unlock();
				return;
			}
		}
		System.out.println("No se ha encontrado la información del usuario");
		lockTablaInfo.unlock();
	}
	
	public ArrayList<FlujosUsuario> getFlujosUsuarios(){
		return new ArrayList<FlujosUsuario>(tabla_flujos_usuarios);
	}
	
	 public void deleteFlujosUsuario(String id_usuario) {
		lockTablaFlujos.lock();
		for(int i=0; i < tabla_flujos_usuarios.size();i++){
			if(tabla_flujos_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_flujos_usuarios.remove(i);
				lockTablaFlujos.unlock();
				return;
			}
		}
		System.out.println("No se ha encontrado los flujos del usuario");
		lockTablaFlujos.unlock();
	}
	
	
}
