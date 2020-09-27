// OSCAR MORUJO FERNANDEZ
package parte2;

public class LockBakery extends Lock{

	 // el valor de turno crece siempre si siempre hay almenos un proceso intentando entrar el la CS (si no empieza de 0 la hacer release)
	private volatile int turn []; //turno de cada proceso
	private int N;
	
	public LockBakery(int N) {
		this.N = N;
		this.turn = new int[this.N];
		for(int i = 0; i < this.N; i++)
			this.turn[i]= 1;
		this.turn = this.turn;
	}
	
	@Override
	void releaseLock(int id) {
		this.turn[id] = 0;
		this.turn = this.turn;
	}

	@Override
	void takeLock(int id) {
		int max = 1;
		for(int i = 0; i < this.N; i++) {
			max = Math.max(max, this.turn[i]);
		}
		
		
		this.turn[id] = max + 1;
		this.turn =  this.turn;
		
		for(int j = 0; j < this.N; j++) {
			if(id != j) {
				while(this.turn[j] != 0 && ((turn[id] > turn[j]) || (turn[id] == turn[j] && id > j)));
			}
		}
		
	}

}
