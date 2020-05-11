package client_src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread{
	
	private Socket socket;
	private String filename;
	
	public Emisor(Socket s,String filename) {
	
			this.socket=s;
			this.filename= filename;	
	}
	
	public void run() {
		
		try {			
			//canales para comunicarse con los clientes
			
			
			PrintWriter outS = new PrintWriter(socket.getOutputStream(),true); // server to client
			;
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
			outS.close();
			socket.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Algo ha fallado en la conexión");
			e.printStackTrace();
		} 
		
}


}
