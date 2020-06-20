// ÓSCAR MORUJO FERNÁNDEZ
package parte2;
public class HiloResta extends Thread {
	
	private Shared shared_class;
	private int N;
	
	public HiloResta (Shared s,int N) {
		this.shared_class = s;
		this.N=N;
	}
	
	public void run() {
		for(int i=0; i < N;i++)
			shared_class.decrement();
	}
}
