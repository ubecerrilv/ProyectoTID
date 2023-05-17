package gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
	JPanel panel;
	JLabel autor, img;
	JButton bBuscar, bAtras, bDelante, bGuardar;
	
	
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
		panel.setBorder(new EmptyBorder(5,5,5,5));

		//CREAR ETIQUETAS
		autor = new JLabel("<html>Realizado por Ulises Becerril Valdés <br> Marcos Daniel Gómez Velázquez</html>");
		rest.gridx = 3;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(autor, rest);

			
		//CREAR BOTON DE MAPEAR
		bBuscar = new JButton("Seleccionar imagen");
		bBuscar.setActionCommand(controlador.Comandos.BUSCA);
		bBuscar.addActionListener(this);
		
		rest.gridx = 0;
		rest.gridy = 0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		
		panel.add(autor, rest);
		
		
		//AGREGAR LOS PANELES A LA VENTANA
		this.add(panel);
		
	
		this.setResizable(false);
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
