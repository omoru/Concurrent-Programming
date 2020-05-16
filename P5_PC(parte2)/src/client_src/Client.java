package client_src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import msg_src.Mensaje;
import msg_src.MsgCerrarConexion;
import msg_src.MsgConexion;
import msg_src.MsgListaUsuarios;
import msg_src.MsgPedirFichero;

public class Client extends Thread{
		
	private int puerto;//puerto de conexion al server
	private String ip_client;//ip del cliente
	private int puerto_propio;// puerto del cliente que se usa cuando va a actuar como server y transmitir datos a otro cliente
	private ObjectOutputStream f_out;
	private String ip_host;//ip server
	private String id_usuario;
	private ArrayList<String> ficheros;//ficheros que tiene el usuario
	private Semaphore waitConected = new Semaphore(0);
	
	public Client(int puerto, String ip_client, int puerto_propio, String ip_host) {
		this.puerto = puerto;
		this.ip_client = ip_client;
		this.puerto_propio = puerto_propio;
		this.ip_host= ip_host;
		this.ficheros = new ArrayList<String>();
		
	}
	
	public void run() {
		try {
			
			Socket socket = new Socket(ip_client,puerto);
			this.f_out= new ObjectOutputStream(socket.getOutputStream());
				
			//El usuario nos proporciona el nombre 
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca su nombre porfavor ");
			this.id_usuario = sc.nextLine();
			
			
			
			System.out.println("Introduce uno a uno los ficheros que tienes disponibles e introduce EXIT cuando hayas terminado:");
			
			String fichero= sc.nextLine();
			while(!fichero.equalsIgnoreCase("EXIT")) {
				File f = new File(fichero);
				if(f.exists()) {
					ficheros.add(fichero);
				}
				else
					System.out.println("El archivo no existe, introduzca otro o EXIT para salir");
				fichero=sc.nextLine();
				
			}
			
			new OyenteServer(socket,this).start();
			Mensaje m = new MsgConexion(ip_client,ip_host,id_usuario,ficheros);
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
			}while(option!=3);
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
		System.out.println("3 -salir");
		System.out.println("Introduzca la opcion deseada: ");		
	}
	
	private Mensaje procesaOpcion(int op,String nombre,String ip_client,String ip_host) {
		
		Mensaje m = null;
		
		switch(op) {
			
			case 1:{//enviar MENSAJE_LISTA_USARIOS
				m = new MsgListaUsuarios(nombre,ip_client,ip_host);
				break;
			}
			
			case 2:{//enviar MENSAJE_PEDIR_FICHERO 3
				
				try {
					System.out.println("Introduce nombre fichero:");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String filename = br.readLine();
					m = new MsgPedirFichero(nombre,ip_client, ip_host, filename);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			
			case 3:{//enviar MENSAJE_CERRAR_CONEXION
				m = new MsgCerrarConexion(ip_client,ip_host,nombre);
				break;
			}
		}
		
		return m;		
	}
	
	public Semaphore getSemaphore() {
		return this.waitConected;
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
	
	public int getPuertoPropio() {
		return this.puerto_propio;
	}
	
	public String get_idUsuario() {
		return this.id_usuario;
	}
	
	public void setIdUsuario(String id_user) {
		this.id_usuario= id_user;
	}
	
	public ArrayList<String> getFicheros(){
		return this.ficheros;
	}
	
	

}
