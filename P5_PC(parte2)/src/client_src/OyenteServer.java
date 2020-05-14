package client_src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client_src.Client;
import msg_src.Mensaje;
import msg_src.MsgConfirmListaUsuarios;
import msg_src.MsgConfirmacionCerrarConexion;
import msg_src.MsgEmitirFichero;
import msg_src.MsgPreparadoCS;
import msg_src.MsgPreparadoSC;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class OyenteServer extends Thread{
	
	private Socket socket;
	private ObjectInputStream f_in; // flujo entrada a cliente
	private Client client;
	public OyenteServer(Socket s,Client client) {
		try {
			this.client=client;
			this.socket = s;
			this.f_in = new ObjectInputStream(socket.getInputStream());
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
							break;
						}
						case "MENSAJE_CONFIRMACION_LISTA_USARIOS":{
							System.out.println("Se ha recibido la información de los usuarios");
							printInfoUsuarios(((MsgConfirmListaUsuarios) m).getInfo_usuarios());
							break;
						}
						case "MENSAJE_EMITIR_FICHERO":{
							Mensaje mm = new MsgPreparadoCS(((MsgEmitirFichero) m).getIdUsuario(),client.getIP(),client.getPuertoPropio());
							client.sendMensaje(mm);
							ServerSocket s= new ServerSocket(client.getPuertoPropio());
							new Emisor(s.accept(),((MsgEmitirFichero) m).getFilename()).start();
							break;
						}
						case "MENSAJE_PREPARADO_SERVIDORCLIENTE":{
							String ip_dest= ((MsgPreparadoSC) m).getMyIP();
							int  puerto_dest=((MsgPreparadoSC) m).getPuertoPropio();
							new Receptor(ip_dest,puerto_dest).start();
							break;
						}
						case "MENSAJE_CONFIRMACION_CERRAR_CONEXION":{
							System.out.println("Adios "+ ((MsgConfirmacionCerrarConexion) m).getIdUsuario());
							socket.close();
							f_in.close();
							return;
							
						}
						default:
	                        System.out.println("Mensaje no identificado");break;
					}
				}
				catch (Exception e) {
					System.out.println("Se ha cortado la conexion del server");
					e.printStackTrace();
					return;
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
