package tid.principal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MostrarImagenBMP {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame ventana = new JFrame("Mostrar Imagen BMP");
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                BufferedImage imagen = ImageIO.read(new File("C:\\Users\\ulise\\Downloads\\rayos-X.jpeg"));
                ImageIcon imagenIcono = new ImageIcon(imagen);
                JLabel etiquetaImagen = new JLabel(imagenIcono);

                ventana.getContentPane().add(etiquetaImagen, BorderLayout.CENTER);

                ventana.pack();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}