package arbolgeneradorminimo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import grafos.GrafoPonderado;

public class AGMTestCasoCompleto {

	@Test
	public void testCreaArbolGeneradorMinimo() {

		GrafoPonderado grafoOriginal=creaGrafoTipo();

		GrafoPonderado agm = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafoOriginal);
		
		ArrayList<int[]> aristasObtenidas = agm.AristasExistentes();
		ArrayList<int[]> aristasEsperadas = ariastasEsperadas();
				
		assertTrue(verificaIgualdad(aristasEsperadas,aristasObtenidas));
	}

	
	private ArrayList<int[]> ariastasEsperadas() {
		
		ArrayList<int[]> ariastasEsperadas = new ArrayList<int[]>();
		int[] arista = {0,1};
		ariastasEsperadas.add(arista);
		int[] arista2 = {1,4};
		ariastasEsperadas.add(arista2);
		int[] arista3 = {2,4};
		ariastasEsperadas.add(arista3);
		int[] arista4 = {3,4};
		ariastasEsperadas.add(arista4);
		int[] arista5 = {4,5};
		ariastasEsperadas.add(arista5);
		int[] arista6 = {5,6};
		ariastasEsperadas.add(arista6);
		
		return ariastasEsperadas;
	}
	
	private boolean verificaIgualdad(ArrayList<int[]> ariastasEsperadas,  ArrayList<int[]> ariastasObtenidas) {
		boolean bandera=false;
		for(int[] arista: ariastasObtenidas) {
			if(!contiene(ariastasEsperadas,arista)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean contiene(ArrayList<int[]> ariastasEsperadas, int[] aristaEvaluada) {
		for(int[] arista : ariastasEsperadas ) {
			if(arista[0] == aristaEvaluada[0] && arista[1] == aristaEvaluada[1] || 
					arista[1] == aristaEvaluada[0] && arista[0] == aristaEvaluada[1]) {
				return true;
			}
		}
		return false;
	}


	private GrafoPonderado creaGrafoTipo() {
		GrafoPonderado g = new GrafoPonderado(7);

		g.agregarArista(0, 1,3);
		g.agregarArista(0, 2,11);
		g.agregarArista(1, 2,9);
		g.agregarArista(1,4,7);
		g.agregarArista(1,3,8);
		g.agregarArista(2,6,10);
		g.agregarArista(2,4,6);
		g.agregarArista(3,4,2);
		g.agregarArista(4,5,3);
		g.agregarArista(2,6,10);
		g.agregarArista(6,5,1);
		
		return g;
	}
	
}
