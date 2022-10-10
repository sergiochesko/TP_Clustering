package clusterizacion;

import java.util.ArrayList;
import java.util.Set;

import grafos.GrafoPonderado;

public class Clusterizador {

	private static ArrayList<int[]> aristasAeliminar;
	private static double porcentajeSignificativamenteMayor=0.3;
	



	public static void clusterizar(GrafoPonderado grafo, int CantEliminacionesRequeridas) {
		
		while(CantEliminacionesRequeridas > 0 && grafo.cantAristas()>0) {
			
			CantEliminacionesRequeridas = eliminaAristasMayoresAvecinas(grafo,CantEliminacionesRequeridas);
		}
	}
	
 	private static int eliminaAristasMayoresAvecinas(GrafoPonderado grafo, Integer CantEliminacionesPosibles) {
		
		//guardo las aristas que voy a eliminar para tener controlada la eliminaci�n y que no sea tan dinamica
		aristasAeliminar = new ArrayList<int[]>(); 
		
		//recorro cada nodo del grafo
		
		armaListaOrdenadaDeAristasAeliminar(grafo, CantEliminacionesPosibles);
		
		//recorro el array ordenado de aristas a eliminar, y elimino las mayores
		for(int[] arista : aristasAeliminar) {
			if(CantEliminacionesPosibles>0) {
				grafo.eliminarArista(arista[0], arista[1]);
				System.out.println("arista eliminada");
				CantEliminacionesPosibles--;
			}
		}
		return CantEliminacionesPosibles;
	}
	
	
	private static void armaListaOrdenadaDeAristasAeliminar(GrafoPonderado grafo, Integer cantEliminacionesPosibles) {
		// TODO Auto-generated method stub
			for(int i=0;i<grafo.tamano();i++) {
			
			Set<Integer> vecinos = grafo.vecinos(i); //guardo los vecinos de i en un set
			
			for (int vecino : vecinos) {//recorro los vecinos de i
				
				
				int[] arista = {i, vecino}; //armo la arista formada por i y su vecino
				
				//compruebo la arista formada sea la mayor de todas sus vecinas y que no este agregada a 
				//la lista de aristar para eliminar
				if(!AristaYaEstaAgregada(arista,aristasAeliminar) && 
					grafo.aristaEsMayorAsusVecinas(arista[0], arista[1], porcentajeSignificativamenteMayor)) {//ver el porcentaje

					//agregar ordenado
					agregarOrdenado(aristasAeliminar, arista, grafo);
					
				}
			}
		}
		
	}
	
	private static boolean AristaYaEstaAgregada(int[] arista, ArrayList<int[]> aristasAeliminar) {
		
		for(int[] a : aristasAeliminar) {
			if(AristasSonIguales(arista, a)) {
				return true;
			}
		}
		return false;
		
	}
	
	private static boolean AristasSonIguales(int[] arista1, int[] arista2) {
		
		return ((arista1[0]==arista2[0] && arista1[1]==arista2[1]) ||(arista1[0]==arista2[1] && arista1[1]==arista2[0]));
				
	}

	private static void agregarOrdenado(ArrayList<int[]> aristasAeliminar, int[] arista, GrafoPonderado grafo) {
		if(aristasAeliminar.size()==0) {
			aristasAeliminar.add(arista);
		}
		else {
			boolean controlAgregado=false;
			for(int i=0;i<aristasAeliminar.size();i++) {
				if(grafo.tamañoArista(arista[0], arista[1]) > grafo.tamañoArista(aristasAeliminar.get(i)[0], aristasAeliminar.get(i)[1]) ) {
					aristasAeliminar.add(i, arista);
					controlAgregado=true;
					break;
				}
			}
			if(controlAgregado==false) {
				aristasAeliminar.add(arista);
			}
			
		}	
		
	}
}
