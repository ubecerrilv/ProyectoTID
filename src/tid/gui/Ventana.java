package tid.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
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
	JButton btnEcz, btnEcA, btnEcN, btnInvB, btnInvF, btnAdd, btnSus;
	JLabel dC, dR, imgRes;
	JTextArea c1, c2, r1, r2;
	
	
	public Ventana() {
		super("Tratamiento de imágenes");
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//CREACION DEL OBJETO DE RESTRICCIONES
		GridBagConstraints rest = new GridBagConstraints();
		
		//CREACION DE LOS ELEMENTOS DE LOS PANELES PRINCIPALES
		//CREACION Y ANAÑIR LOS PANELES DEL MENU
			oBas = new JPanel(new GridLayout(3,1));
			collage = new JPanel();
			rotacion = new JPanel();
			filtros = new JPanel();
			morfo = new JPanel();
			segmentacion = new JPanel();
			
			//FALTA AGREGAR LO QUE LLEVA CADA PANEL
			ec = new JPanel();
			ec.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Ecualización",TitledBorder.CENTER,TitledBorder.TOP));
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
			inv.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Inversión",TitledBorder.CENTER,TitledBorder.TOP));
			inv.setLayout(new GridBagLayout());
			btnInvB = new JButton("Inversión binaria");
			btnInvB.setActionCommand(Comandos.INVB);
			btnInvB.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			inv.add(btnInvB, rest);//INVERSION BINARIA AGREGADO
			
			btnInvF = new JButton("Inversión fotográfica");
			btnInvF.setActionCommand(Comandos.INVF);
			btnInvF.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			inv.add(btnInvF, rest);//INVERSION FOTOGRAFICA AGREGADO
			
			ad = new JPanel();
			ad.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Adición y sustracción",TitledBorder.CENTER,TitledBorder.TOP));
			ad.setLayout(new GridBagLayout());
			c1 = new JTextArea();
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rest.fill=GridBagConstraints.HORIZONTAL;
			rest.weightx=1.0;
			
			ad.add(c1, rest);//C1 AGREGADO
			
			c2 = new JTextArea();
			rest.gridx = 2;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(c2, rest);//C2 AGREGADO
			
			r1 = new JTextArea();
			rest.gridx = 0;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(r1, rest);//R1 AGREGADO
			
			r2 = new JTextArea();
			rest.gridx = 2;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(r2, rest);//R2 AGREGADO
			rest.fill=GridBagConstraints.CENTER;
			
			dC = new JLabel("<- de la columna a la columna->");
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(dC, rest);//DC AGREGADO
			
			dR = new JLabel("<- del renglón al renglón->");
			rest.gridx = 1;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			
			ad.add(dR, rest);//R2 AGREGADO
			
			btnAdd = new JButton("Adicionar");
			btnAdd.setActionCommand(Comandos.ADD);
			btnAdd.addActionListener(this);
			rest.gridx = 0;
			rest.gridy = 2;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rest.fill=GridBagConstraints.EAST;
			
			ad.add(btnAdd, rest);//ADD AGREGADO
			
			btnSus = new JButton("Sustraer");
			btnSus.setActionCommand(Comandos.ADD);
			btnSus.addActionListener(this);
			rest.gridx = 2;
			rest.gridy = 2;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			rest.fill=GridBagConstraints.WEST;
			
			ad.add(btnSus, rest);//SUS AGREGADO
			rest.fill=GridBagConstraints.CENTER;
			
			imgRes = new JLabel();
			rest.gridx = 0;
			rest.gridy = 3;
			rest.gridwidth = 3;
			rest.gridheight = 1;
			
			ad.add(imgRes, rest);//RES AGREGADO
			
			rest.weightx=0;
			
			
			oBas.add(ec);
			oBas.add(inv);
			oBas.add(ad);
			
				
		
		
		//CREAR E INSERTAR COMPONENTES
		
		//CREAR FILE CHOOSER
		imagen = new JFileChooser();
		imagen.setFileFilter(new FileNameExtensionFilter ("png","PNG", "jpg", "JPG", "tiff", "TIFF", "jpeg", "JPEG", "bmp", "BMP", "ppm", "PPM"));
		
		//CREAR PANELES
		//PANEL CENTRAL
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));

		//CREAR ETIQUETAS
		autor = new JLabel("<html>Realizado por:<br><ul><li>Ulises Becerril Valdés</li> <li>Marcos Daniel Gómez Velázquez</li><li>Raúl Salazar Godínez</li></ul></html>");
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
		
		
		bAtras = new JButton("<--");
		bAtras.setActionCommand(tid.controlador.Comandos.ATRAS);
		bAtras.addActionListener(this);
		
		rest.gridx = 0;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bAtras, rest);
		
		
		bDelante = new JButton("-->");
		bDelante.setActionCommand(tid.controlador.Comandos.ADELANTE);
		bDelante.addActionListener(this);
		
		rest.gridx = 1;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bDelante, rest);
		
		
		bGuardar = new JButton("Guardar imagen");
		bGuardar.setActionCommand(tid.controlador.Comandos.ADELANTE);
		bGuardar.addActionListener(this);
		
		rest.gridx = 2;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bGuardar, rest);
		rest.weightx =0;
		
		//CREAR, AGREGAR LOS ELEMENTOS Y AGREGAR MENU
		menu = new JTabbedPane();
		menu.addTab("Básicos", oBas);
		menu.addTab("Collage", collage);
		menu.addTab("Rotación", rotacion);
		menu.addTab("Filtros", filtros);
		menu.addTab("Op. morfológicas", morfo);
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
		ImageIcon imagenR = new ImageIcon(imagen.getSelectedFile().getPath());
		Icon icono = new ImageIcon(imagenR.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_DEFAULT));
		
		img.setIcon(icono);
		imgRes.setText("Resolución de la imagen: "+imagenR.getIconWidth()+"x"+imagenR.getIconHeight());
		
		this.repaint();
		
		break;		
	
	case Comandos.ATRAS: //REGRESAR UN PASO
		break;
		
	case Comandos.ADELANTE:// ADELANTAR UN PASO
		break;
		
	case Comandos.GUARDA://GUARDAR IMAGEN
		break;
		
	case Comandos.ECUALIZAR://ECUALIZAR IMAGEN
		break;
		
	case Comandos.HISTA://MOSTRAR HISTOGRAMA ANTERIOR
		break;
		
	case Comandos.HISTN://MOSTRAR HISTOGRAMA NUEVO
		break;
		
	case Comandos.INVB://INVERSION BINARIA
		break;
		
	case Comandos.INVF://INVERSION FOTOGRAFICA
		break;
		
	case Comandos.ADD://ADICION
		break;
		
	case Comandos.SUS://SUSTRACION
		break;
		}//FIN SWITCH
	}//FIN ACTION
	
}//FIN CLASE VENTANA
