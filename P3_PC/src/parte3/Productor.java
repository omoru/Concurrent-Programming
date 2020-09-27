// OSCAR MORUJO FERNANDEZ
package parte3;


public class Productor extends Thread {
	
	private Shared v_compartida;
	@SuppressWarnings("unused")
	private int id;
	
	public Productor(Shared v,int id) {
		this.v_compartida = v;
		this.id= id;
		
	}
	
	
	public void run() {
		while(true) {
			v_compartida.almacenar(new Producto());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
