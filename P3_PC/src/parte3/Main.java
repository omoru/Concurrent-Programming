// ÓSCAR MORUJO FERNÁNDEZ
package parte3;

public class Main {
	
	private static int NC = 10;
	private static int NP = 3;
	private static int tam_buffer = 5;
	
	public static void main(String args[]) {
		
		
		Productor[] productores = new Productor[NP];
		Consumidor[] consumidores = new Consumidor[NC];
		Shared v= new Shared(tam_buffer);
		
		for(int i=0; i < NC;i++) {
			consumidores[i]= new Consumidor(v,i);
			
		}
		
		for(int i=0; i < NP;i++) {
			productores[i]= new Productor(v,i);
		}
		
		for(int i = 0; i < NP;i++)
			productores[i].start();
		for (int i = 0; i < NC; i++)
			consumidores[i].start();
		
		
		
	}
}
