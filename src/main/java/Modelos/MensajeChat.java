/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Enum.TipoChat;
import Enum.TipoMensaje;
import java.io.Serializable;

/**
 *
 * @author Ian
 */
public class MensajeChat extends Mensaje implements Serializable{
    TipoChat tipoChat;
    String mensaje;

    public MensajeChat(TipoChat tipoChat, String mensaje, String sender) {
        super(TipoMensaje.CHAT, sender);
        this.tipoChat = tipoChat;
        this.mensaje = mensaje;
    }

    public MensajeChat(TipoChat tipoChat, String mensaje, String sender, String reiver) {
        super(TipoMensaje.CHAT, sender, reiver);
        this.tipoChat = tipoChat;
        this.mensaje = mensaje;
    }

    public TipoChat getTipoChat() {
        return tipoChat;
    }

    public void setTipoChat(TipoChat tipoChat) {
        this.tipoChat = tipoChat;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        if (tipoChat == TipoChat.PRIVADO)
            return "[Privado] de " + getSender() + " para " + getReceiver() + ": " + mensaje.toString();
        return "[Publico] de " + getSender() +  ": " + mensaje.toString();
    }
    
    

    
    
    
}
