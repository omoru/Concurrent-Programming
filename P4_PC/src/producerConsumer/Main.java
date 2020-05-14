package producerConsumer;



public class Main {
	
	private static int NC = 4;
	private static int NP = 4;
	private static int tam_buffer = 6;
	public static void main(String args[]) {
		
		Producer[] productores = new Producer[NP];
		Consumer[] consumidores = new Consumer[NC];
		MonitorPC m= new MonitorPC(tam_buffer);
		
		for(int i=0; i < NC;i++) {
			consumidores[i]= new Consumer(m,i);
			
		}
		
		for(int i=0; i < NP;i++) {
			productores[i]= new Producer(m,i);
		}
		
		for(int i = 0; i < NP;i++)
			productores[i].start();
		for (int i = 0; i < NC; i++)
			consumidores[i].start();
	
	}

}
