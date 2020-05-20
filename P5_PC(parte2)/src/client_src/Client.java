package client_src;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.ClientMainWindow;
import msg_src.Mensaje;
import msg_src.MsgAñadirArchivo;
import msg_src.MsgCerrarConexion;
import msg_src.MsgConexion;
import msg_src.MsgListaUsuarios;
import msg_src.MsgPedirFichero;

public class Client extends Thread{
		
	private int puerto;//puerto de conexion al server
	private String ip_client;//ip del cliente
	private String mode;
	private OyenteServer OS;
	private ObjectOutputStream f_out;
	private String ip_host;//ip server
	private String id_usuario;
	//private ArrayList<String> ficheros;//ficheros que tiene el usuario
	private Semaphore waitConected = new Semaphore(0);
	
	public Client(int puerto, String ip_client, String ip_host,String runMode) {
		this.puerto = puerto;
		this.ip_client = ip_client;
		this.ip_host= ip_host;
		//this.ficheros = new ArrayList<String>();
		this.mode= runMode;
		
	}
	
	public Semaphore getSemaphore() {
		return this.waitConected;
	}
	
	public String getMode() {
		return this.mode;
	}
	public void run() {
		
			
			if(mode.equalsIgnoreCase("GUI")) {
				runGUI();
				
			}
			else if(mode.equalsIgnoreCase("BATCH")) 
				runBatch();
				
			
		
		
		
		
	}
	
	private void runGUI() {
		try {			
			String name = JOptionPane.showInputDialog("Introduzca su nombre porfavor");
			while(name.length()==0) {
				name = JOptionPane.showInputDialog("El nombre no puede estar vacio");
			}
			this.id_usuario = name;
			Socket socket = new Socket(ip_client,puerto);
			this.f_out= new ObjectOutputStream(socket.getOutputStream());
			this.OS =new OyenteServer(socket,this);
				

			
			
			Client cliente = this;
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					new ClientMainWindow(cliente);						
				}	
				
			});
		
			OS.start();
			Mensaje m = new MsgConexion(ip_client,ip_host,id_usuario,new ArrayList<String>());
			f_out.writeObject(m);	
		}catch(Exception e) {
			System.out.println("Mal funcionamiento en Cliente");
			e.printStackTrace();
			return;
		
		}	
	}
	
	private void runBatch() {
		try {
			Socket socket = new Socket(ip_client,puerto);
			this.f_out= new ObjectOutputStream(socket.getOutputStream());
				
			//El usuario nos proporciona el nombre 
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca su nombre porfavor ");
			this.id_usuario = sc.nextLine();
			
			/*System.out.println("Introduce uno a uno los ficheros que tienes disponibles e introduce EXIT cuando hayas terminado:");
			String fichero= sc.nextLine();
			while(!fichero.equalsIgnoreCase("EXIT")) {
				File f = new File(fichero);
				if(f.exists()) {
					ficheros.add(fichero);
				}
				else System.out.println("El archivo no existe, introduzca otro o EXIT para salir");
		
				fichero=sc.nextLine();
				
			}
			*/
			new OyenteServer(socket,this).start();
			Mensaje m = new MsgConexion(ip_client,ip_host,id_usuario,new ArrayList<String>());
			f_out.writeObject(m);	
			/////////////////////////////
			waitConected.acquire();// el proceso cliente se detiene hasta que le despierta el oyente cliente al recibir confirmación conexión
			////////////////////////////
			System.out.println("Bienvenido, "+this.id_usuario);
			
			int option;
			do{
				displayMenu();
				option = sc.nextInt();
				//Envia mensaje a server
				Mensaje msg = procesaOpcion(option,id_usuario,ip_client,ip_host);
				f_out.writeObject(msg);
			}while(option!=0);
			sc.close();
		
		}catch(Exception e) {
			System.out.println("Mal funcionamiento en Cliente");
			e.printStackTrace();
			return;
		
		}	
		
	}
	
		
	private void displayMenu() {
		System.out.println("Menú:");
		System.out.println("==========");
		System.out.println("1 -consultar lista usuarios");
		System.out.println("2 -pedir fichero");
		System.out.println("3-añadir fichero");
		System.out.println("0 -salir");
		System.out.println("Introduzca el numero de opcion: ");		
	}
	
	
	private Mensaje procesaOpcion(int op,String nombre,String ip_client,String ip_host) {
		
		Mensaje m = null;
		
		switch(op) {			
			case 1:{//enviar MENSAJE_LISTA_USARIOS
				m = new MsgListaUsuarios(nombre,ip_client,ip_host);
				break;
			}
			case 2:{//enviar MENSAJE_PEDIR_FICHERO
				try {
					System.out.println("Introduce nombre fichero:");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String filename = br.readLine();
					m = new MsgPedirFichero(nombre,ip_client, ip_host, filename);
				} catch (IOException e) {e.printStackTrace();}				
				break;
			}
			case 3:{
				try {
					System.out.println("Introduce nombre fichero a añadir:");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String filename;
					filename = br.readLine();
					m= new MsgAñadirArchivo(ip_client, ip_host, filename, id_usuario);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			}
			case 0:{//enviar MENSAJE_CERRAR_CONEXION
				m = new MsgCerrarConexion(ip_client,ip_host,nombre);
				break;
			}
		}
		
		return m;		
	}
	
	
	
	public void addObserver(OSobserver o) {
		this.OS.addObserver(o);
		
	}
	

	
	public String getIP_HOST() {
		return this.ip_host;
	}
	public void sendMensaje(Mensaje m) {
		try {
			f_out.writeObject(m);
		} catch (IOException e) {
			System.out.println("No se pudo enviar el mensaje");
			e.printStackTrace();
		}
	}
	
	public void changeUserName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca otro id, el que ha elegido esta repetido: ");
		String nombre = sc.nextLine();
		this.id_usuario = nombre;
		
	}
	
	
	
	public String getIP() {
		return ip_client;
	}
	
	
	
	public String get_idUsuario() {
		return this.id_usuario;
	}
	
	public void setIdUsuario(String id_user) {
		this.id_usuario= id_user;
	}

	
	

}
