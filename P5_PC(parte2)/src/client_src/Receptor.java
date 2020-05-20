package client_src;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;




public class Receptor extends Thread {
	
	private String mode;
	private String ip;
	private int puerto;
	private ArrayList<OSobserver> observers;
	private String file_extension;
	private String filename;
	
	public Receptor(ArrayList<OSobserver>  observers,String ip, int puerto,String mode, String filename) {
		this.puerto=puerto;
		this.ip = ip;
		this.observers=observers;
		this.mode=mode;
		this.filename=filename;
		String [] parts = new String[2];
		parts = this.filename.split("\\.");
		String extension = parts[1];
		this.file_extension= "."+ extension;
	}
	
	public void run() {
		try {

				//Creamos el canal de comunicación con ip_host por el puerto correspondiente
				System.out.println("El emisor esta esperando en el puerto "+ puerto);
				Socket socket = new Socket(ip,puerto);
				if(mode.equalsIgnoreCase("GUI"))descargaArchivoGUI(socket);
				else descargaArchivoBatch(socket);
				socket.close();
			
			} catch (Exception e) {
				System.out.println("Mal funcionamiento en Cliente");
				e.printStackTrace();

			}
	}
	
	
	
	private void descargaArchivoGUI(Socket socket) throws IOException{
		
		String filename = JOptionPane.showInputDialog("Introduzca como quiere guardar el archivo(sin la extension de archivo):");
		while(filename.length()==0) {
			filename = JOptionPane.showInputDialog("El nombre no puede estar vacio");
		}
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		FileOutputStream fos = new FileOutputStream(filename + this.file_extension);
		int count;
	    byte[] bytes = new byte[16 * 1024];
	    long  totalbytes=0;
	    while((count = dis.read(bytes)) > 0) {
	    	fos.write(bytes, 0, count);
	    	totalbytes+=count;
	    	for(int i=0; i <observers.size();i++)observers.get(i).onDownloading(filename,totalbytes/1000);
		}
	    for(int i=0; i <observers.size();i++)observers.get(i).onDownloading(filename,totalbytes/1000);
	    for(int i=0; i <observers.size();i++)observers.get(i).onFileDownloaded(filename + this.file_extension,totalbytes/1000);
	    
		fos.close();
        dis.close();
			
		
	}
	private void descargaArchivoBatch(Socket socket) throws Exception {
			
			System.out.println("Introduce como quieres guardar el archivo(sin la extension de archivo):");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String filename = br.readLine();
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			FileOutputStream fos = new FileOutputStream(filename + this.file_extension);
			System.out.println("RECIBIENDO");
			int count;
		    byte[] bytes = new byte[16 * 1024];
		    long  totalbytes=0;
		    long temp=0;
		    while((count = dis.read(bytes)) > 0) {
		    	fos.write(bytes, 0, count);
		    	totalbytes+=count;
		    	if(temp < totalbytes) {
		    		System.out.println("Se han descargado "+ totalbytes/1000+" bytes");
		    		temp = totalbytes * 2;
		    	}
		    		    	
			}
		    System.out.println("Se han descargado "+ totalbytes/1000+" KBytes");
			System.out.println("Archivo solicitado se ha descargado");
			fos.close();
	        dis.close();
		
	}

}
