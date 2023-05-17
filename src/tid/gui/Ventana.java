package tid.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
	JPanel panel;
	JLabel autor, img;
	JButton bBuscar, bAtras, bDelante, bGuardar;
	JTabbedPane menu;
	//PANELES DEL MENU
	JPanel oBas, collage, rotacion, filtros, morfo, segmentacion;
	
	
	public Ventana() {
		super("Tratamiento de imágenes");
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		//CREAR E INSERTAR COMPONENTES
		//CREACION DEL OBJETO DE RESTRICCIONES
		GridBagConstraints rest = new GridBagConstraints();
		
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
		
		//CREACION Y ANAÑIR LOS PANELES DEL MENU
		oBas = new JPanel();
		collage = new JPanel();
		rotacion = new JPanel();
		filtros = new JPanel();
		morfo = new JPanel();
		segmentacion = new JPanel();
		//FALTA AGREGAR LO QUE LLEVA CADA PANEL
		
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
	case "":
		
		break;		
		}//FIN SWITCH
	}//FIN ACTION
	
}//FIN CLASE VENTANA
