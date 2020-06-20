package multibufferV1;


public class Producer extends Thread {
	private MonitorMB m;
	private int id;
	private int tam;
	
	
	public Producer(MonitorMB m, int id,int tam) {
		this.m=m;
		this.id=id;
		this.tam=tam;
	}
	
	public void run() {

		while(true) {
			int numero = (int) (Math.random() * (this.tam/2)) + 1;
			
			Producto[] p = new Producto[numero];
			for(int i=0; i < numero;i++) {
				p[i]= new Producto(this.id);
			}
			this.m.almacenar(p);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
