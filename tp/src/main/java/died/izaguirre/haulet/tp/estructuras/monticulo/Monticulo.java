package died.izaguirre.haulet.tp.estructuras.monticulo;

import java.util.ArrayList;

public class Monticulo<T extends Comparable<T>> {
	
	private ArrayList<T> elementos = new ArrayList<>();
			
	public Monticulo(ArrayList<T> elementos) {
		super();
		for (T elemento : elementos) {
			this.insertar(elemento);
		}
	}

	public void insertar(T elemento) 
	{
		elementos.add(elemento);
		int pos = elementos.size() - 1;
		int padre = (pos - 1)/2;
		while(padre >= 0 && elementos.get(padre).compareTo(elemento) == -1) 
		{
			subirHijo(pos, padre);
			pos = padre;
			padre = (pos - 1)/2;
		}
		
	}
	
	private void subirHijo(int hijo, int padre) 
	{
		if(hijo >= elementos.size()) throw new ArrayIndexOutOfBoundsException();
		T aux = elementos.get(padre);
		elementos.set(padre, elementos.get(hijo));
		elementos.set(hijo, aux);		
	}
	
	
	public T quitarRaiz() 
	{
		if(elementos.isEmpty()) return null;
		else 
		{
			T raiz = elementos.get(0);
			int tam = elementos.size() - 1; 
			T ultimo = elementos.get(tam);
			elementos.set(0, ultimo);
			elementos.remove(tam);
			int padre = 0;
			int hijoIzq = 1;							//Hijo izquierdo
			int hijoDer = hijoIzq + 1;
			while(elementos.size() > hijoIzq) 
			{
				if(ultimo.compareTo(elementos.get(hijoIzq)) > 0) 
				{
					subirHijo(hijoIzq, padre);			//Bajamos el padre subiendo el hijo
					ultimo = elementos.get(hijoIzq);
					padre = hijoIzq;
					hijoIzq = padre*2 + 1;
					hijoDer = hijoIzq + 1;
				} 
				else 
					if(elementos.size() > hijoDer && ultimo.compareTo(elementos.get(hijoDer)) > 0) 
				{
					subirHijo(hijoDer, padre);	
					ultimo = elementos.get(hijoDer);
					padre = hijoDer;
					hijoIzq = padre*2 + 1;
					hijoDer = hijoIzq + 1;					
				} 	else break;
			}
			return raiz;
		}
	}
}
