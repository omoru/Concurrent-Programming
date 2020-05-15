package client_src;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	private String id_usuario;
	
	public Emisor(Socket s,String filename,String id_usuario) {
	
			this.socket=s;
			this.filename= filename;
			this.id_usuario=id_usuario;
	}
	
	public void run() {
		
		try {			
			//canales para comunicarse con los clientes
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	        FileInputStream fis = new FileInputStream(filename);
			
			int count;
			byte[] bytes = new byte[16 * 1024];
			while((count = fis.read(bytes)) > 0) {
				dos.write(bytes, 0, count);
			}
				
			
			
			//cerramos canales y la conexion
			fis.close();
			dos.close();
			socket.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Algo ha fallado en la conexión");
			e.printStackTrace();
		} 
		
}


}
