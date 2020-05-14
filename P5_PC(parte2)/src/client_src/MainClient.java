package client_src;
import java.net.Socket;
import java.util.Scanner;



public class MainClient {

	public static void main(String[] args) {
		
		if(args.length!=4) {
			System.out.println("Usage: args[0]->ip server, args[1]->puerto server, args[2]-> ip cliente, args[3]-> puerto cliente");
			System.out.println("Es necesaria la IP del host y puerto");
			return;
		}
		
		try {
			String ip_host = args[0];//ip server
			int PUERTO=Integer.parseInt(args[1]);
			String ip_client = args[2];//ip cliente
			int puerto_propio=Integer.parseInt(args[3]);
			String nombre;		
			//El usuario nos proporciona el nombre 
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca su nombre porfavor ");
			nombre = sc.nextLine();
					
			//Creamos el canal de comunicación con ip_host por el puerto correspondiente
			
				
			Client client = new Client(PUERTO,ip_client,puerto_propio,ip_host,nombre);
			client.start();		
			
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
			
	}



}
