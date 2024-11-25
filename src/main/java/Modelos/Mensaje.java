/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Enum.TipoMensaje;
import Jugador.Player;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ian
 */
public class Mensaje implements Serializable{
    String sender;
    String receiver;
    TipoMensaje tipo;
    public ArrayList<Player> jugadores;
    public Player jugador;

    public Mensaje(TipoMensaje tipo, String sender) {
        this.sender = sender;
        this.tipo = tipo;
    }

    public Mensaje(TipoMensaje tipo, String sender, String reiver ) {
        this.sender = sender;
        this.receiver = reiver;
        this.tipo = tipo;
    }
    
    
    

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String reiver) {
        this.receiver = reiver;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }
    
    
    
}
