// OSCAR MORUJO FERNANDEZ
package EJ1;

public class HiloSuma extends Thread {
	
	private Auxiliar _a;
	private Lock lock;
	
	
	
	public HiloSuma (Auxiliar a,Lock lock) {
		_a = a;
		this.lock=lock;
	}
	
	public void run() {
		int i=0;
		while(i < _a.N) {
			lock.setBoolSuma(true);
			lock.set_last(2);
			while(lock.isSubtracting() && lock.get_last() == 2) {
				;
			}
			//sc
			_a.increase();
			lock.setBoolSuma(false);
			//noSc
			i++;
			
		}
	}
}
