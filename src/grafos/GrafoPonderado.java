package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GrafoPonderado
{
	// Representamos el grafo por su matriz de adyacencia
	private double[][] A;
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public GrafoPonderado(int vertices)
	{
		A = new double[vertices][vertices];
	}
	
	// Agregado de aristas
	public void agregarArista(int i, int j, double peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = peso;
		A[j][i] = peso;
	}
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = 0;
		A[j][i] = 0;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		//verificarDistintos(i, j);

		return A[i][j]!=0;
	}
	
	// Informa el tama�o la arista especificada
	public double tamañoArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return A.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	public boolean aristaEsMayorAsusVecinas(int nodo1, int nodo2, double porcentaje) {
		
		for(int i=0;i<this.tamano();i++) {
			
			if((existeArista(nodo1, i) && tamañoArista(nodo1, i)>tamañoArista(nodo1, nodo2)*(1+porcentaje)) || 
					existeArista(nodo2, i) && tamañoArista(nodo2, i)>tamañoArista(nodo1, nodo2)*(1+porcentaje)) {
				return false;
			}
		}
		return true;
		
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public double pesoGrafo() {
		double peso = 0;
		for(int i = 0; i < this.tamano(); i++) {
			for(int j = 0; j < this.tamano(); j++) if(i!=j) {
				peso = peso + this.tamañoArista(i, j);
			}
		}
		return peso/2;
	}
	
	public int cantAristas() {
		int cantAristas=0;
		for(int i = 0; i < this.tamano(); i++) {
			for(int j = 0; j < this.tamano(); j++) {
				if(this.existeArista(i, j)) {
					cantAristas++;
				}
			}
		}
		return cantAristas/2;
	}
	
	public ArrayList<int[]> AristasExistentes(){
		ArrayList<int[]> AristasExistentes = new ArrayList<int[]>();
		
		for(int i = 0; i < this.tamano(); i++) {
			for(int j = 0; j < this.tamano(); j++) {
				if(this.existeArista(i, j)) {
					int[] arista = {i,j};
					AristasExistentes.add(arista);
				}
			}
		}
		System.out.println(AristasExistentes);
		return AristasExistentes;
		
	}
}
