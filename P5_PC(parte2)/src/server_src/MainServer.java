package server_src;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {

	
	public static void main(String[] args) {
	
			if(args.length!=2) {
				System.out.println("Arguments: ip_server server_port");
				System.out.println("Example: 192.169.0.21 555");
				return;
			}
			String ip_server =args[0];
			int PUERTO = Integer.parseInt(args[1]);
			MonitorServer monitor = new MonitorServer();
			//Iniciamos el server
			
			new Server(PUERTO,ip_server,monitor).start();
	}
}
