package parte_1;

import java.io.*;
import java.net.*;

public class Server{
	
	public static void main(String[] args) {
		int puerto = 555;
		// TODO Auto-generated method stub
		try {
			ServerSocket s = new ServerSocket(puerto);
			while(true) {
				System.out.println("Esperando nuevas conexiones...");
				Conexion cnx = new Conexion(s.accept()); // detenemos al servidor hasta que llega un cliente
				cnx.start();				
				System.out.println("Se ha establecido una nueva conexión con el cliente");
			}
			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema en Server");
			e.printStackTrace();
		}
		
	}
	
}
