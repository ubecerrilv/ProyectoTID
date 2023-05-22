package tid.principal;

import org.opencv.core.Core;

import tid.controlador.Comandos;
import tid.controlador.ControlPrincipal;
import tid.controlador.ControlVPrincipal;
import tid.gui.Ventana;

public class Principal {

	public static void main(String[] args) {
		//VARIABLES NECESARIAS
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				ControlPrincipal CP; //CONTROL PRINCIPAL
				
				Ventana Vent;
				ControlVPrincipal CV; //VENTANA Y SU CONTROL
				
				//CREACION DE LOS OBJETOS
				CV = new ControlVPrincipal();
				Vent = new Ventana();
				
				CP = new ControlPrincipal(CV, Vent);//VENTANA Y CONTROL EN EL PRINCIPAL
				
				
				//CONTROL DE LA VENTANA
				Vent.setControl(CV);
				
				
				//INICIA EL PROGRAMA
				CP.ejecutaComando(Comandos.INICIA, null, null);
	}//FIN MAIN
}//FIN CLASE PRINCIPAL
