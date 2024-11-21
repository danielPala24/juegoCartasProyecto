/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import Jugador.Card;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author PALA
 */
public class CardCommand implements iCommand  {
    private ArrayList<File> imageFiles;
    private static final String COMMAND_NAME = "card";

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
            case "-CREATE":
                createCard(args,console);
                break;
            case "-ATTACK":
                handleAttack(args,console);
                break;
            default:
                console.append("\nError: Operacion no reconocida en 'card'.");
                break;
        }
    }
    
    private void createCard(String[] args, JTextArea console) {
        if(args.length<3){
            console.append("\nError: Parametros insuficientes.");
            return;
        }
        
        String cardName = args[1];
        String cardType = args[2];
        int imageNum = Integer.parseInt(args[3]);
        
        // configuramos la imagen de la carta correctamente
        loadImagesFromFolder("/C://Users//PALA//Documents//GitHubRepos//juegoCartasProyecto//src//main//resources//Images/");
        
        File selectedImageFile = imageFiles.get(imageNum);
        ImageIcon cardImage = new ImageIcon(selectedImageFile.getPath());
        // ahora creamos la carta
        Card newCard = new Card(cardImage, cardName, cardType);
        
        console.append("\nCarta creada: " + newCard.toString());
    }
    
    
    
    
    // metodo adicional para cargar correctamente la imagen
    private void loadImagesFromFolder(String folderPath) {
        imageFiles = new ArrayList<>();
        File folder = new File(folderPath);
        
        // Filter for image files
        File[] files = folder.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".png") || 
            name.toLowerCase().endsWith(".jpg") || 
            name.toLowerCase().endsWith(".jpeg") || 
            name.toLowerCase().endsWith(".gif")
        );

        if (files != null) {
            for (File file : files) {
                imageFiles.add(file);
            }
        }

        if (imageFiles.isEmpty()) {
            return;
        }
    }

    private void handleAttack(String[] args, JTextArea console) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
