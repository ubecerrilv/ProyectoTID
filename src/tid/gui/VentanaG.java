package tid.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import tid.controlador.Comandos;

@SuppressWarnings("serial")
public class VentanaG extends VentanaAGeneral {
	private JPanel panel;
	private JLabel nombre, ruta, nombreExt;
	private JTextArea nombreR;
	private JComboBox<String> ext;
	private JButton eRuta, guardar;
	private String name, route, extention;
	private String[] extensiones = {"png", "jpg", "tif", "jpeg", "bmp", "ppm"};
	private JFileChooser carpeta;

	public VentanaG() {
		super("Guardar imagen");
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		carpeta = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		carpeta.setDialogTitle("Seleciona la ruta donde se guardar la imagen");
		carpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		GridBagConstraints rest = new GridBagConstraints();
		rest.fill = GridBagConstraints.HORIZONTAL;
		rest.weighty = 1.0;
		rest.weightx=1.0;
		//CREAR PANEL Y LAYOUT
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));
		
		nombre = new JLabel("NOMBRE DEL ARCHIVO:");
		
		rest.gridx = 0;
		rest.gridy = 0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		panel.add(nombre, rest);
		
		nombreR = new JTextArea("ImagenProcesada");
		rest.gridx = 2;
		rest.gridy = 0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		panel.add(nombreR, rest);
		
		nombreExt = new JLabel("Elige la extension del archivo");
		rest.gridx = 0;
		rest.gridy = 1;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		panel.add(nombreExt, rest);
		
		
		ext = new JComboBox<String>();
		for(int i = 0;i<extensiones.length;i++) {
			ext.addItem(extensiones[i]);
		}
		rest.gridx = 2;
		rest.gridy = 1;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		panel.add(ext, rest);
		
		ruta = new JLabel();
		rest.gridx = 0;
		rest.gridy = 2;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		panel.add(ruta, rest);
		
		eRuta = new JButton("Selecionar carpeta");
		eRuta.setActionCommand(Comandos.BUSCARRUTA);
		eRuta.addActionListener(this);
		rest.gridx = 2;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		panel.add(eRuta, rest);
		
		guardar = new JButton("Guardar imagen");
		guardar.setActionCommand(Comandos.GUARDARREALMENTE);
		guardar.addActionListener(this);
		rest.gridx = 1;
		rest.gridy = 3;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		panel.add(guardar, rest);
		
		this.add(panel);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}//FIN CONSTRUCTOR

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case Comandos.BUSCARRUTA:
			carpeta.showOpenDialog(this);
			route=carpeta.getSelectedFile().getPath();
			ruta.setText(route);
			this.repaint();
			break;
			
		case Comandos.GUARDARREALMENTE:
			if(route.compareTo("")!=0) {
				this.name=nombreR.getText();
				this.extention = (String)ext.getSelectedItem();
				this.control.ejecutaComando(Comandos.GUARDO, null, null);
			}else {
				JOptionPane.showMessageDialog(this, "Selecciona una ruta donde guardar el archivo");
			}
			break;
		}//FIN SWITCH
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public JTextArea getNombreR() {
		return nombreR;
	}

	public void setNombreR(JTextArea nombreR) {
		this.nombreR = nombreR;
	}
	
	
	

}
