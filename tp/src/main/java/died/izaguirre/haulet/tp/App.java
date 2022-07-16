package died.izaguirre.haulet.tp;

import java.util.ArrayList;
import java.util.Collections;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.impl.BoletoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.BoletoDao;
import died.izaguirre.haulet.tp.dao.interfaces.CRUD;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.estructuras.*;
import died.izaguirre.haulet.tp.estructuras.grafo.*;
import died.izaguirre.haulet.tp.estructuras.matriz.Matriz;
import died.izaguirre.haulet.tp.tablas.Boleto;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.linea.Linea;

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
        
//        Linea l1 = new Linea("Linea Economica",10,"Linea 9","Rojo",10,true,true);
//        LineaDao daoLinea = new LineaDaoImpl();
//        daoLinea.add(l1);
//        System.out.println(l1.getId());
////        daoLinea.remove(daoLinea.find("Linea 9", "Rojo").getId());
////        Boleto b1 = new Boleto(l1,50D);
////        BoletoDao daoBoleto = new BoletoDaoImpl();
////        daoBoleto.add(b1);
////        
//        
//        DBConnection.close();
        
    }
    
    public static void pruebaGrafo() 
    {
    	Parada parada1 = new Parada(1,"Alvear");
    	Parada parada2 = new Parada(2,"Banderas");
    	Parada parada3 = new Parada(3,"Irigoyen");
    	Parada parada4 = new Parada(4,"Alberdi");
    	Parada parada5 = new Parada(5,"Rufini");
    	
    	ArrayList<Parada> nodos = new ArrayList<>();
    	nodos.add(parada1);
    	nodos.add(parada2);
    	nodos.add(parada3);
    	nodos.add(parada4);
    	nodos.add(parada5);
    	
    	Camino arista1 = new Camino(parada1, parada2, 300);
    	Camino arista2 = new Camino(parada1, parada4, 250);
    	Camino arista3 = new Camino(parada1, parada5, 100);
    	Camino arista4 = new Camino(parada2, parada3, 452);
    	Camino arista5 = new Camino(parada2, parada5, 500);
    	Camino arista6 = new Camino(parada2, parada4, 200);
    	Camino arista7 = new Camino(parada3, parada5, 400);
    	Camino arista8 = new Camino(parada4, parada5, 200);
    	
    	ArrayList<Camino> aristas = new ArrayList<>();
    	aristas.add(arista1);
    	aristas.add(arista2);
    	aristas.add(arista3);
    	aristas.add(arista4);
    	aristas.add(arista5);
    	aristas.add(arista6);
    	aristas.add(arista7);
    	aristas.add(arista8);
    	
    	GrafoDirigido gp = new GrafoDirigido(nodos,aristas);	//El grafo es el ejemplo del power
    															//A = 1, B = 2, C = 3 D = 4 E = 5
    	ArrayList<ArrayList<Parada>> result = gp.todosLosCaminos(parada1, parada5);
    	result.forEach(j -> 
    	{
    		System.out.println();
    		System.out.println("---");
    		Collections.reverse(j);
    		j.forEach(it -> 
    		{
    			System.out.print(" " + it.getNroParada());
    		});
    	});
    	

    	
//    	GrafoDirigido gp = new GrafoDirigido(nodos,aristas);
//    	gp.getMatrizAdyacencia().recorrerMatriz((i,j) -> System.out.println(i));
//    	
//    	GrafoConPeso gp = new GrafoConPeso(nodos,aristas);
//    	gp.getMatrizPeso().recorrerMatriz((i,j) -> System.out.println(i));
//    	gp.dijkstra(parada1, parada5).forEach(j -> System.out.println(j.getNroParada()));
    }
}
