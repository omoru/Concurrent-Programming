// OSCAR MORUJO FERNANDEZ
package parte2;

public class HiloResta extends Thread {
	
	private int N;
	private Auxiliar v_compartida;
	private int id_proceso;
	private Lock lock;
	
	public HiloResta(int N,Auxiliar a, int id,Lock lock) {
		this.N=N;
		this.v_compartida=a;
		this.id_proceso=id;
		this.lock=lock;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) {
			lock.takeLock(this.id_proceso);
			this.v_compartida.decrease();
			lock.releaseLock(this.id_proceso);
		}
	}
}
