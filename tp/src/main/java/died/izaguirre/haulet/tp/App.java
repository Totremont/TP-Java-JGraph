package died.izaguirre.haulet.tp;

import java.util.ArrayList;

import died.izaguirre.haulet.tp.estructuras.*;
import died.izaguirre.haulet.tp.estructuras.grafo.*;
import died.izaguirre.haulet.tp.estructuras.matriz.Matriz;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

/**
 * Acá estará el main principal
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        int[][] mat = {{1,0,0,1},{0,0,0,1},{1,0,0,0},{0,1,0,0}};
        Matriz m = new Matriz(mat);
        pruebaGrafo();
               
//        GrafoConPeso warshal = new GrafoDirigido(null, null, m);
//        Matriz resultado = warshal.warshall();  
//        resultado.recorrerMatriz((i,j) -> System.out.println(i));
        
//        Matriz resultado = m.multiplicar(m, m);
//        resultado.recorrerMatriz((i,j) -> System.out.println(i));                
        
    }
    
    public static void pruebaGrafo() 
    {
    	Parada parada1 = new Parada(1,1,"Alvear");
    	Parada parada2 = new Parada(2,2,"Banderas");
    	Parada parada3 = new Parada(3,3,"Irigoyen");
    	Parada parada4 = new Parada(4,4,"Alberdi");
    	
    	ArrayList<Parada> nodos = new ArrayList<>();
    	nodos.add(parada1);
    	nodos.add(parada2);
    	nodos.add(parada3);
    	nodos.add(parada4);
    	
    	ArrayList<Parada> aristas1 = new ArrayList<>();
    	aristas1.add(parada2);
    	aristas1.add(parada4);
    	
    	ArrayList<Integer> distancias1 = new ArrayList<>();
    	distancias1.add(300);
    	distancias1.add(1200);
    	
    	
    	ArrayList<Parada> aristas2 = new ArrayList<>();
    	aristas2.add(parada3);
    	aristas2.add(parada4);
    	
    	ArrayList<Integer> distancias2 = new ArrayList<>();
    	distancias2.add(200);
    	distancias2.add(500);
    	
    	ArrayList<Parada> aristas3 = new ArrayList<>();
    	aristas3.add(parada4);
    	
    	ArrayList<Integer> distancias3 = new ArrayList<>();
    	distancias3.add(200);
    	
    	ArrayList<Parada> aristas4 = new ArrayList<>();
    	
    	Camino camino1 = new Camino(parada1,aristas1,distancias1);
    	Camino camino2 = new Camino(parada2,aristas2,distancias2);
    	Camino camino3 = new Camino(parada3, aristas3, distancias3);
    	Camino camino4 = new Camino(parada4, aristas4, null);
    	
    	ArrayList<Camino> caminos = new ArrayList<>();
    	caminos.add(camino1);
    	caminos.add(camino2);
    	caminos.add(camino3);
    	caminos.add(camino4);
    	
//    	GrafoDirigido gp = new GrafoDirigido(nodos,caminos);
//    	gp.getMatrizAdyacencia().recorrerMatriz((i,j) -> System.out.println(i));	Anda
    	
    	GrafoConPeso gp = new GrafoConPeso(nodos,caminos);
    	//gp.getMatrizPeso().recorrerMatriz((i,j) -> System.out.println(i));	Anda
    	//gp.dijkstra(parada1, parada2).forEach(j -> System.out.println(j.getNroParada())); Anda
    }
}
