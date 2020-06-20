package parte3;

public class Shared {
	
	public int matrix_1[][];
	public int matrix_2[][];
	public int result[][];
	public int MAX;
	
	public Shared(int matrix_1[][],int matrix_2[][],int N) {
		this.MAX = N;
		this.matrix_1 = matrix_1;
		this.matrix_2 = matrix_2;
		this.result = new int[MAX][MAX];
		
	}
	
	

}
