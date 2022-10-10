import arbolgeneradorminimo.ArbolGeneradorMinimo;
import clusterizacion.Clusterizador;
import grafos.GrafoPonderado;

public class prueba {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

		//g.agregarArista(5, 6,6);
		
		System.out.println(g);
		
		GrafoPonderado ret = ArbolGeneradorMinimo.CreaArbolGeneradorMinimo(g);
		System.out.println(ret);
		
		System.out.println(ret.tamañoArista(0, 1));//
		System.out.println(ret.tamañoArista(0, 2));//
		System.out.println(ret.tamañoArista(1, 2));//
		System.out.println(ret.tamañoArista(1, 4));//
		System.out.println(ret.tamañoArista(1, 3));//
		System.out.println(ret.tamañoArista(3, 4));//
		System.out.println(ret.tamañoArista(2, 6));//
		System.out.println(ret.tamañoArista(2, 4));//
		System.out.println(ret.tamañoArista(4, 5));//
		System.out.println(ret.tamañoArista(5, 6));//
		
		
		Clusterizador.clusterizar(ret, 3);
		System.out.println("aplicado");
		
		System.out.println(ret.tamañoArista(0, 1));//
		System.out.println(ret.tamañoArista(0, 2));//
		System.out.println(ret.tamañoArista(1, 2));//
		System.out.println(ret.tamañoArista(1, 4));//
		System.out.println(ret.tamañoArista(1, 3));//
		System.out.println(ret.tamañoArista(3, 4));//
		System.out.println(ret.tamañoArista(2, 6));//
		System.out.println(ret.tamañoArista(2, 4));//
		System.out.println(ret.tamañoArista(4, 5));//
		System.out.println(ret.tamañoArista(5, 6));//
		
		

	}

}