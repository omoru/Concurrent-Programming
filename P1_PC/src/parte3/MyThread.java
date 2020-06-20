package parte3;

public class MyThread extends Thread{
	
	private Shared s;
	private int fila;

	
	public MyThread(int fila,Shared s) {
		this.s = s;
		this.fila=fila;
		
	}
	
	public void run() {
	
		for(int calculados=0; calculados <s.MAX;calculados++) {
			int acum=0;
			for(int i=0; i < s.MAX;i++) {
				acum+=(s.matrix_1[fila][i]*s.matrix_2[i][calculados]);	
			}
			s.result[fila][calculados] = acum;
		}
	}
	
	

}
