package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

//import instancias.Instancias;
import main.Main;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;


public class View {

	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JPanel panelControles;
	private int instanciaelegida;
	private Main principal;
	private JLabel TituloElijaInstancia;
	private JTextField txtCantClusters;
	private JLabel Titulo;
	private ArrayList<Coordinate> arrayDeCoordenadas;
	private JButton btnGrafoCompleto;
	private JButton btnAgm;
	private JLabel lblNewLabel;
	private JPanel panelSecundario;
	private JButton BotonSeleccionar;
	private JButton btnClustering;
	private JComboBox comboBox_instancia;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//this.instancia = new Instancias();
		
		iniciaElementosDelView();
		
		mapa = new JMapViewer();
		mapa.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		mapa.setBounds(91, 250, 793, 348);
		panelMapa.add(mapa);
		
		
		
		BotonSeleccionar = new JButton("Seleccionar");
		BotonSeleccionar.setBackground(new Color(153, 51, 255));
		BotonSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				instanciaelegida = comboBox_instancia.getSelectedIndex();
				
				//creo la clase principal
				creaPrincipal(instanciaelegida);
				
				creaMapa();
				creaCoordenadasYdibujaMarcadores();
				btnGrafoCompleto.setEnabled(true);
			}


		});
		BotonSeleccionar.setBounds(114, 180, 112, 33);
		panelMapa.add(BotonSeleccionar);
		
		
		
		btnGrafoCompleto = new JButton("Grafo Completo");
		btnGrafoCompleto.setEnabled(false);
		btnGrafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dibujaLineas();
				btnAgm.setEnabled(true);
				/*
				 * Hay que borrar el mapa anterior y dibujar uno nuevo
				 */
			}
		});
		btnGrafoCompleto.setBackground(new Color(153, 51, 255));
		btnGrafoCompleto.setBounds(33, 78, 112, 33);
		panelSecundario.add(btnGrafoCompleto);
		
		
		btnAgm = new JButton("AGM");
		btnAgm.setEnabled(false);
		btnAgm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapa.removeAllMapPolygons();
				principal.aplicaAGM();
				dibujaLineas();
				btnClustering.setEnabled(true);
				txtCantClusters.setEnabled(true);
				txtCantClusters.setEditable(true);
			}
		});
		btnAgm.setBackground(new Color(153, 51, 255));
		btnAgm.setBounds(220, 78, 112, 33);
		panelSecundario.add(btnAgm);
		
		
		btnClustering = new JButton("Clustering");
		btnClustering.setEnabled(false);
		btnClustering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str=txtCantClusters.getText();
				int CantClusters = Integer.parseInt(str);
				mapa.removeAllMapPolygons();
				System.out.println(CantClusters+1);
				principal.aplicaClustering(CantClusters);
				dibujaLineas();
			}
		});
		btnClustering.setBackground(new Color(153, 51, 255));
		btnClustering.setBounds(386, 78, 112, 33);
		panelSecundario.add(btnClustering);
		
	}
	private void creaPrincipal(int instanciaelegida) {
		this.instanciaelegida=instanciaelegida;
		principal = new Main(instanciaelegida);
		
	}
	
	private void dibujaMatrizDeLineas(ArrayList<ArrayList<MapPolygon>> matrizDeLineas) {
		// TODO Auto-generated method stub
		for(int i=0;i<matrizDeLineas.size();i++) {
			for(int j=0;j<matrizDeLineas.size();j++) {
				//System.out.println("entro");
				if(principal.debeDibujarArista(i, j)) {
					mapa.addMapPolygon(matrizDeLineas.get(i).get(j));
				}
			}
		}
		
	}

	private void creaMatrizDeLineas(ArrayList<Coordinate> arrayDeCoordenadas, ArrayList<ArrayList<MapPolygon>> matrizDeLineas) {
		// TODO Auto-generated method stub
		for(Coordinate coord1 : arrayDeCoordenadas) {
			ArrayList<MapPolygon> fila = new ArrayList<MapPolygon>();
			for(Coordinate coord2 : arrayDeCoordenadas) {
				fila.add(creaSegmento(coord1,coord2));
			}
			matrizDeLineas.add(fila);
		}
		
	}

	private void agregarCoordenadasAlMapa(ArrayList<Coordinate> arrayDeCoordenadas) {
		// TODO Auto-generated method stub
		for(Coordinate coord : arrayDeCoordenadas) {
			agregaMarcadorAlMapa(creaMarcador(coord));
		}
	}

	private ArrayList<Coordinate> CreaArrayCoordenadas(ArrayList<double[]> arrayDatos) {
		// TODO Auto-generated method stub
		ArrayList<Coordinate> arrayDeCoordenadas = new ArrayList<Coordinate>();
		for(double[] coord : arrayDatos) {
			arrayDeCoordenadas.add(creaCoordenada(coord[0], coord[1]));
		}
		return arrayDeCoordenadas;
	}

	private Coordinate creaCoordenada(double x, double y) {
		return new Coordinate(x, y);	
	}
	
	private MapMarker creaMarcador(Coordinate coordenada) {
		return new MapMarkerDot(coordenada);
	}
	
	private void agregaMarcadorAlMapa(MapMarker marcador) {
		mapa.addMapMarker(marcador);
	}
	

	
	private MapPolygonImpl creaSegmento(Coordinate coord1, Coordinate coord2) {
		ArrayList<Coordinate> segmento = new ArrayList<Coordinate>();
		segmento.add(coord1);
		segmento.add(coord2);
		segmento.add(coord1);
		return new MapPolygonImpl(segmento);
	}

	private void creaMapa() {
		double[] DatoCoordenadaCentral = principal.centroDeCoordenadas(principal.devuelveInstanciaElegida(instanciaelegida));
		Coordinate coordenadaCentral = creaCoordenada(DatoCoordenadaCentral[0], DatoCoordenadaCentral[1]);
		mapa.setDisplayPosition(coordenadaCentral, 12);
	}

	private void creaCoordenadasYdibujaMarcadores() {
		arrayDeCoordenadas = CreaArrayCoordenadas(principal.devuelveInstanciaElegida(instanciaelegida));
		agregarCoordenadasAlMapa(arrayDeCoordenadas);
	}

	private void dibujaLineas() {
		ArrayList<ArrayList<MapPolygon>> matrizDeLineas = new ArrayList<ArrayList<MapPolygon>>();
		creaMatrizDeLineas(arrayDeCoordenadas, matrizDeLineas);
		dibujaMatrizDeLineas(matrizDeLineas);
	}
	
	private void iniciaElementosDelView() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelMapa = new JPanel();
		panelMapa.setBackground(Color.BLACK);
		panelMapa.setBounds(500, 500, 400, 600);
		
		frame.getContentPane().add(panelMapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(400, 200, 300, 300);
		
		
		panelControles.setLayout(null);
		panelMapa.setLayout(null);
		
		Titulo = new JLabel("Clustering con AGM");
		Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 46));
		Titulo.setBounds(277, 11, 461, 56);
		panelMapa.add(Titulo);
		
		TituloElijaInstancia = new JLabel("Elija num. de Instancia");
		TituloElijaInstancia.setForeground(new Color(255, 255, 255));
		TituloElijaInstancia.setFont(new Font("Tahoma", Font.BOLD, 16));
		TituloElijaInstancia.setBounds(91, 103, 258, 33);
		panelMapa.add(TituloElijaInstancia);
		
		comboBox_instancia = new JComboBox();
		comboBox_instancia.setBounds(77, 142, 177, 27);
		comboBox_instancia.setToolTipText("");
		comboBox_instancia.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
		
		
		panelMapa.add(comboBox_instancia);
		
		
		panelSecundario = new JPanel();
		panelSecundario.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelSecundario.setBackground(Color.DARK_GRAY);
		panelSecundario.setBounds(330, 102, 554, 122);
		panelMapa.add(panelSecundario);
		panelSecundario.setLayout(null);
		
		txtCantClusters = new JTextField();
		txtCantClusters.setEditable(false);
		txtCantClusters.setEnabled(false);
		txtCantClusters.setText("Cant. Clusters");
		txtCantClusters.setColumns(10);
		txtCantClusters.setBounds(386, 40, 112, 27);
		panelSecundario.add(txtCantClusters);
		
		lblNewLabel = new JLabel("Proceso de Clustering");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(181, 11, 246, 34);
		panelSecundario.add(lblNewLabel);
	}
	/*
	 * final
	 */
}
