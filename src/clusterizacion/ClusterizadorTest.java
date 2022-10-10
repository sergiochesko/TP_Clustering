package clusterizacion;

import static org.junit.Assert.*;

import org.junit.Test;

import arbolgeneradorminimo.ArbolGeneradorMinimo;
import bfs.BFS;
import grafos.GrafoPonderado;

public class ClusterizadorTest {

	@Test
	public void testClusterizaUnaArista() {
		
		GrafoPonderado grafo = inicializarGrafoAGM();
		
		Clusterizador.clusterizar(grafo, 1);
		
		assertTrue(grafo.cantAristas() == grafo.tamano()-2);
		assertFalse(BFS.esConexo(grafo));
	}
	
	@Test
	public void testClusterizaDosArista() {
		
		GrafoPonderado grafo = inicializarGrafoAGM();
		
		Clusterizador.clusterizar(grafo, 2);
		
		assertTrue(grafo.cantAristas() == grafo.tamano()-3);
		assertFalse(BFS.esConexo(grafo));
	}
	
	@Test
	public void testClusterizaTodasArista() {
		
		GrafoPonderado grafo = inicializarGrafoAGM();
		
		Clusterizador.clusterizar(grafo, grafo.tamano()-1);
		
		assertTrue(grafo.cantAristas() == 0);
		assertFalse(BFS.esConexo(grafo));
	}
	
	@Test
	public void testClusterizaDeMasArista() {
		
		GrafoPonderado grafo = inicializarGrafoAGM();
		
		Clusterizador.clusterizar(grafo, grafo.tamano()+1);
		
		assertTrue(grafo.cantAristas() == 0);
		assertFalse(BFS.esConexo(grafo));
	}

	
	
	private GrafoPonderado inicializarGrafoAGM() {
		
		int tamaño=8;
		GrafoPonderado g = new GrafoPonderado(tamaño);
		
		for(int i = 0; i < tamaño; i++)  {
			for(int j = 0; j < tamaño; j++) if(i!=j){
				g.agregarArista(i, j,j+1);
			}
		}
		return ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(g);
	}
}
