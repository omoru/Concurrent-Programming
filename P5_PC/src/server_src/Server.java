package server_src;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import users_src.FlujosUsuario;
import users_src.Usuario;
import java.util.concurrent.Semaphore;

public class Server {
	
	//Contiene id, ip y ficheros de los usuarios
	private static ArrayList<Usuario> tabla_informacion_usuarios = new ArrayList<Usuario>();
	//Contiene id, flujo de entrada y flujo de salida de los usuarios
	private static ArrayList<FlujosUsuario> tabla_flujos_usuarios = new ArrayList<FlujosUsuario>();
	private static int PUERTO;
	private static Semaphore s= new Semaphore(1);
	
	
	
	public static void main(String[] args) {
		
		try {
			if(args.length!=1) {
				System.out.println("Usage: Server 555");
				System.out.println("Es necesario el puerto");
				return;
			}
			
			PUERTO = Integer.parseInt(args[0]);
			//Creamos el ServerSocket asociado al puerto
			ServerSocket s = new ServerSocket(PUERTO);
			
			while(true) {
				System.out.println("Esperando nuevas conexiones...");
				new OyenteClient(s.accept()).start(); // detenemos al servidor hasta que llega un cliente
				System.out.println("Se ha establecido una nueva conexión con el cliente");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema en el Server");
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void añadirUsuario(Usuario u) {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabla_informacion_usuarios.add(u);
		s.release();
	}
	
	
	public static void añadirFlujosUsuario(FlujosUsuario fu) {
		tabla_flujos_usuarios.add(fu);
	}
	
	
	
	public static ArrayList<Usuario> getUsersInfo(){
		return new ArrayList<Usuario>(tabla_informacion_usuarios);
	}
	
	
	
	public static ArrayList<FlujosUsuario> getFlujosUsuarios(){
		return new ArrayList<FlujosUsuario>(tabla_flujos_usuarios);
	}
	
	
	
	public static void deleteInfoUsuario(String id_usuario) {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i < tabla_informacion_usuarios.size();i++){
			if(tabla_informacion_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_informacion_usuarios.remove(i);
				s.release();
				return;
			}
		}
		System.out.println("No se ha encontrado la información del usuario");
		s.release();
	}
	
	
	public static void deleteFlujosUsuario(String id_usuario) {
		for(int i=0; i < tabla_flujos_usuarios.size();i++){
			if(tabla_flujos_usuarios.get(i).getIdUsuario().equals(id_usuario)) {
				tabla_flujos_usuarios.remove(i);
				return;
			}
		}
		System.out.println("No se ha encontrado los flujos del usuario");
	}
	
	public static String getOwnerFile(String filename) {
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
	public static ObjectOutputStream getOutputStreamOC(String id_usuario) {
		ObjectOutputStream fout=null;
		boolean encontrado=false;
		for(int i=0; i < tabla_flujos_usuarios.size() && !encontrado;i++){
			if(id_usuario.equals(tabla_flujos_usuarios.get(i).getIdUsuario())) {
				encontrado=true;
				fout = tabla_flujos_usuarios.get(i).getFout();
			}
		}
		return fout;
		
	}
	


}
