package died.izaguirre.haulet.tp;

import java.util.ArrayList;

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
import died.izaguirre.haulet.tp.tablas.Linea;
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
        
        Linea l1 = new Linea("Linea Economica",10,"Linea 9","Rojo",10,true,true);
        LineaDao daoLinea = new LineaDaoImpl();
        daoLinea.add(l1);
        System.out.println(daoLinea.find("Linea 9", "Rojo").getNombre());
//        daoLinea.remove(daoLinea.find("Linea 9", "Rojo").getId());
//        Boleto b1 = new Boleto(l1,50D);
//        BoletoDao daoBoleto = new BoletoDaoImpl();
//        daoBoleto.add(b1);
//        
        
        DBConnection.close();
        
    }
    
    public static void pruebaGrafo() 
    {
    	Parada parada1 = new Parada(1,"Alvear");
    	Parada parada2 = new Parada(2,"Banderas");
    	Parada parada3 = new Parada(3,"Irigoyen");
    	Parada parada4 = new Parada(4,"Alberdi");
    	
    	ArrayList<Parada> nodos = new ArrayList<>();
    	nodos.add(parada1);
    	nodos.add(parada2);
    	nodos.add(parada3);
    	nodos.add(parada4);
    	
    	Camino arista1 = new Camino(parada1, parada2, 300);
    	Camino arista2 = new Camino(parada1, parada4, 1200);
    	Camino arista3 = new Camino(parada2, parada3, 200);
    	Camino arista4 = new Camino(parada2, parada4, 500);
    	Camino arista5 = new Camino(parada3, parada4, 200);
    	
    	ArrayList<Camino> aristas = new ArrayList<>();
    	aristas.add(arista1);
    	aristas.add(arista2);
    	aristas.add(arista3);
    	aristas.add(arista4);
    	aristas.add(arista5);

    	
//    	GrafoDirigido gp = new GrafoDirigido(nodos,aristas);
//    	gp.getMatrizAdyacencia().recorrerMatriz((i,j) -> System.out.println(i));
//    	
//    	GrafoConPeso gp = new GrafoConPeso(nodos,aristas);
//    	gp.getMatrizPeso().recorrerMatriz((i,j) -> System.out.println(i));
//    	gp.dijkstra(parada1, parada4).forEach(j -> System.out.println(j.getNroParada()));
    }
}
