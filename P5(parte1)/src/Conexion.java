

import java.io.*;
import java.net.*;

public class Conexion extends Thread{
	
	Socket socket;
	
	public Conexion(Socket socket) {
		this.socket= socket;
	}
	
	public void run() {
		
			try {			
				//canales para comunicarse con los clientes
				
				BufferedReader inS = new BufferedReader(new InputStreamReader(socket.getInputStream())); //client to server
				PrintWriter outS = new PrintWriter(socket.getOutputStream(),true); // server to client
				
				//Esperamos a que el cliente nos indique el archivo que desea
				String filename = inS.readLine();
				File file = new File(filename);
				if(file.exists()) {
					BufferedReader input_file = new BufferedReader(new FileReader(file)); //para leer el archivo
					String linea = input_file.readLine();
					while(linea != null) {
						outS.println(linea);
						linea = input_file.readLine();
					}
					input_file.close();
				}
				else {
					outS.println("ErrorNoEArchivo");
				}
				
				//cerramos canales y la conexion
				System.out.println("Cerrando conexion con cliente");
				outS.close();
				inS.close();
				socket.close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Algo ha fallado en la conexión");
				e.printStackTrace();
			} 
			
	}
	
	
}
