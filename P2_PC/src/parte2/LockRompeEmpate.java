// OSCAR MORUJO FERNANDEZ
package parte2;


//Livelock free,avoid innecesary delays and ensures eventual entry
public class LockRompeEmpate extends Lock{
	private int N; // 2*M
	private volatile int in[];//array que contiene el state de cada proceso i-esimo
	private volatile int last[];//el ultimo en llegar a la etapa i-esima
	
	
	public LockRompeEmpate(int n){
		N=n;
		in = new int[N];
		last = new int[N];
		for (int i = 0; i < N; i++){
			in[i] = -1;
			last[i] = -1;
		}
		this.last = this.last; 
		this.in = this.in;
	}
	
	@Override
	void takeLock(int id) {
		for(int j = 0; j < this.N; j++) {
			//proceso i en etapa j
			this.in[id] = j;
			this.in = this.in; 
			//proceso i ultimo de la etapa j
			this.last[j] = id;
			this.last = this.last;
			
			//aseguramos que un solo
			for(int k = 0; k < this.N; k++) {
				if(k != id) {
					//esperamos si hay otro proceso en una etapa >= que este
					//salimos cuando llega otro proceso a nuestra etapa y dejamos de ser el último o si no tenemos ninguno por una estapa mayor
					while(this.in[k] >= this.in[id] && this.last[j] == id)
						;
				}
				
			}
			
		}
		//System.out.println("El proceso "+ id +" ha entrado en la sc");
	}
	
	@Override
	void releaseLock(int id) {
		this.in[id] = -1;
		this.in = this.in;
	}

}
