// ÓSCAR MORUJO FERNÁNDEZ
package parte3;


public class Productor extends Thread {
	
	private Shared v_compartida;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
