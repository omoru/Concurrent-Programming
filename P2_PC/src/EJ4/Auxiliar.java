package EJ4;

public class Auxiliar {
	
	volatile public int a;
	public int turn[];
	public int N;
	
	public Auxiliar(int n) {
		a = 0;
		N = n;
		turn = new int[n];
		for (int i = 0; i < n; i++) {
			turn[i] = 0;
		}
	}
	
	public void increase() {
		a++;
	}
	
	public void decrease() {
		a--;
	}
	
	public int maxTurn(){
		int ret = 0;
		for(int i = 0; i < N; i++) {
			if(turn[i] > ret)
				ret = turn[i];
		}
		return ret;
	}
	
	public String toString() {
		return Integer.toString(a);
	}

}
