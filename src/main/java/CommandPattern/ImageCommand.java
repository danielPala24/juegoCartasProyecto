/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import Utilidades.getRelativePath;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author PALA
 */
public class ImageCommand implements iCommand {
    private static final String COMMAND_NAME = "img";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void execute(String[] args, JTextArea console) {
        if(args.length<2){
            console.append("\nError: Parametros insuficientes.");
            return;
        }
        
        String operation = args[0].toUpperCase();
        console.append(operation);
        
        switch(operation) {
            case "-GALLERY":
                //openGallery(args,console);
                break;
            case "-SHOW":
                showImage(args,console);
                break;
            default:
                console.append("\nError: Operacion no reconocida en 'img'.");
                break;
        }
    }
    
    private void showImage(String[] args, JTextArea console) {
        try {
            if (args.length < 2) {
                console.append("\nError: Debes proporcionar el número de la imagen.");
                return;
            }

            // Ruta base de las imágenes
            String folderPath = new getRelativePath().getMainPath() + "\\resources\\Images\\";
            String imageNumber = args[1];

            // Construir la ruta de la imagen
            System.out.println(folderPath + imageNumber + ".jpg");
            File imageFile = new File(folderPath + imageNumber + ".jpg"); // Ajusta la extensión si es PNG u otro formato.

            if (!imageFile.exists()) {
                console.append("\nError: La imagen no existe en la ruta especificada.");
                return;
            }

            // Cargar la imagen
            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image scaledImage = imageIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            // Crear el frame para mostrar la imagen
            JFrame frame = new JFrame("Imagen: " + imageNumber);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 600);

            JLabel label = new JLabel(imageIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            frame.add(label);
            frame.setVisible(true);

            console.append("\nImagen mostrada: " + imageNumber);
        } catch (Exception e) {
            console.append("\nError al mostrar la imagen: " + e.getMessage());
        }
    }
    
}
