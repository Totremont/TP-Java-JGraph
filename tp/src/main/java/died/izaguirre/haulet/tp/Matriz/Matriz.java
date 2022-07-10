package died.izaguirre.haulet.tp.Matriz;

public class Matriz {

	private int[][] valores; // Indican si hay camino, o el peso de los mismos (los pesos son valores
								// enteros).

	public Matriz() {
	};

	public Matriz(int n) {
		this();
		valores = new int[n][n];
	}

	public Matriz(int[][] mat) {
		valores = mat;
	}

	// No hago los metodos de como cargar datos hasta que definamos la clase de
	// grafo.

	public int[][] getValores() {
		return valores;
	}

	// Metodos básicos -- Deberian usarse como auxiliar en esta clase nomas
	public int[][] duplicarMatriz(int[][] mat) {

		int[][] resultante = new int[mat.length][mat.length];

		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat.length; j++)
				resultante[i][j] = mat[i][j];

		return resultante;

	}

	public int[][] multiplicar(int[][] matA, int[][] matB) {

		int[][] resultante = new int[matA.length][matB[0].length];

		if (matA[0].length == matB.length) {
			for (int i = 0; i < matA.length; i++) {
				for (int j = 0; j < matB[0].length; j++) {
					for (int k = 0; k < matA[0].length; k++) {
						resultante[i][j] += matA[i][k] * matB[k][j];
					}
				}
			}
		}

		return resultante;

	}

	public int[][] potencia(int[][] mat, int n) {

		if (n <= 0)
			return mat;
		else if (n == 1)
			return mat;
		else
			return multiplicar(mat, potencia(mat, n - 1));

	}
	
	public int[][] potencia(int n){
		return this.potencia(valores, n);
	}
	
	// Transforma una matriz de enteros a una booleana, util para la transitividad
	public boolean[][] matrizBooleana(int[][] mat){
		
		boolean[][] resultante = new boolean[mat.length][mat.length];
		
		for(int i = 0 ; i < mat.length ; i++)
			for(int j = 0 ; j < mat.length ; j ++)
				resultante[i][j] = mat[i][j] != 0 ? true : false;
		
		return resultante;
		
	}
	
	public boolean[][] matrizBooleana(){
		return this.matrizBooleana(valores);
	}
	
	public void insertar(int value, int x, int y){
		if(x < valores.length & y < valores.length)
			valores[x][y] = value;		
	}
	
	// Algoritmo de warshall
	
	public boolean[][] warshall(boolean[][] mat){
		
		boolean[][] resultante = mat;
		
		for(int k = 0 ; k < mat.length ; k++) // k para recorrer por la diagonal principal
			for(int i = 0; i < mat.length ; i++) // i recorre horizontalmente a la altura de k
					for(int j = 0 ; j < mat.length ; j++)
						resultante[i][j] = (mat[i][j] || (mat[i][k] && mat[k][j])); 
		
		return resultante;
		
	}
	
	public boolean[][] warshall(int[][] mat){
		return this.warshall(this.matrizBooleana(mat));
	}
	
	public boolean[][] warshall(){
		return this.warshall(valores);
	}
	
}

