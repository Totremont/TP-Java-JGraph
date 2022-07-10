package died.izaguirre.haulet.tp;

import died.izaguirre.haulet.tp.Matriz.*;

/**
 * Acá estará el main principal
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        int[][] mat = {{1,0,0,1},{0,0,0,1},{1,0,0,0},{0,1,0,0}};
        Matriz m = new Matriz(mat);
        
        boolean[][] warshal = m.warshall();
        
        for(int i = 0 ; i < warshal.length ; i++)
        	for(int j = 0 ; j < warshal.length ; j++)
        		System.out.println(warshal[i][j]);
        
    }
}
