package client_src;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import gui.ClientMainWindow;



public class MainClient {

	public static void main(String[] args) {
		
		
		start(args);
		
			
	}
	
	private static void start(String[] args) {
		if(args.length!=4) {
			System.out.println("Usage: args[0]->ip server, args[1]->puerto server, args[2]-> ip cliente,  args[3]-> GUI o BATCH");
			System.out.println("Ejemplo: 192.168.0.21 555 192.168.0.21 GUI");
			return;
		}
		String mode = args[3];
		if(mode.equalsIgnoreCase("GUI")) {
			startGUImode(args);
		}
		else {
			startBatchMode(args);			
		}
		
	}
	
	
	private static void startBatchMode(String[] args) {
		String mode = args[3];
		String ip_host = args[0];//ip server
		int PUERTO=Integer.parseInt(args[1]);
		String ip_client = args[2];//ip cliente
		Client client = new Client(PUERTO,ip_client,ip_host,mode);
		client.start();	
	}
	
	private static void startGUImode(String[] args) {
		try {
			String mode = args[4];
			String ip_host = args[0];//ip server
			int PUERTO=Integer.parseInt(args[1]);
			String ip_client = args[2];//ip cliente
			int puerto_propio=Integer.parseInt(args[3]);
			Client client = new Client(PUERTO,ip_client,puerto_propio,ip_host,mode);
			client.start();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}



}
