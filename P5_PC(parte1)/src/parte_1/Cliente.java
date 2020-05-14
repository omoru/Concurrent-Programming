package parte_1;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	
	
	public static void main(String[] args) {
		
		int puerto = 555;	
		
		try {
			
			String op;
			InetAddress ip_host = InetAddress.getLocalHost();
			//////////
			do {
				//Creamos el canal de comunicación con ip_host por el puerto correspondiente
				Socket socket = new Socket(ip_host,puerto);
				//abrimos los canales de comunicación para los dos flujos( uno para escribir strings y otro para leerlos)
				BufferedReader inC = new BufferedReader(new InputStreamReader(socket.getInputStream())); //server to client flow
				PrintWriter outC = new PrintWriter(socket.getOutputStream(),true); // client to server flow
				
				//El usuario nos proporciona el nombre del fichero a descargar
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Introduzca el nombre del archivo que desea descargar: ");
				String filename  = br.readLine();
				
				outC.println(filename); 
				/*linea puede ser tanto el string que indica que no existe el archivo
				 * como una linea del fichero a descargar. */
				String linea = inC.readLine(); 
				
				if(!linea.equals("ErrorNoEArchivo")) {
					PrintWriter downloaded = new PrintWriter("Descargas/download.txt", "UTF-8");
					while(linea != null) {
						downloaded.println(linea);
						linea = inC.readLine();
					}
					downloaded.close();
				}
				else System.out.println("El archivo " + filename +" no existe.");
				
				outC.close();
				inC.close();
				socket.close();
				
				System.out.println("¿Desea descargar otro archivo? ( y / n ): ");
				op = br.readLine();
				
			}while(op.equalsIgnoreCase("y"));
			
			} catch (Exception e) {
				System.out.println("Mal funcionamiento en Cliente");
				e.printStackTrace();

			}
	}
	
	

}
