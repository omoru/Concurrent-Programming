// ÓSCAR MORUJO FERNÁNDEZ
package parte2;
public class Consumidor extends Thread{

	private Shared v_compartida;
	private int id;
	
	public Consumidor(Shared v,int id) {
		this.v_compartida= v;
		this.id= id;
		
	}
	
	public void run() {
		
		while(true) {
			this.v_compartida.extraer();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
