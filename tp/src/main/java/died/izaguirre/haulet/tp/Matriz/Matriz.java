package died.izaguirre.haulet.tp.Matriz;

public class Matriz {
	
	private int[][] valores; // Indican si hay camino, o el peso de los mismos (los pesos son valores enteros).
	
	public Matriz() {};
	
	public Matriz(int n) {
		this();
		valores = new int[n][n];		
	}
	
	// No hago los metodos de como cargar datos hasta que definamos la clase de grafo.
	
	// Metodos básicos
	private int[][] duplicarMatriz(int[][] mat) {
		
		int[][] resultante = new int[mat.length][mat.length];
		
		for(int i = 0 ; i < mat.length ; i++)
			for(int j = 0 ; j < mat.length ; j++)
				resultante[i][j] = mat[i][j];
		
		return resultante;
		
	}
	
	private int[][] multiplicar(int[][] matA, int[][] matB){
		
		int[][] resultante = new int[matA.length][matB[0].length];
		
		if (matA[0].length == matB.length) {
	        for (int i = 0; i < matA.length; i++) {
	            for (int j = 0; j < matB[0].length; j++) {
	                for (int k = 0; k < matA[0].length; k++) {
	                    // aquí se multiplica la matriz
	                    resultante[i][j] += matA[i][k] * matB[k][j];
	                }
	            }
	        }
	    }
		
		return resultante;
		
	}
	
	private int[][] potencia(int[][] mat, int n){
		
		if(n <= 0)
			return mat;
		else if(n == 1)
				return mat;
			else
				return multiplicar(mat, potencia(mat, n-1));
		
	}
	
	
	
}
