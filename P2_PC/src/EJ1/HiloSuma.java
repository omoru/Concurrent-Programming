package EJ1;

public class HiloSuma extends Thread {
	
	private Auxiliar _a;
	
	
	
	public HiloSuma (Auxiliar a) {
		_a = a;
	}
	
	public void run() {
		int i=0;
		while(i < _a.N) {
			_a.boolsuma=true;
			_a.last = 1;
			while(_a.boolresta && _a.last == 2) {
				;
			}
			//sc
			_a.increase();
			_a.boolsuma=false;
			i++;
			//noSc
		}
	}
}
