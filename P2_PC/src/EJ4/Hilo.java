package EJ4;

public class Hilo extends Thread{
	
	private Auxiliar _a;
	private LockBakery _mtx;
	private int _id;
	
	public Hilo(Auxiliar a, LockBakery mtx, int id){
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
