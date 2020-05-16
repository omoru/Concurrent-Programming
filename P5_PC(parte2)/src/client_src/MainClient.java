package client_src;

import java.util.Scanner;



public class MainClient {

	public static void main(String[] args) {
		
		if(args.length!=5) {
			System.out.println("Usage: args[0]->ip server, args[1]->puerto server, args[2]-> ip cliente, args[3]-> puerto cliente args[4]-> GUI o BATCH");
			System.out.println("Ejemplo: 192.168.0.21 555 192.168.0.21 588");
			return;
		}
		
		try {
			
			String ip_host = args[0];//ip server
			int PUERTO=Integer.parseInt(args[1]);
			String ip_client = args[2];//ip cliente
			int puerto_propio=Integer.parseInt(args[3]);
			String mode = args[4];
							
			Client client = new Client(PUERTO,ip_client,puerto_propio,ip_host);
			client.start();		
			
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
			
	}



}
