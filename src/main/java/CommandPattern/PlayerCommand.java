/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import Cliente.Cliente;
import Enum.TipoMensaje;
import Jugador.Player;
import Modelos.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author PALA
 */
public class PlayerCommand implements iCommand {
    private static final String COMMAND_NAME = "player";
    Cliente cliente;
    
    
    public PlayerCommand(Cliente cliente){
        this.cliente = cliente;
    }
    
    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void execute(String[] args, JTextArea console) {
        if (args.length < 1) {
            console.append("\nError: Parámetros insuficientes.\n");
            return;
        }

        String operation = args[0].toUpperCase();

        switch (operation) {
            case "-SURRENDER":
                playerSurrender(console);
                break;
            case "-Start":
                playerStart();
                break;
            case "-RECHARGE":
                abilitiesRecharge(console);
                break;
            case "-WILDCARD":
                activateWildCard(console);
                break;
            case "-SELECTCARD":
                if (args.length < 2) {
                    console.append("\nError: Debes proporcionar el nombre de la carta.\n");
                } else {
                    loadCardInfo(args[1], console);
                }
                break;
            case "-SELECT":
                if (args.length < 2) {
                    console.append("\nError: Debes proporcionar el nombre del contrincante.\n");
                } else {
                    loadPlayerInfo(args[1], console);
                }
                break;
            case "-PASS":
                passTurn(console);
                break;
            default:
                console.append("\nError: Operación no reconocida en 'player'.\n");
                break;
        }
    }
    
    private void playerStart(){
        try {
            cliente.broadcoast(new MensajeInicioFin(false, cliente.getNombre()));
        } catch (IOException ex) {
            Logger.getLogger(PlayerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void playerSurrender(JTextArea console) {
        try {
            cliente.broadcoast(new Mensaje(TipoMensaje.RENDIRSE,cliente.getNombre()));
            console.append("\nEl jugador se ha rendido.\n");
        } catch (IOException ex) {
            Logger.getLogger(PlayerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void abilitiesRecharge(JTextArea console) {
        try {
            cliente.broadcoast(new Mensaje(TipoMensaje.RECARGAARMAS,cliente.getNombre()));
            console.append("\nHabilidades del jugador recargadas.\n");
        } catch (IOException ex) {
            Logger.getLogger(PlayerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void activateWildCard(JTextArea console) {
        try {
            cliente.broadcoast(new Mensaje(TipoMensaje.USARCOMODIN,cliente.getNombre()));
            console.append("\nWildcard activada.\n");
        } catch (IOException ex) {
            Logger.getLogger(PlayerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadCardInfo(String card, JTextArea console){
        cliente.manager.updateCardSelected(card);
        console.append("\nCargando información de la carta: " + card);
        
    }
    private void loadPlayerInfo(String opponentName, JTextArea console) {
        // Implementación del método para cargar información del jugador
        Player ref = cliente.manager.getPlayerByName(opponentName);
        cliente.manager.updateStats(ref);
        console.append("\nCargando información del jugador: " + opponentName + "\n");
    }

    private void passTurn(JTextArea console) {
        try {
            cliente.broadcoast(new MensajePasarTurno(cliente.getNombre()));
            console.append("\nHas pasado el turno.\n");
        } catch (IOException ex) {
            Logger.getLogger(PlayerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
