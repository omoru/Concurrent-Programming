package EJ3;

public class Hilo extends Thread{
	
	private Auxiliar _a;
	private LockTicket _mtx;
	private int _id;
	
	public Hilo(Auxiliar a, LockTicket mtx, int id){
		_a = a;
		_mtx = mtx;
		_id = id;
	}
	
	public void run() {
			
			_mtx.takeLock(_id);
			if(_id % 2 == 0)
				_a.increase();
			else
				_a.decrease();
			_mtx.releaseLock();
			
		}
}
