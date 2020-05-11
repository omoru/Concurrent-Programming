package client_src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import msg_src.Mensaje;
import msg_src.MsgCerrarConexion;
import msg_src.MsgConexion;
import msg_src.MsgListaUsuarios;
import msg_src.MsgPedirFichero;

public class Client {
		
		private static int PUERTO;//puerto de conexion al server
		private static String ip_client;//puerto conexiones entrantes
		private static int puerto_propio;// puerto del cliente que se usa cuando va a actuar como server y transmitir datos a otro cliente
		private static ObjectOutputStream f_out;
		
		public static void main(String[] args) {
			
			if(args.length!=4) {
				System.out.println("Usage: args[0]->ip server, args[1]->puerto server, args[2]-> ip cliente, args[3]-> puerto cliente");
				System.out.println("Es necesaria la IP del host y puerto");
				return;
			}
			
			String ip_host = args[0];//ip server
			PUERTO=Integer.parseInt(args[1]);
			ip_client = args[2];//ip cliente
			puerto_propio=Integer.parseInt(args[3]);
			String nombre;
			
			
					
			try {
				
					//El usuario nos proporciona el nombre 
					Scanner sc = new Scanner(System.in);
					System.out.println("Introduzca su nombre porfavor ");
					nombre = sc.nextLine();
					
					//Creamos el canal de comunicación con ip_host por el puerto correspondiente
					Socket socket = new Socket(ip_client,PUERTO);
					
					//Flujo de salida del cliente
					f_out= new ObjectOutputStream(socket.getOutputStream());
					
					//leyendo el socket			
					new OyenteServer(socket).start();
					
				
					System.out.println("Introduce uno a uno los ficheros que tienes disponibles e introduce XXX cuando hayas terminado:");
					ArrayList<String> ficheros = new ArrayList<String>();
					String fichero= sc.nextLine();
					while(!fichero.equalsIgnoreCase("XXX")) {
						ficheros.add(fichero);
						fichero=sc.nextLine();
					}
					
					//ENVIAR MENSAJE CONEXION A SERVER
					Mensaje m = new MsgConexion(ip_client,ip_host,nombre,ficheros);
					f_out.writeObject(m);				
					int option;
					
					do{
						Thread.sleep(500);
						displayMenu();
						option = sc.nextInt();
						//Envia mensaje a server
						Mensaje msg = procesaOpcion(option,nombre,ip_client,ip_host);
						f_out.writeObject(msg);
						
					}while(option!=3);
					
					
	
					
				
				} catch (Exception e) {
					System.out.println("Mal funcionamiento en Cliente");
					e.printStackTrace();
	
				}
	}

	public static String getIP() {
		return ip_client;
	}
	
	public static int getPuertoPropio() {
		return puerto_propio;
	}
	
	private static void displayMenu() {
		System.out.println("Menú:");
		System.out.println("==========");
		System.out.println("1 -consultar lista usuarios");
		System.out.println("2 -pedir fichero");
		System.out.println("3 -salir");
		System.out.println("Introduzca la opcion deseada: ");		
	}
	public static void sendMensaje(Mensaje m) {
		try {
			f_out.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Mensaje procesaOpcion(int op,String nombre,String ip_client,String ip_host) {
		
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
	

}
