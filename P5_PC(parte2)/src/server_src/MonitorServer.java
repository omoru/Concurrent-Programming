package server_src;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import users_src.File;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class MonitorServer {
	
	private ArrayList<Usuario> tabla_informacion_usuarios;//Contiene id, ip y ficheros de los usuarios
	private ArrayList<FlujosUsuario> tabla_flujos_usuarios;	//Contiene id, flujo de entrada y flujo de salida de los usuarios
	final Lock lockTablaFlujos = new ReentrantLock();
	final Lock lockTablaInfo = new ReentrantLock();
	
	
	public MonitorServer() {
		this.tabla_informacion_usuarios= new ArrayList<Usuario>();
		this.tabla_flujos_usuarios= new ArrayList<FlujosUsuario>();
		
	}
	
	
	public boolean userAlreadyExists(String id_usuario) {
		for(int i=0; i < tabla_informacion_usuarios.size();i++){
			if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario))
				return true;
		}
		return false;
	}
	public String getOwnerFile(String filename) {
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
	
	 public boolean addFile(String filename,String ruta_filename,String id_usuario) {
		 for(int i=0; i < tabla_informacion_usuarios.size();i++){
				if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
					this.tabla_informacion_usuarios.get(i).addFile(new String(filename), new String(ruta_filename));
					return true;
				}
			}
		 return false;
	 }
	
	
	 public  void añadirFlujosUsuario(FlujosUsuario fu) {
		lockTablaFlujos.lock();
		tabla_flujos_usuarios.add(fu);
		lockTablaFlujos.unlock();
	}
	
	 public ArrayList<Usuario> getUsersInfo(){
		 ArrayList<Usuario> table_info = new ArrayList<Usuario>();
	        for(Usuario user : tabla_informacion_usuarios) {
	        	table_info.add(new Usuario(user));
	        }
	        return table_info;
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
