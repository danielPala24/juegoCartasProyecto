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
public class MensajeTurno extends Mensaje implements Serializable{
    String turnoActual;
    
    public MensajeTurno(String turno, String sender) {
        super(TipoMensaje.TURNO, sender);
        this.turnoActual = turno;
    }

    public String getTurnoActual() {
        return turnoActual;
    }
    
    
    
    
}
