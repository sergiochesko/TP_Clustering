package main;
import java.util.ArrayList;

import arbolgeneradorminimo.ArbolGeneradorMinimo;
import clusterizacion.Clusterizador;
import grafos.GrafoPonderado;
import instancias.Instancias;

public class Main {

	private GrafoPonderado grafo;
	private Instancias instancia;
	
	/*
	 * Este metodo contructor crea una clase principal que la encargada de controlar el resto de las clases.
	 */
	public Main(int numeroDeInstancia) {
		this.instancia = new Instancias();
		
		ArrayList<double[]> arrayDatosCoordenadas = devuelveInstanciaElegida(numeroDeInstancia);
		//creo el grafo
		this.grafo = new GrafoPonderado(arrayDatosCoordenadas.size());
		
		//agrego las aristas al grafo
		for(int i=0;i<arrayDatosCoordenadas.size();i++) {
			for(int j=0;j<arrayDatosCoordenadas.size();j++) {
				if(i!=j) {
					grafo.agregarArista(i, j, distanciaEntreCoord(arrayDatosCoordenadas.get(i), arrayDatosCoordenadas.get(j)));
				}
			}
		}
	}
	
	/*
	 * metodos privado que devuelve la distancia entre dos coordenadas
	 */
	private double distanciaEntreCoord(double[] coord1, double[] coord2) {
		
		double x1 = coord1[0];
		double y1 = coord1[1];
		double x2 = coord2[0];
		double y2 = coord2[1];
		
		double distX= Math.abs(x1-x2);
		double distY= Math.abs(y1-y2);
		
		return Math.sqrt(distX*distX + distY*distY );
	}
	
	/*
	 * Este metodo indica si unaarista se debe dibujar o no, verificando que exista la arista en el grafo
	 */
	public boolean debeDibujarArista(int i,int j) {
		
		return grafo.existeArista(i, j);
	}
	
	/*
	 * Metodo que aplica el AGM a un grafo pasado por parametro
	 */
	public void aplicaAGM() {
		grafo = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(grafo);
	}
	
	/*
	 * Este metodo calcula el centro de coordenadas,a partir de datos pasados por parametro
	 */
	public double[] centroDeCoordenadas(ArrayList<double[]> datosCoordenadas) {
		double Xmayor = datosCoordenadas.get(0)[0];
		double Xmenor = datosCoordenadas.get(0)[0];
		double Ymayor = datosCoordenadas.get(0)[1];
		double Ymenor = datosCoordenadas.get(0)[1];
		
		for (double[] coord : datosCoordenadas) {
			if(coord[0] > Xmayor) {
				Xmayor = coord[0];
			}
			if(coord[0] < Xmenor) {
				Xmenor = coord[0];
			}
			if(coord[1] > Ymayor) {
				Ymayor = coord[1];
			}
			if(coord[1] < Ymenor) {
				Ymenor = coord[1];
			}
		}
		
		double[] coordenadaCentral = {(Xmayor+Xmenor)/2,(Ymayor+Ymenor)/2};
	
		return coordenadaCentral;
	}
	
	/*
	 * Metodo aplica Clustering a al atributo Grafo de la clase.
	 */
	public void aplicaClustering(int cantidadDeClusters) {
		Clusterizador.clusterizar(grafo, cantidadDeClusters);
	}
	
	public ArrayList<double[]> devuelveInstanciaElegida(int numeroDeInstancia){
		return instancia.devuelveInstanciaElegida(numeroDeInstancia);
	}
}
