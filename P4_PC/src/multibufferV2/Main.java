// OSCAR MORUJO FERNANDEZ
package multibufferV2;


public class Main {
public static void main(String args[]) {
		
		final int NC = 10;
		final int NP = 10;
		final int tam_buffer = 15;
		
		Producer[] productores = new Producer[NP];
		Consumer[] consumidores = new Consumer[NC];
		MonitorMB_v2 v= new MonitorMB_v2(tam_buffer);
		
		for(int i=0; i < NP;i++) 
			productores[i]= new Producer(v,i,tam_buffer);
		
		for(int i=0; i < NC;i++) 
			consumidores[i]= new Consumer(v,i,tam_buffer);
		
		for(int i = 0; i < NP;i++)
			productores[i].start();
		
		for (int i = 0; i < NC; i++)
			consumidores[i].start();
		
		}
		
}
