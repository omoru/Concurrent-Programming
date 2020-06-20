package EJ2;

public class LockRompeEmpate{
	
	private Auxiliar _a;
	
	public LockRompeEmpate(Auxiliar a){
		_a = a;
	}

	void takeLock(int id) {
		for(int j = 0; j < _a.N; j++) {
			//proceso i en etapa j
			_a.in[id] = j;
			_a.in = _a.in; 
			//proceso i ultimo de la etapa j
			_a.last[j] = id;
			_a.last = _a.last;
			
			for(int k = 0; k < _a.N; k++) {
				if(k != id) {
					while(_a.in[k] >= _a.in[id] && _a.last[j] == id)
						;
				}
				
			}
			
		}
	}
	
	void releaseLock(int id) {
		_a.in[id] = -1;
		_a.in = _a.in;
	}
	
}
