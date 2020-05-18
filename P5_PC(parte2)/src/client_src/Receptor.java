package client_src;


import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;


public class Receptor extends Thread {
	

	private String ip;
	private int puerto;
	private String id_usuario;
	private String filename;
	public Receptor(String ip, int puerto,String id_usuario,String filename) {
		this.puerto=puerto;
		this.ip = ip;
		this.id_usuario=id_usuario;
		this.filename=filename;
	}
	
	public void run() {
		try {

				//Creamos el canal de comunicación con ip_host por el puerto correspondiente
				System.out.println("El emisor esta esperando en el puerto "+ puerto);
				Socket socket = new Socket(ip,puerto);
				descargaArchivo(socket);
				socket.close();
			
			} catch (Exception e) {
				System.out.println("Mal funcionamiento en Cliente");
				e.printStackTrace();

			}
	}
	
	private void descargaArchivo(Socket socket) {
		try {
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			FileOutputStream fos = new FileOutputStream("prueba\\"+filename);
			System.out.println("RECIBIENDO");
			int count;
		    byte[] bytes = new byte[16 * 1024];
		    long  totalbytes=0;
		    long temp=0;
		    while((count = dis.read(bytes)) > 0) {
		    	fos.write(bytes, 0, count);
		    	totalbytes+=count;
		    	if(temp < totalbytes) {
		    		System.out.println("Se han descargado "+ totalbytes+" bytes");
		    		temp = totalbytes * 4;
		    	}	    	
			}
		    System.out.println("Se han descargado "+ totalbytes+" bytes");
			
			System.out.println("Archivo solicitado se ha descargado");
			fos.close();
	        dis.close();
		}catch (Exception e) {
			System.out.println("Mal funcionamiento en Cliente");
			e.printStackTrace();

		}
	}

}
