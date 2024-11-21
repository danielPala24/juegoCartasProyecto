/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import javax.swing.JTextArea;

/**
 *
 * @author PALA
 */
public class PlayerCommand implements iCommand {
    private static final String COMMAND_NAME = "player";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void execute(String[] args, JTextArea console) {
        if (args.length < 1) {
            console.append("\nError: Parámetros insuficientes.");
            return;
        }

        String operation = args[0].toUpperCase();

        switch (operation) {
            case "-SURRENDER":
                playerSurrender(console);
                break;
            case "-RECHARGE":
                abilitiesRecharge(console);
                break;
            case "-WILDCARD":
                activateWildCard(console);
                break;
            case "-SELECT":
                if (args.length < 2) {
                    console.append("\nError: Debes proporcionar el nombre del contrincante.");
                } else {
                    loadPlayerInfo(args[1], console);
                }
                break;
            case "-PASS":
                passTurn(console);
                break;
            default:
                console.append("\nError: Operación no reconocida en 'player'.");
                break;
        }
    }

    private void playerSurrender(JTextArea console) {
        // Implementación del método para rendirse
        console.append("\nEl jugador se ha rendido.");
    }

    private void abilitiesRecharge(JTextArea console) {
        // Implementación del método para recargar habilidades
        console.append("\nHabilidades del jugador recargadas.");
    }

    private void activateWildCard(JTextArea console) {
        // Implementación del método para activar wildcard
        console.append("\nWildcard activada.");
    }

    private void loadPlayerInfo(String opponentName, JTextArea console) {
        // Implementación del método para cargar información del jugador
        console.append("\nCargando información del jugador: " + opponentName);
    }

    private void passTurn(JTextArea console) {
        // Implementación del método para pasar turno
        console.append("\nTurno pasado.");
    }
    
}
