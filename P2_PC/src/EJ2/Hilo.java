package EJ2;

public class Hilo extends Thread{
	
	private Auxiliar _a;
	private LockRompeEmpate _mtx;
	private int _id;
	
	public Hilo(Auxiliar a, LockRompeEmpate mtx, int id){
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
			_mtx.releaseLock(_id);
			
		}

}
