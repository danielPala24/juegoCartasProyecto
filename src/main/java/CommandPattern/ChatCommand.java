/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import Cliente.Cliente;
import Enum.TipoChat;
import Modelos.MensajeChat;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author PALA
 */
public class ChatCommand implements iCommand {
    private static final String COMMAND_NAME = "chat";
    private Cliente cliente;
    
    public ChatCommand(Cliente cliente){
        this.cliente = cliente;
    }

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
            case "-PUBLIC":
                if (args.length < 2) {
                    console.append("\nError: Debes proporcionar un mensaje para ser enviado.");
                } else {
                    String message = String.join(" ", extractArgs(args, 1));
                    sendPublicMessage(message, console);
                }
                break;

            case "-PRIVATE":
                if (args.length < 3) {
                    console.append("\nError: Debes proporcionar un destinatario y un mensaje para ser enviado.");
                } else {
                    String recipient = args[1];
                    String privateMessage = String.join(" ", extractArgs(args, 2));
                    sendPrivateMessage(recipient, privateMessage, console);
                }
                break;

            default:
                console.append("\nError: Operación no reconocida en 'chat'.\n");
                break;
        }
    }

    private void sendPublicMessage(String message, JTextArea console) {
        try {
            // WIP
            cliente.broadcoast(new MensajeChat(TipoChat.PUBLICO, message, cliente.getNombre()));
        } catch (IOException ex) {
            Logger.getLogger(ChatCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendPrivateMessage(String recipient, String message, JTextArea console) {
        try {
            // WIP
            cliente.broadcoast(new MensajeChat(TipoChat.PRIVADO, message, cliente.getNombre(), recipient));
        } catch (IOException ex) {
            Logger.getLogger(ChatCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Extrae un subconjunto de argumentos desde un índice especificado.
     * 
     * @param args Array de argumentos completo.
     * @param start Índice de inicio desde donde extraer los argumentos.
     * @return Array de argumentos desde el índice especificado.
     * lo utilizamos para poder agarrar desde donde inicia el mensaje y unificarlo
     */
    private String[] extractArgs(String[] args, int start) {
        String[] result = new String[args.length - start];
        System.arraycopy(args, start, result, 0, result.length);
        return result;
    }
}