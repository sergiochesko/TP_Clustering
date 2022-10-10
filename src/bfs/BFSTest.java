package bfs;


import static org.junit.Assert.*;


import org.junit.Test;


import grafos.Assert;

import grafos.GrafoPonderado;


public class BFSTest {


	@Test(expected=IllegalArgumentException.class)

	public void grafoNullTest() {

		BFS.esConexo(null);

	}


	@Test

	public void grafoVacioTest() {

		assertTrue(BFS.esConexo(new GrafoPonderado(0)));

	}

	
	@Test

	public void grafoUnVerticeTest() {

		assertTrue(BFS.esConexo(new GrafoPonderado(1)));

	}

	
	@Test

	public void grafoDosVerticesAisladosTest() {

		assertFalse(BFS.esConexo(new GrafoPonderado(2)));

	}

	
	@Test

	public void grafoDosVerticesConexoTest() {

		GrafoPonderado g = new GrafoPonderado(2);

		g.agregarArista(0, 1,5);

		assertTrue(BFS.esConexo(g));

	}

	
	@Test

	public void grafoInconexoTest() {

		GrafoPonderado g = inicializarGrafoInconexo();

		
		assertFalse(BFS.esConexo(g));

	}

	
	@Test

	public void grafoConexoTest() {

		GrafoPonderado g = inicializarGrafoInconexo();

		g.agregarArista(4, 6,8);

		assertTrue(BFS.esConexo(g));

	}

	
	@Test

	public void alcanzablesInconexoTest() {

		GrafoPonderado g = inicializarGrafoInconexo();

		
		int[] esperado = {0, 1, 2, 3, 4};

		Assert.iguales(esperado, BFS.alcanzables(g, 0));

	}

	
	private GrafoPonderado inicializarGrafoInconexo() {

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
