package EJ1;

public class HiloResta extends Thread {
	
	private Auxiliar _a;
	
	
	public HiloResta (Auxiliar a) {
		_a = a;
	}
	
	public void run() {
		int i=0;
		while(i < _a.N) {
			_a.boolresta=true;
			_a.last = 2;
			while(_a.boolsuma && _a.last == 1) {
				;
			}
			//sc
			_a.decrease();
			_a.boolresta=false;
			i++;
			//noSc
		}
	}
}
