package tid.controlador;


import java.util.ArrayList;

import tid.gui.Ventana;
import tid.modelo.Data;
import tid.modelo.Imagen;

public class ControlPrincipal extends ControlAbs{
	
/*
 * Esta clase es creada como un control de las ventanas, dirige cuando mostrase u ocultarse
 */
	
/**********************************************************************************************************************************************
 * 
 * 																ATRIBUTOS
 * 
 *********************************************************************************************************************************************/
	
	private Ventana vent;

	
				
/**********************************************************************************************************************************************
 * 
 * 																M�TODOS
 * 
 *********************************************************************************************************************************************/

	public ControlPrincipal(ControlVPrincipal CVP, Ventana venta) {
		this.vent = venta;
	}
	
	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {
		switch(c) {
		case Comandos.INICIA:
			vent.setBounds(0, 0, 800, 600);
			vent.setLocationRelativeTo(null);
			vent.setVisible(true);
			
		break;	
		}
		return null;	
		
	}

	@Override
	public Imagen collage(ArrayList<Imagen> imagenes, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Imagen erosion(Imagen i, int[][] mat, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Imagen dilatacion(Imagen i, int[][] mat, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}//FIN CLASE
