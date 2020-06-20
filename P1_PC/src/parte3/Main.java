package parte3;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		//INICIALIZACION MATRICES
		//-------------------------------------------------------------------
		System.out.println("Introduce el numero de filas de la matriz cuadrada:");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int column = row;

		int[][] first = new int[row][column];
		int[][] second = new int[row][column];

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				System.out.println(String.format("Enter first[%d][%d] integer", r, c));
				first[r][c] = sc.nextInt();
			}
		}

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				System.out.println(String.format("Enter second[%d][%d] integer", r, c));
				second[r][c] = sc.nextInt();
			}
		}

		// close the scanner
		sc.close();
		//---------------------------------------------------------------------------
		
		// print both matrices
		System.out.println("First Matrix:\n");
		print2dArray(first);

		System.out.println("Second Matrix:\n");
		print2dArray(second);
		
		//CADA PROCESO CALCULA UNA FILA DE LA MATRIZ RESULTADO
		Shared s = new Shared(first,second,row);
		MyThread[] threads = new MyThread[row];
	
		for(int fila = 0; fila < row;fila++) {
			threads[fila]= new MyThread(fila,s);
		}
		
		for(int j=0; j < row;j++) {
			threads[j].start();
		}
		
		for(int j=0; j < row;j++) {
			try {
				threads[j].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Result:\n");
		print2dArray(s.result);
		

	}
	private static void print2dArray(int[][] matrix) {
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				System.out.print(matrix[r][c] + "\t");
			}
			System.out.println();
		}
	}
	
}
