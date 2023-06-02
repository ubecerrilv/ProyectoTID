package tid.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import tid.controlador.Comandos;
import tid.modelo.Imagen;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	GridBagConstraints rest;
	BufferedImage imagenAct ;
	Imagen imgActRGB, segundaAux;
	int cont1 =0, conti = 1;
	VentanaG ventanaGuar;
	ArrayList<Imagen> imagenesCollage;
	
	JPanel panel;
	JLabel autor, img;
	JButton bBuscar, bAtras, bDelante, bGuardar;
	JTabbedPane menu;
	JFileChooser imagen;
	//PANELES DEL MENU
	JPanel oBas, collage, rotacion, filtros, morfo, segmentacion;
	
	//ELEMENTOS DE LOS PANELES PARA LAS OPERACIONES
	//OP BASICAS
	JPanel ec, inv, ad;
	JButton btnEcz, btnEcA, btnEcN, btnInvB, btnInvF, btnAdd, btnSus, btnSeleccionarSus;
	JLabel imgRes, segundaImg;
	
	//COLLAGE
	JPanel imgCollage, tamanos;
	JButton agregarImg, generarCollage, regenerarCollage, reset;
	JLabel imgAgregadaA, columnas, renglones;
	JTextArea c, r;
	
	//ROTACION Y ESPEJO
	JPanel rot, espejo;
	JButton rotDer, rotIzq, espj;
	
	//FILTROS
	JButton moda, media, mediana, gauss, n1,n2,n3,n4,n5,n6,n7,n8,n9, lp4, lp8,
	pre, sobel, robert;
	JPanel estad, gaussianos, maxMin, laplace, contornos;
	
	//OPERACIONES MORFO
	JButton ero, dila, estruc;
	JPanel defEstruc, pDefMor;
	
	public Ventana() {
		super("Tratamiento de imagenes");

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//CREACION DEL OBJETO DE RESTRICCIONES
		rest = new GridBagConstraints();
		imagenesCollage = new ArrayList<Imagen>();
		
		//CREACION DE LOS ELEMENTOS DE LOS PANELES PRINCIPALES
		//CREACION Y ANAÃ‘IR LOS PANELES DEL MENU
			oBas = new JPanel(new GridLayout(3,1));
			collage = new JPanel(new GridLayout(2,1));
			rotacion = new JPanel(new GridLayout(2,1));
			filtros = new JPanel(new GridBagLayout());
			morfo = new JPanel(new GridLayout(2,1));
			segmentacion = new JPanel();
			
			//FALTA AGREGAR LO QUE LLEVA CADA PANEL
			//MORFO
			defEstruc = new JPanel();
			defEstruc.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Elemento estructurante",TitledBorder.CENTER,TitledBorder.TOP));
			defEstruc.setLayout(new GridBagLayout());
			
			pDefMor = new JPanel();
			pDefMor.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Operaciones morfologicas",TitledBorder.CENTER,TitledBorder.TOP));
			pDefMor.setLayout(new GridBagLayout());
			ero = new JButton("Erosionar");
			ero.setActionCommand(Comandos.EROSION);
			ero.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			pDefMor.add(ero, rest);//EROSIONAR AGREGADO
			
			dila = new JButton("Dilatar");
			dila.setActionCommand(Comandos.DILATACION);
			dila.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			pDefMor.add(dila, rest);//DILATAR AGREGADO
			
			morfo.add(defEstruc);
			morfo.add(pDefMor);
			
			//OPERACIONES BASICAS
			ec = new JPanel();
			ec.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Ecualizacion",TitledBorder.CENTER,TitledBorder.TOP));
			ec.setLayout(new GridBagLayout());
			btnEcz = new JButton("Ecualizar imagen");
			btnEcz.setActionCommand(Comandos.ECUALIZAR);
			btnEcz.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 2;
			rest.gridheight = 1;
			
			ec.add(btnEcz, rest);//ECUALIZAR AGREGADO
			
			btnEcA = new JButton("Histograma original");
			btnEcA.setActionCommand(Comandos.HISTA);
			btnEcA.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ec.add(btnEcA, rest);//MOSTRAR HISTOGRAMA ANTERIOR AGREGADO
			
			btnEcN = new JButton("Histograma ecualizado");
			btnEcN.setActionCommand(Comandos.HISTN);
			btnEcN.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ec.add(btnEcN, rest);//MOSTRAR HISTOGRAMA ANTERIOR AGREGADO

			
			inv =  new JPanel();
			inv.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Inversion",TitledBorder.CENTER,TitledBorder.TOP));
			inv.setLayout(new GridBagLayout());
			btnInvB = new JButton("Inversion binaria");
			btnInvB.setActionCommand(Comandos.INVB);
			btnInvB.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			inv.add(btnInvB, rest);//INVERSION BINARIA AGREGADO
			
			btnInvF = new JButton("Inversion fotografica");
			btnInvF.setActionCommand(Comandos.INVF);
			btnInvF.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			inv.add(btnInvF, rest);//INVERSION FOTOGRAFICA AGREGADO
			
			ad = new JPanel();
			ad.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Adicion y sustraccion",TitledBorder.CENTER,TitledBorder.TOP));
			ad.setLayout(new GridBagLayout());
			
			btnSeleccionarSus = new JButton("Selecciona la imagen a adicionar o sustraer");
			btnSeleccionarSus.setActionCommand(Comandos.BUSCARSUS);
			btnSeleccionarSus.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 2;
			rest.gridheight = 1;
			ad.add(btnSeleccionarSus, rest);
			
			segundaImg = new JLabel();
			rest.gridx = 0;
			rest.gridy = 1;
			rest.gridwidth = 2;
			rest.gridheight = 1;
			ad.add(segundaImg, rest);
			
			btnAdd = new JButton("Adicionar");
			btnAdd.setActionCommand(Comandos.ADD);
			btnAdd.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 2;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(btnAdd, rest);//ADD AGREGADO
			
			btnSus = new JButton("Sustraer");
			btnSus.setActionCommand(Comandos.SUS);
			btnSus.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 2;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(btnSus, rest);//SUS AGREGADO
			
			rest.weightx=0;
			
			oBas.add(ec);
			oBas.add(inv);
			oBas.add(ad);//FIN OPERACIONES BASICAS
			
			//COLLAGE
			imgCollage = new JPanel();
			imgCollage.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Selecion de imagenes",TitledBorder.CENTER,TitledBorder.TOP));
			imgCollage.setLayout(new GridBagLayout());
			agregarImg = new JButton("Agregar imagen");
			agregarImg.setActionCommand(Comandos.ACOLLAGE);
			agregarImg.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			imgCollage.add(agregarImg, rest);//AGREGAR IMAGEN AGREGADO
			
			imgAgregadaA = new JLabel();//AUN NO SE AGREGA HASTA TENER POR LO MENOS UNA IMAGEN
			
			
			tamanos = new JPanel();
			tamanos.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tamanos",TitledBorder.CENTER,TitledBorder.TOP));
			tamanos.setLayout(new GridBagLayout());
			generarCollage = new JButton("Generar collage");
			generarCollage.setActionCommand(Comandos.GCOLLAGE);
			generarCollage.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 2;
			rest.gridwidth = 2;
			rest.gridheight = 1;
			tamanos.add(generarCollage, rest);//BOTON DE GENERAR COLLAGE AGREGADO
			
			regenerarCollage = new JButton("Generar otra distribucion");
			regenerarCollage.setActionCommand(Comandos.REGENERAR);
			regenerarCollage.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 3;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rest.fill = GridBagConstraints.HORIZONTAL;
			tamanos.add(regenerarCollage, rest);
			
			reset = new JButton("Limpiar imagenes");
			reset.setActionCommand(Comandos.RESET);
			reset.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 3;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			tamanos.add(reset, rest);
			
			columnas = new JLabel("Columnas:");
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			tamanos.add(columnas, rest);//ETIQUETA DE COLUMNAS AGREGADO
			
			c = new JTextArea();
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			tamanos.add(c, rest);//AREA DE COLUMNAS AGREGADO
			
			renglones = new JLabel("Renglones:");
			rest.gridx = 0;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			tamanos.add(renglones, rest);//ETIQUETA DE RENGLONES AGREGADO
			
			r = new JTextArea();
			rest.gridx = 1;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			tamanos.add(r, rest);//AREA DE RENGLONES AGREGADO
			rest.fill = GridBagConstraints.NONE;
		
			collage.add(imgCollage);
			collage.add(tamanos);
			
			//ROTACION Y ESPEJO
			rot = new JPanel();
			rot.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Rotaciones",TitledBorder.CENTER,TitledBorder.TOP));
			rot.setLayout(new GridBagLayout());
			rotIzq = new JButton("Rotar 90 a la izquierda");
			rotIzq.setActionCommand(Comandos.ROTARIZQUIERDA);
			rotIzq.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rot.add(rotIzq, rest);//ROTAR IZQUIERDA
			
			rotDer = new JButton("Rotar 90 a la derecha");
			rotDer.setActionCommand(Comandos.ROTARDERECHA);
			rotDer.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rot.add(rotDer, rest);//ROTAR DERECHA
			
			espejo = new JPanel();
			espejo.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Espejo",TitledBorder.CENTER,TitledBorder.TOP));
			espejo.setLayout(new GridBagLayout());
			espj = new JButton("Efecto espejo");
			espj.setActionCommand(Comandos.ESPEJO);
			espj.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			espejo.add(espj, rest);//ROTAR DERECHA
			
			rotacion.add(rot);
			rotacion.add(espejo);
			
			//FILTROS
			estad = new JPanel();
			estad.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Filtros estadisticos",TitledBorder.CENTER,TitledBorder.TOP));
			estad.setLayout(new GridBagLayout());
			media = new JButton("Filtro de media");
			media.setActionCommand(Comandos.MEDIA);
			media.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			estad.add(media, rest);//FILTRO MEDIA
			
			mediana = new JButton("Filtro mediana");
			mediana.setActionCommand(Comandos.MEDIANA);
			mediana.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			estad.add(mediana, rest);//FILTRO MEDIANA
			
			moda = new  JButton("Filtro moda");
			moda.setActionCommand(Comandos.MODA);
			moda.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			estad.add(moda, rest);//FILTRO MODA

			gaussianos = new JPanel();
			gaussianos.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Filtro gaussiano",TitledBorder.CENTER,TitledBorder.TOP));
			gaussianos.setLayout(new GridBagLayout());
			gauss = new JButton("Gaussiano");
			gauss.setActionCommand(Comandos.GAUSS);
			gauss.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			gaussianos.add(gauss, rest);//FILTRO GAUSSIANO
			
			maxMin = new JPanel();
			maxMin.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Filtro de maximos y minimos",TitledBorder.CENTER,TitledBorder.TOP));
			maxMin.setLayout(new GridBagLayout());
			n1 = new JButton("n = 1");
			n1.setActionCommand(Comandos.N1);
			n1.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n1,rest);//N1
			
			n2 = new JButton("n = 2");
			n2.setActionCommand(Comandos.N2);
			n2.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n2,rest);//N2
			
			n3 = new JButton("n = 3");
			n3.setActionCommand(Comandos.N3);
			n3.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n3,rest);//N3
			
			n4 = new JButton("n = 4");
			n4.setActionCommand(Comandos.N4);
			n4.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n4,rest);//N4
			
			n5 = new JButton("n = 5");
			n5.setActionCommand(Comandos.N5);
			n5.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n5,rest);//N5
			
			n6 = new JButton("n = 6");
			n6.setActionCommand(Comandos.N6);
			n6.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n6,rest);//N
			
			n7 = new JButton("n = 7");
			n7.setActionCommand(Comandos.N7);
			n7.addActionListener(this);
			rest.gridx = 3;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n7,rest);//N7
			
			n8 = new JButton("n = 8");
			n8.setActionCommand(Comandos.N8);
			n8.addActionListener(this);
			rest.gridx = 3;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n8,rest);//N8
			
			n9 = new JButton("n = 9");
			n9.setActionCommand(Comandos.N9);
			n9.addActionListener(this);
			rest.gridx = 4;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			maxMin.add(n9,rest);//N9
			
			laplace = new JPanel();
			laplace.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Filtros laplacianos",TitledBorder.CENTER,TitledBorder.TOP));
			laplace.setLayout(new GridBagLayout());
			lp4 = new JButton("Laplace 4 vecinos");
			lp4.setActionCommand(Comandos.LP4);
			lp4.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			laplace.add(lp4, rest);//FILTRO LP4
			
			lp8 = new JButton("Laplace 8 vecinos");
			lp8.setActionCommand(Comandos.LP8);
			lp8.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			laplace.add(lp8, rest);//FILTRO LP8
			
			contornos = new JPanel();
			contornos.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Deteccion de contornos",TitledBorder.CENTER,TitledBorder.TOP));
			contornos.setLayout(new GridBagLayout());
			pre = new JButton("Prewitt");
			pre.setActionCommand(Comandos.PRE);
			pre.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			contornos.add(pre, rest);//FILTRO PREWITT
			
			sobel = new JButton("Sobel");
			sobel.setActionCommand(Comandos.SOBEL);
			sobel.addActionListener(this);
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			contornos.add(sobel, rest);//FILTRO SOBEL
			
			robert = new JButton("Roberts");
			robert.setActionCommand("Roberts");
			robert.addActionListener(this);
			rest.gridx = 3;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			contornos.add(robert, rest);//FILTRO ROBERTS
			
			rest.fill=GridBagConstraints.BOTH;
			rest.gridx = 0;rest.gridy = 0;
			filtros.add(estad,rest);rest.gridx = 0;rest.gridy = 1;
			filtros.add(gaussianos,rest);rest.gridx = 0;rest.gridy = 2;
			filtros.add(maxMin,rest);rest.gridx = 0;rest.gridy = 3;
			filtros.add(laplace,rest);rest.gridx = 0;rest.gridy = 4;
			filtros.add(contornos,rest);rest.fill=GridBagConstraints.NONE;
		
		//CREAR E INSERTAR COMPONENTES
		
		//CREAR FILE CHOOSER
		imagen = new JFileChooser();
		imagen.setFileFilter(new FileNameExtensionFilter ("png","PNG", "jpg", "JPG", "tif", "TIF", "jpeg", "JPEG", "bmp", "BMP", "ppm", "PPM"));
		
		//CREAR PANELES
		//PANEL CENTRAL
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));

		//CREAR ETIQUETAS
		autor = new JLabel("<html>Realizado por:<br><ul><li>Ulises Becerril Valdes</li> <li>Marcos Daniel Gomez Velazquez</li><li>Raul Salazar Godinez</li></ul></html>");
		rest.gridx = 3;
		rest.gridy = 2;
		rest.weightx = 1.0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(autor, rest);
		rest.weightx = 0;
		
		
		img = new JLabel();
		
		rest.gridx = 0;
		rest.gridy = 1;
		rest.weighty = 1.0;
		rest.gridwidth = 3;
		rest.gridheight = 1;
		rest.fill= GridBagConstraints.BOTH;
		
		panel.add(img, rest);
		rest.weighty = 0;
		rest.fill= GridBagConstraints.NONE;

			
		//CREAR BOTON DE BUSCAR
		bBuscar = new JButton("Seleccionar imagen");
		bBuscar.setActionCommand(tid.controlador.Comandos.BUSCA);
		bBuscar.addActionListener(this);
		
		rest.gridx = 0;
		rest.gridy = 0;
		rest.weightx =1.0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		
		panel.add(bBuscar, rest);
		
		imgRes = new JLabel();//LABEL DE RESOLUCION
		rest.gridx = 2;
		rest.gridy = 0;
		rest.weightx=1.0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(imgRes, rest);//RES AGREGADO
		
		
		bAtras = new JButton("<-");
		bAtras.setActionCommand(tid.controlador.Comandos.ATRAS);
		bAtras.addActionListener(this);
		
		rest.gridx = 0;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bAtras, rest);
		
		
		bDelante = new JButton("->");
		bDelante.setActionCommand(tid.controlador.Comandos.ADELANTE);
		bDelante.addActionListener(this);
		
		rest.gridx = 1;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bDelante, rest);
		
		
		bGuardar = new JButton("Guardar imagen");
		bGuardar.setActionCommand(tid.controlador.Comandos.GUARDA);
		bGuardar.addActionListener(this);
		
		rest.gridx = 2;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bGuardar, rest);
		rest.weightx =0;
		
		//CREAR, AGREGAR LOS ELEMENTOS Y AGREGAR MENU
		menu = new JTabbedPane();
		menu.addTab("Basicos", oBas);
		menu.addTab("Collage", collage);
		menu.addTab("Rotacion", rotacion);
		menu.addTab("Filtros", filtros);
		menu.addTab("Op. morfologicas", morfo);
		menu.addTab("Segmentacion", segmentacion);
		
		rest.gridx = 3;
		rest.gridy = 1;
		rest.weighty = 1.0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		rest.fill= GridBagConstraints.BOTH;
		
		panel.add(menu, rest);
		rest.weighty = 0;
		rest.fill= GridBagConstraints.NONE;
		//AGREGAR LOS PANELES A LA VENTANA
		this.add(panel);
		
	
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//FIN CONSTRUCTOR DE LA VENTANA

@Override
public void actionPerformed(ActionEvent e) {
		
	switch (e.getActionCommand()) {//CASO DE LOS COMANDOS (BOTONES)
	case Comandos.BUSCA://BUSCAR IMAGEN
		imagen.showOpenDialog(this);
		
		try {
			imagenAct = ImageIO.read(new File(imagen.getSelectedFile().getPath()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Icon icono = new ImageIcon(imagenAct.getScaledInstance(img.getWidth(), img.getHeight(), DO_NOTHING_ON_CLOSE));
		
		img.setIcon(icono);
		imgRes.setText("Resolucion de la imagen: "+imagenAct.getWidth()+"x"+imagenAct.getHeight());

		imgActRGB = new Imagen(imagen.getSelectedFile().getPath());
		
		this.control.ejecutaComando(Comandos.BUSCA, imgActRGB, null);
		
		this.cont1=0;
		this.repaint();
		
		break;		
	
	case Comandos.ATRAS: //REGRESAR UN PASO
		imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ATRAS, null, null);
		
		if(imgActRGB != null) {
			img.setIcon(imgActRGB.convertirMatAImg());
			this.repaint();
		}
		break;
		
	case Comandos.ADELANTE:// ADELANTAR UN PASO
		imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ADELANTE, null, null);
		if(imgActRGB != null) {
			img.setIcon(imgActRGB.convertirMatAImg());
			this.repaint();
		}
		break;
		
	case Comandos.GUARDA://GUARDAR IMAGEN
		if(imgActRGB != null) {
			this.control.ejecutaComando(Comandos.GUARDA, imgActRGB, null);
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen y realiza un procedimiento para poder guardarla");
		}//FIN IF DE IMAGEN NULA
		
		break;
		
	case Comandos.ECUALIZAR://ECUALIZAR IMAGEN
		if(this.imagenAct!=null && cont1 ==0) {
			if(esImagenRGB()) {
				 if(JOptionPane.showConfirmDialog(this, "La imagen sera transformada a escala de grises")==0) {
					 imgActRGB = (Imagen) this.control.ejecutaComando(Comandos.ECUALIZARGB, this.imgActRGB,null );
					 img.setIcon(imgActRGB.convertirMatAImg());
					 this.repaint();
					cont1++;
				 }
			}else {
				imgActRGB = (Imagen) this.control.ejecutaComando(Comandos.ECUALIZARGB, this.imgActRGB, null);
				img.setIcon(imgActRGB.convertirMatAImg());
				this.repaint();
				cont1++;
			}
		}else if(cont1 !=0){
			imgActRGB = (Imagen) this.control.ejecutaComando(Comandos.ECUALIZAGRIS, this.imgActRGB, null);
			img.setIcon(imgActRGB.convertirMatAImg());
			this.repaint();
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen primero");
		}
		
		
		break;
		
	case Comandos.HISTA://MOSTRAR HISTOGRAMA ANTERIOR
		if( imgActRGB != null) {
			Imagen img = (Imagen) this.control.ejecutaComando(Comandos.HISTA, null, null);
			JOptionPane.showMessageDialog(this, new JLabel(img.convertirMatAImg()));
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		break;			
		
	case Comandos.HISTN://MOSTRAR HISTOGRAMA NUEVO
		if(cont1 >0) {
			Imagen img1 = (Imagen) this.control.ejecutaComando(Comandos.HISTN, imgActRGB, null);
			JOptionPane.showMessageDialog(this, new JLabel(img1.convertirMatAImg()));
		}else {
			JOptionPane.showMessageDialog(this, "Ecualiza la imagen primero");
		}
		break;			
		
		
	case Comandos.INVB://INVERSION BINARIA
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.INVB, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
			repaint();			
		}else {
			JOptionPane.showMessageDialog(this,"Selecciona una imagen");
		}
		break;
		
	case Comandos.INVF://INVERSION FOTOGRAFICA
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.INVF, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
			repaint();			
		}else {
			JOptionPane.showMessageDialog(this,"Selecciona una imagen");
		}
		break;
		
	case Comandos.BUSCARSUS:
		imagen.showOpenDialog(this);
		segundaAux = new Imagen(imagen.getSelectedFile().getPath());
		segundaImg.setText(imagen.getSelectedFile().getName());
		break;
		
	case Comandos.ADD://ADICION
		if(imgActRGB != null && segundaImg!=null) {
			imgActRGB = (Imagen) this.control.ejecutaComando(Comandos.ADD, imgActRGB, segundaAux);	
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Elige las imagenes a sumar");
		}
		repaint();
		break;
		
	case Comandos.SUS://SUSTRACION
		if(imgActRGB != null && segundaImg!=null) {
			imgActRGB = (Imagen) this.control.ejecutaComando(Comandos.SUS, imgActRGB, segundaAux);		
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Elige las imagenes a sustraer");
		}
		repaint();
		break;
		
	case Comandos.GCOLLAGE://GENERAR EL COLLAGE
		int x =0, y =0;
		if(c.getText().compareTo("")!=0 && r.getText().compareTo("")!=0) {
			x = Integer.parseInt(c.getText());
			y = Integer.parseInt(r.getText());			
		}
		
		if(!imagenesCollage.isEmpty() && x!=0 && y!= 0) {
			imgActRGB  = this.control.collage(imagenesCollage, x, y);
			this.img.setIcon(imgActRGB.convertirMatAImg());
			repaint();
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona por lo menos una imagen y coloca el tamano");
		}
		break;
		
	case Comandos.ACOLLAGE://AGREGAR UNA IMAGEN AL COLLAGE
		if(conti <9) {
			imagen.showOpenDialog(this);
			imagenesCollage.add(new Imagen(imagen.getSelectedFile().getPath()));
			imgAgregadaA = new JLabel(imagen.getSelectedFile().getName());
			
			rest.gridx = 0;
			rest.gridy = conti;
			rest.gridwidth = 2;
			rest.gridheight = 1;
			imgCollage.add(imgAgregadaA, rest);
			conti++;
		}else {
			JOptionPane.showMessageDialog(this, "Maximo 8 imagenes por collage");
		}
		repaint();
		break;
		
	case Comandos.REGENERAR:
		int x1 =0, y1 =0;
		if(c.getText().compareTo("")!=0 && r.getText().compareTo("")!=0) {
			x1 = Integer.parseInt(c.getText());
			y1 = Integer.parseInt(r.getText());			
		}
		
		if(!imagenesCollage.isEmpty() && x1!=0 && y1!= 0) {
			imgActRGB  = this.control.collage(imagenesCollage, x1, y1);
			this.img.setIcon(imgActRGB.convertirMatAImg());
			repaint();
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona por lo menos una imagen y coloca el tamano");
		}
		//RESETEAR PANEL Y ARREGLO DE IMAGENES AL FINAL O AGREGAR BOTON DE LIMPIAR Y REGENERAR EL COLLAGE
		break;
		
	case Comandos.RESET:
		for(int i =imagenesCollage.size(); i>0;i--) {
			imgCollage.remove(i);
		}
		imagenesCollage = new ArrayList<Imagen>();
		repaint();
		break;
		
	case Comandos.ROTARIZQUIERDA:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ROTARIZQUIERDA, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.ROTARDERECHA:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ROTARDERECHA, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.ESPEJO:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ESPEJO, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.MEDIA:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.MEDIA, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.MEDIANA:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.MEDIANA, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.MODA:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.MODA, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.GAUSS:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.GAUSS, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N1:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N1, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N2:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N2, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N3:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N3, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N4:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N4, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N5:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N5, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N6:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N6, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N7:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N7, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N8:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N8, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.N9:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.N9, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.LP4:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.LP4, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.LP8:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.LP8, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.PRE:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.PRE, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.SOBEL:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.SOBEL, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.ROBERT:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.ROBERT, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.EROSION:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.EROSION, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		
	case Comandos.DILATACION:
		if(imgActRGB!=null) {
			imgActRGB = (Imagen)this.control.ejecutaComando(Comandos.DILATACION, imgActRGB, null);
			this.img.setIcon(imgActRGB.convertirMatAImg());
		}else {
			JOptionPane.showMessageDialog(this, "Selecciona una imagen");
		}
		repaint();
		break;
		}//FIN SWITCH
	}//FIN ACTION

public boolean esImagenRGB() {
    int ancho = imagenAct.getWidth();
    int alto = imagenAct.getHeight();

    for (int y = 0; y < alto; y++) {
        for (int x = 0; x < ancho; x++) {
            int color = imagenAct.getRGB(x, y);
            Color c = new Color(color);
            if (c.getRed() != c.getGreen() || c.getRed() != c.getBlue() || c.getGreen() != c.getBlue()) {
                return true; // Se encontrÃ³ al menos un pÃ­xel con componente de color diferente
            }
        }
    }

    return false; // Todos los pÃ­xeles tienen las mismas componentes de color (escala de grises)
}//FIN ES RGB

public VentanaG getVentanaGuar() {
	return ventanaGuar;
}

public void setVentanaGuar(VentanaG ventanaGuar) {
	this.ventanaGuar = ventanaGuar;
}


	
}//FIN CLASE VENTANA
