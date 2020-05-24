package server_src;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import users_src.File;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class MonitorServer {
	
	private ArrayList<Usuario> tabla_informacion_usuarios= new ArrayList<Usuario>();;//Contiene id, ip y ficheros de los usuarios
	private ArrayList<FlujosUsuario> tabla_flujos_usuarios = new ArrayList<FlujosUsuario>();//Contiene id, flujo de entrada y flujo de salida de los usuarios
	final Lock lockTablaFlujos = new ReentrantLock();//controla el acceso a la tabla_flujos_usuarios
	final Lock lockTablaInfo = new ReentrantLock();//controla el acceso a la tabla información de usuarios
	
	
	public MonitorServer() {
				
	}
	
	//Devuelve true si el id_usuario yá está en server
	public boolean userAlreadyExists(String id_usuario) {
		lockTablaInfo.lock();
		for(int i=0; i < tabla_informacion_usuarios.size();i++){
			if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				lockTablaInfo.unlock();
				return true;
			}
		}
		lockTablaInfo.unlock();
		return false;
	}
	
	//Devuelve el id del usuario que tiene el archivo indicado por parámetros
	 public String getOwnerFile(String filename) {
		lockTablaInfo.lock();
		String id_owner=null;
		boolean encontrado= false;
		for(int i=0; i < tabla_informacion_usuarios.size() && !encontrado;i++){
			for(File f:tabla_informacion_usuarios.get(i).getFiles()) {
				if(f.getFilename().equals(filename)) {
					id_owner= tabla_informacion_usuarios.get(i).getIdUsuario();
					encontrado=true;
					break;					
				}
			}
		}
		lockTablaInfo.unlock();
		return id_owner;
	}
	
	
	 	
	 //Añade al usuario a la base de datos
	 public void addUser(Usuario u) {
		lockTablaInfo.lock();
		tabla_informacion_usuarios.add(u);
		lockTablaInfo.unlock();
	}
	
	 //Añade el archivo a la lista  del usuario en la base de datos.
	 //Devuelve true si lo consigue y false en otro caso
	 public boolean addFile(String filename,String ruta_filename,String id_usuario) {
		 lockTablaInfo.lock();
		 for(int i=0; i < tabla_informacion_usuarios.size();i++){
				if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
					this.tabla_informacion_usuarios.get(i).addFile(new String(filename), new String(ruta_filename));
					lockTablaInfo.unlock();
					return true;
				}
			}
		 lockTablaInfo.unlock();
		 return false;
	 }
	 
	 //Devuelve la tabla de informacion de los usuarios con sus ficheros
	 public ArrayList<Usuario> getUsersInfo(){
		 lockTablaInfo.lock();
		 ArrayList<Usuario> table_info = new ArrayList<Usuario>();
	        for(Usuario user : tabla_informacion_usuarios) {
	        	table_info.add(new Usuario(user));
	        }
	     lockTablaInfo.unlock();
	     return table_info;
	}
	
	 //Borra la informacion del usuario con id_usuario de la tabla de informacion
	 public void deleteInfoUsuario(String id_usuario) {
		lockTablaInfo.lock();
		for(int i=0; i < tabla_informacion_usuarios.size();i++){
			if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_informacion_usuarios.remove(i);
				lockTablaInfo.unlock();
				return;
			}
		}
		lockTablaInfo.unlock();
	}
	
	
	 //añade los canales del usuario a la tabla de flujos
	 public void añadirFlujosUsuario(FlujosUsuario fu) {
		lockTablaFlujos.lock();
		tabla_flujos_usuarios.add(fu);
		lockTablaFlujos.unlock();
	}
	 
	 
	 //Devuelve la referencia al canal de salida del usuario con id_usuario
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
	
	
	
	//Devuelve la tabla de flujos de los usuarios
	public ArrayList<FlujosUsuario> getFlujosUsuarios(){
		lockTablaFlujos.lock();
		ArrayList<FlujosUsuario> flujos = new ArrayList<FlujosUsuario>();
		for(int i=0; i < this.tabla_flujos_usuarios.size();i++) {
			FlujosUsuario flujos_aux = new FlujosUsuario(tabla_flujos_usuarios.get(i).getIdUsuario(),
					tabla_flujos_usuarios.get(i).getFout(), tabla_flujos_usuarios.get(i).getFin());
			flujos.add(flujos_aux);
		}
		lockTablaFlujos.unlock();
		return flujos;
	}
	
	//Borra la informacion del usuario con id_usuario de la tabla de flujos
	 public void deleteFlujosUsuario(String id_usuario) {
		lockTablaFlujos.lock();
		for(int i=0; i < tabla_flujos_usuarios.size();i++){
			if(tabla_flujos_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_flujos_usuarios.remove(i);
				lockTablaFlujos.unlock();
				return;
			}
		}
		lockTablaFlujos.unlock();
	}
	
	
}
