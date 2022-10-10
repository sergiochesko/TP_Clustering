package arbolgeneradorminimo;

import static org.junit.Assert.*;
import org.junit.Test;
import bfs.BFS;
import grafos.GrafoPonderado;

public class ArbolGeneradorMinimoTest {

	@Test
	public void testTamañoAGM() {
		GrafoPonderado grafo = inicializarGrafoCompleto();
		
		GrafoPonderado grafoAGM = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafo);
		
		assertTrue(grafo.tamano()==(grafoAGM.cantAristas()+1));
		
	}
	
	@Test
	public void testEsConexoAGM() {
		GrafoPonderado grafo = inicializarGrafoCompleto();
		
		GrafoPonderado grafoAGM = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafo);
		
		assertTrue(BFS.esConexo(grafoAGM));
	}
	
	@Test
	public void testElPEsoEsMenorAGM() {
		GrafoPonderado grafo = inicializarGrafoCompleto();
		
		GrafoPonderado grafoAGM = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafo);
		
		assertTrue(grafo.pesoGrafo()>grafoAGM.pesoGrafo());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGrafoInconexo() {
		GrafoPonderado grafo = InicializarGrafoInconexo();
		
		ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafo);
		
	}
	
	
	
	
	
	private GrafoPonderado inicializarGrafoCompleto() {
		
		int tamaño=8;
		GrafoPonderado g = new GrafoPonderado(tamaño);
		
		for(int i = 0; i < tamaño; i++)  {
			for(int j = 0; j < tamaño; j++) if(i!=j){
				g.agregarArista(i, j,j+1);
			}
		}
		return g;
	}
	
	private GrafoPonderado InicializarGrafoInconexo() {
		
		GrafoPonderado g = new GrafoPonderado(7);
		g.agregarArista(0, 1,5);
		g.agregarArista(0, 2,6);
		g.agregarArista(1, 2,2);
		g.agregarArista(1, 3,1);
		g.agregarArista(2, 4,5);
		g.agregarArista(3, 4,4);
		g.agregarArista(5, 6,6);
		
		return g;
		
	}

}
