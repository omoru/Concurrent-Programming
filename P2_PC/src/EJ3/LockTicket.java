package EJ3;


public class LockTicket{
	
	private Auxiliar _a;
	
	public LockTicket(Auxiliar a){
		_a = a;
	}
	
	void takeLock(int id) {
		_a.turn[id] = _a.number.getAndAdd(1);
		while(_a.turn[id] != _a.next) {
			;
		}
			
	}
	
	void releaseLock() {
		_a.next++;
	}
	
	

}
