package died.izaguirre.haulet.tp.estructuras.matriz;


import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Matriz implements Cloneable {

	private int[][] valores;
	private boolean[][] booleana;

	public Matriz(int n) {
		if(n > 0) {
			valores = new int[n][n];
			booleana = new boolean[n][n];
		}
		else throw new MatrizException("No se puede crear una matriz nula");
	}
	
	public Matriz(int filas, int columnas) {
		if(filas > 0 && columnas > 0) { 
			valores = new int[filas][columnas];
			booleana = new boolean[filas][columnas];
		}
		else throw new MatrizException("No se puede crear una matriz nula");
	}

	public Matriz(int[][] mat) {
		if(mat.length > 0) { 
			valores = mat;
			booleana = new boolean[mat.length][mat[0].length];
			obtenerMatrizBooleana();
		}
		else throw new MatrizException("No se puede crear una matriz nula");
	}

	public int getValor(int fila, int columna)
	{
		if(fila >= valores[0].length || columna >= valores.length ) 
			throw new MatrizException("Posición fuera de rango");	
		return valores[fila][columna];
	}
		
	public boolean getValorBooleano(int fila, int columna)
	{
		return booleana[fila][columna];
	}
	
	public int getFilas() 
	{
		return valores.length;
	}
	
	public int getColumnas() 
	{
		return valores[0].length;
	}
	
	public Matriz multiplicar(Matriz matA, Matriz matB) {
		
		if(matA.getColumnas() != matB.getFilas()) 
			throw new MatrizException("Las matrices no son multiplicables");

		Matriz resultante = new Matriz(matA.getFilas(), matB.getColumnas());
		
		int valor = 0;

		for (int i = 0; i < resultante.getFilas(); i++) 
		{
			for (int j = 0; j < resultante.getColumnas(); j++) 
			{
				for (int k = 0; k < resultante.getFilas(); k++) 
					valor += matA.getValor(i, k) * matB.getValor(k, j);
				resultante.insertarValor(valor, i, j);
				valor = 0;
			}
		}
		return resultante;
											
	}				
	
	public void insertarValor(int valor, int fila, int columna)
	{
		if(valores.length == 0 || fila >= valores.length || columna >= valores[0].length ) 
			throw new MatrizException("Posición fuera de rango");
		else valores[fila][columna] = valor;
		booleana[fila][columna] = valor > 0 ? true : false;
	}
	
	public void insertarValor(boolean valor, int fila, int columna)
	{
		if(valores.length == 0 || fila >= valores.length || columna >= valores[0].length ) 
			throw new MatrizException("Posición fuera de rango");
		else booleana[fila][columna] = valor;
	}
			
	
	//Toma una funcion con 2 argumentos (i, j -> fila, columna) y cambia el valor en esa posicion
	public void modificarMatriz(BiFunction<Integer, Integer,Integer> funcion) 
	{
		for(int i = 0; i < valores.length; i++) 
		{
			for(int j = 0; j < valores[0].length; j++) 
			{
				insertarValor(funcion.apply(i, j), i, j);				
			}
		}
	}
	
	//Toma un valor (recorre por columnas) de la matriz valores y booleana y realiza una operacion void
	public void recorrerMatriz(BiConsumer<Integer, Boolean> funcion) 
	{
		for(int i = 0; i < valores.length; i++) 
		{
			for(int j = 0; j< valores[0].length; j++) 
			{					
				funcion.accept(valores[i][j], booleana[i][j]);		
			}
		}
	}
		
	private void obtenerMatrizBooleana() 
	{
		for(int i = 0; i < valores.length; i++) 
		{
			for(int j = 0; j< valores[0].length; j++) 
			{
				booleana[i][j] = valores[i][j] > 0 ? true : false;
				
			}
		}
	}
	
	public Matriz clonar() 
	{
		return new Matriz(this.valores);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return clonar();
	}
	
}

