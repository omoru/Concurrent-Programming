// OSCAR MORUJO FERNANDEZ
package EJ1;

public class HiloResta extends Thread {
	
	private Auxiliar _a;
	private Lock lock;
	
	public HiloResta (Auxiliar a,Lock lock) {
		_a = a;
		this.lock=lock;
	}
	
	public void run() {
		int i=0;
		while(i < _a.N) {
			lock.setBoolResta(true);
			lock.set_last(1);
			while(lock.isAdding() && lock.get_last() == 1) {
				;
			}
			//sc
			_a.decrease();
			lock.setBoolResta(false);
			//noSc
			i++;
			
		}
	}
}
