package client_src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Receptor extends Thread {
	
	private Socket socket;
	private String ip;
	private int puerto;
	public Receptor(String ip, int puerto) {
		this.puerto=puerto;
		this.ip = ip;
	}
	
	public void run() {
		try {

				//Creamos el canal de comunicación con ip_host por el puerto correspondiente
				Socket socket = new Socket(ip,puerto);
				//abrimos los canales de comunicación para los dos flujos( uno para escribir strings y otro para leerlos)
				BufferedReader inC = new BufferedReader(new InputStreamReader(socket.getInputStream())); //server to client flow
	
				//El usuario nos proporciona el nombre del fichero a descargar
				System.out.println("RECIBIENDO");
				
				String linea = inC.readLine(); 
				PrintWriter downloaded = new PrintWriter("download.txt", "UTF-8");
				while(linea != null) {
					downloaded.println(linea);
					linea = inC.readLine();
				}
				System.out.println("Archivo solicitado se ha descargado");
				downloaded.close();
				inC.close();
				socket.close();
			
			} catch (Exception e) {
				System.out.println("Mal funcionamiento en Cliente");
				e.printStackTrace();

			}
	}

}
