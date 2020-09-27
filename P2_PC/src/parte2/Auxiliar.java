// OSCAR MORUJO FERNANDEZ
package parte2;

public class Auxiliar {

	volatile private int a;

	public Auxiliar() {
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

