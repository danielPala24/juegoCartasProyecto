/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Enum.TipoMensaje;
import java.io.Serializable;

/**
 *
 * @author Ian
 */
public class MensajeSalidaMutua extends Mensaje implements Serializable{
    
    public MensajeSalidaMutua(String sender, String receiver) {
        super(TipoMensaje.SALIDAMUTUA, sender, receiver);
    }
    
}
