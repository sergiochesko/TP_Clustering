package arbolgeneradorminimo;

import java.util.HashSet;
import java.util.Set;

import bfs.BFS;
import grafos.GrafoPonderado;

public class ArbolGeneradorMinimo {

	public static GrafoPonderado CreaArbolGeneradorMinimo(GrafoPonderado grafo) {
		
		verificarSeaConexo(grafo);
		
		GrafoPonderado arbolMinimo = new GrafoPonderado(grafo.tamano());
		Set<Integer> marcados = new HashSet<>();
		marcados.add(0);
		
		double[] posibleMenorArista = {0,0,0};//voy guardando los valores de la menor arista posible (nodo,nodo,tama�oarista)
		
		while (marcados.size()<grafo.tamano()) {
			
			//recorro todos los nodos marcados y voy guardando la menor arista que aparezca
			for(Integer nodoEvaluado : marcados) {
				buscoMenorAristaEntreVecinosDeUnNodo(nodoEvaluado,grafo,posibleMenorArista,marcados);
			}
			
			//agrego la arista al arbol
			arbolMinimo.agregarArista((int)posibleMenorArista[0], (int)posibleMenorArista[1], posibleMenorArista[2]);
			
			//agrego el nodo a los marcados
			marcados.add((int)posibleMenorArista[1]);
			
			//reinicio la arista
			reinicioValorPosibleMenorArista(posibleMenorArista);
		}
		return arbolMinimo;
	}


	private static void buscoMenorAristaEntreVecinosDeUnNodo(Integer nodoEvaluado,
			GrafoPonderado grafo, double[] posibleMenorArista, Set<Integer> marcados) {
		// TODO Auto-generated method stub
		Set<Integer> vecinosDelNodo = grafo.vecinos(nodoEvaluado);//guardo todos los vecinos del nodo
		for (int nodoVecino : vecinosDelNodo) {//recorro la lista de vecinos y voy buscando la menor arista		
			if(!marcados.contains(nodoVecino)) {
				
				if(posibleMenorArista[2]==0) {
					guardoLaMenorArista(posibleMenorArista,nodoEvaluado,nodoVecino,grafo);
				}
				
				else {
						if(grafo.tamañoArista(nodoEvaluado, nodoVecino) <= posibleMenorArista[2]) {
							guardoLaMenorArista(posibleMenorArista,nodoEvaluado,nodoVecino,grafo);
						}
				}
			}
		}
		
	}


	private static void reinicioValorPosibleMenorArista(double[] posibleMenorArista) {
		// TODO Auto-generated method stub
		posibleMenorArista[0]=0;
		posibleMenorArista[1]=0;
		posibleMenorArista[2]=0;
		
	}

	private static void guardoLaMenorArista(double[] posibleMenorArista, Integer nodoEvaluado, int nodoVecino,
			GrafoPonderado grafo) {
		// TODO Auto-generated method stub
		posibleMenorArista[0] = nodoEvaluado;
		posibleMenorArista[1] = nodoVecino;
		posibleMenorArista[2] = grafo.tamañoArista(nodoEvaluado, nodoVecino);
		
	}
	
	private static void verificarSeaConexo(GrafoPonderado grafo)
	{
		if(!BFS.esConexo(grafo) )
			throw new IllegalArgumentException("El grafo no es conexo");
	}
}
