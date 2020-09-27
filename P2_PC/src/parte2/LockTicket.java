// OSCAR MORUJO FERNANDEZ
package parte2;

import java.util.concurrent.atomic.AtomicInteger;



public class LockTicket extends Lock{
	
	private volatile AtomicInteger number;
	private volatile int turnos[];
	private volatile int next;
	private int N;
	
	public LockTicket(int n){
		this.N=n;
		this.turnos = new int[N];
		this.next=1;
		for(int i=0; i < N;i++) {
			turnos[i]=0;
		}
		this.number = new AtomicInteger(1);
	}
	
	void takeLock(int id) {
		turnos[id] = this.number.getAndAdd(1);
		while(turnos[id] != next) {
			;
		}
			
	}

	@Override
	void releaseLock(int id) {
		this.next++;
		
	}
	
	

}
