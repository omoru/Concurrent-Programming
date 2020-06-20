package EJ4;

public class LockBakery {
	
	private Auxiliar _a;
	
	public LockBakery(Auxiliar a){
		_a = a;
	}

	void takeLock(int id) {
		_a.turn[id] = 1;
		_a.turn[id] = _a.maxTurn() + 1;
		
		for(int i = 0; i < _a.N; i++) {
			if(i != id) {
				while(_a.turn[i] != 0 && turnoMayor(id, i))
					;
			}
		}
	}
	
	void releaseLock(int id) {
		_a.turn[id] = 0;	
	}
	
	private boolean turnoMayor(int id, int i) {
		return (_a.turn[id] > _a.turn[i] || 
				(_a.turn[id] == _a.turn[i] && id > i) );
	}

}
