/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import Cliente.Cliente;
import GameManager.GameManager;
import Jugador.*;
import StrategyPattern.*;
import Utilidades.RedimensionarImagen;
import Utilidades.getRelativePath;
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
    private Cliente cliente;
    
    
    public CardCommand (Cliente cliente){
        this.cliente = cliente;
    }

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
        loadImagesFromFolder(new getRelativePath().getMainPath() + "\\resources\\Images\\");
        
        File selectedImageFile = imageFiles.get(imageNum);
        ImageIcon cardImage = RedimensionarImagen.redimensionarImagen(new ImageIcon(selectedImageFile.getPath()),140,195);
        // ahora creamos la carta
        Card newCard = new Card(cardImage, cardName, cardType);
        cliente.manager.getJugador().addCard(newCard);
        cliente.manager.updateCards();
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
        if(args.length < 5){
            console.append("\nError: Formato correcto: -ATTACK [nombreContrincante] [nombreCartaAUsar] [nombreHabilidadAUsar] [estrategiaAUsar]\n");
            return;
        }
        
        String targetName = args[1];
        String cardName = args[2];
        String abilityName = args[3];
        String strategyName = args[4];
        
        try { 
            // obtenemos el jugador actual
            Player currentPlayer = GameManager.getInstance().getCurrentPlayer();
            
            // obtenemos el jugador objetivo
            Player targetPlayer = cliente.manager.getPlayerByName(targetName);
            if(targetPlayer == null){
                console.append("\nError: Jugador objetivo no encontrado.\n");
                return;
            }
            
            // carta seleccionada
            Card selectedCard = currentPlayer.getCardByName(cardName);
            if(selectedCard == null){
                console.append("\nError: Carta '" + cardName + "' no encontrada en tu mazo.\n");
                return;
            }
            
            // ahora buscamos la habilidad seleccionada
            Ability selectedAbility = null;
            for(Ability ability : selectedCard.getCardAbilities()) {
                if(ability.getAbilityName().equals(abilityName)) {
                    selectedAbility = ability;
                    break;
                }
            }
            if(selectedAbility == null) {
                console.append("\nError: Habilidad '" + abilityName + "' no encontrada en la carta o ya fue utilizada.\n");
                return;
            }

            ArrayList<Integer> damage;
            
            // Verificar si se usa una estrategia o no
            if(strategyName.equalsIgnoreCase("none")) {
                // Ataque sin estrategia - usar el daño base de la habilidad
                damage = new ArrayList<>(selectedAbility.getDamagePerType());
            } else {
                // Configurar la estrategia según el nombre
                iAttackStrategy strategy;
                switch(strategyName.toLowerCase()) {
                    case "randomduplex":
                        strategy = new RandomDuplexStrategy();
                        break;
                    case "randomcombination":
                        strategy = new RandomCombinationStrategy(currentPlayer.getPlayerCards());
                        break;
                    case "bestcombination":
                        strategy = new BestCombinationStrategy(currentPlayer.getPlayerCards());
                        break;
                    case "average":
                        strategy = new AverageStrategy(cliente.manager.getJugadores());
                        break;
                    case "optimal":
                        strategy = new OptimalStrategy(cliente.manager.getJugadores());
                        break;
                    default:
                        console.append("\nError: Estrategia no reconocida. Opciones válidas: randomduplex, randomcombination, bestcombination, none\n");
                        return;
                }

                // Ejecutar el ataque con la estrategia
                selectedCard.setAttackStrategy(strategy);
                damage = selectedCard.executeAttack(selectedAbility); // No tengo mucha idea de como implementar esto
            }

            // Aplicar el daño al objetivo
            applyDamage(targetPlayer, damage, console);
            
            // Registrar la habilidad como usada
            selectedCard.getCardUsedAbilities().add(selectedAbility);
            
            // Mostrar resultado del ataque
            console.append("\nAtaque realizado con " + cardName + " usando " + abilityName + 
                         (strategyName.equalsIgnoreCase("none") ? "" : " y estrategia " + strategyName));
            console.append("\nDaño realizado: " + damage.toString());

        } catch (Exception e) {
            console.append("\nError durante el ataque: " + e.getMessage());
        }
    }
    
    private void applyDamage(Player targetPlayer, ArrayList<Integer> damage, JTextArea console) {
        // Aqui se podria hacer como tal la aplicacion del daño viendo el tipo de cada carta del contrincante
       // asi entonces si el contrincante tiene cartas tipo (se que no son nuestros tipos pero es por ejemplo) fuego, tierra, aire fuego
       // entonces si ahi se iria pasando por cada monstruo dandole el daño correspondiente
        
    }
    
}
