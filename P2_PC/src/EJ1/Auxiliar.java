// OSCAR MORUJO FERNANDEZ
package EJ1;

public class Auxiliar {

	private volatile int a;
	public int N;
	
	
	public Auxiliar(int  n) {
		N=n;
		a = 0;
	}
	
	public void increase() {
		a++;
	}
	
	public void decrease() {
		a--;
	}
	
	public String toString() {
		return Integer.toString(a);
	}

}