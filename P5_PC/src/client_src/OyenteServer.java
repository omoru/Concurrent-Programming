package client_src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client_src.Client;
import msg_src.Mensaje;
import msg_src.MsgPreparadoCS;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class OyenteServer extends Thread{
	
	private Socket socket;
	private ObjectInputStream f_in; // flujo entrada a cliente
	private ObjectOutputStream f_out; // flujo entrada a cliente
	
	public OyenteServer(Socket s) {
		try {
			this.socket = s;
			this.f_in = new  ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("PROBLEMA EN LA CREACION DE OYENTESERVER");
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		while(true) {
			Mensaje m;		
				try {
					 m = (Mensaje)f_in.readObject();
					 
					switch(m.getMensaje()) {
						case "MENSAJE_CONFIRMACION_CONEXION":{
							System.out.println("Conexion realizada con server");
							System.out.println("info: ip origen :" + m.getIPOrigen() +" ip_dest: "+ m.getIPDestino());
							break;
						}
						case "MENSAJE_CONFIRMACION_LISTA_USARIOS":{
							System.out.println("Se ha recibido la información de los usuarios");
							printInfoUsuarios(m.getInfo_usuarios());
							break;
						}
						case "MENSAJE_EMITIR_FICHERO":{
							Mensaje mm = new MsgPreparadoCS(m.getIdUsuario(),Client.getIP(),Client.getPuertoPropio());
							Client.sendMensaje(mm);
							ServerSocket s= new ServerSocket(Client.getPuertoPropio());
							new Emisor(s.accept(),m.getFilename()).start();
							break;
						}
						case "MENSAJE_PREPARADO_SERVIDORCLIENTE":{
							String ip_dest= m.getMyIP();
							int  puerto_dest=m.getPuertoPropio();
							new Receptor(ip_dest,puerto_dest).start();
							break;
						}
						case "MENSAJE_CONFIRMACION_CERRAR_CONEXION":{
							System.out.println("Adios "+ m.getIdUsuario());
							socket.close();
							f_in.close();
							return;
							
						}
						default:
	                        System.out.println("Mensaje no identificado");break;
				}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		}
		
	}
	
	
	
	private void printInfoUsuarios(ArrayList<Usuario> usuarios) {
		System.out.println("TABLA INFORMACION USUARIOS");
		for(Usuario u: usuarios) {
			System.out.println("ID: " + u.getIdUsuario());
			System.out.println("Ficheros: " + u.getFicheros() +'\n');
		}
		
	}


}
