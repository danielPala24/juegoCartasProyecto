/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;


import Enum.TipoMensaje;
import GUI.VistaJugador;
import GUI.VistaJugadorManager;
import GameManager.GameManager;
import Jugador.Player;
import Modelos.Mensaje;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ian
 */
public class Cliente {
    private final String IP = "localhost";
    private final int PORT = 8084;
    private Socket socket;
    ObjectOutputStream salida;
    private DataOutputStream salidaDatos;
    ThreadCliente threadCliente;
    String nombre;
    VistaJugador pantalla;
    public VistaJugadorManager manager;

    public Cliente(VistaJugador pantalla) {
        this.pantalla = pantalla;
        conectar();
    }

    
    
    public void conectar(){
        try {
            socket = new Socket(IP, PORT);
            salida = new ObjectOutputStream(socket.getOutputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            threadCliente = new ThreadCliente(socket, this);
            threadCliente.start();
            this.nombre = JOptionPane.showInputDialog("Nombre: ");
            manager = new VistaJugadorManager(pantalla);
            Mensaje msg = new Mensaje(TipoMensaje.NCLIENTE,nombre);
            salida.writeObject(msg);
            
        } catch (IOException ex) {
            
        }
    }
    
    
    public void broadcoast(Mensaje mensaje) throws IOException{
        salida.writeObject(mensaje);
    }

    public ThreadCliente getThreadCliente() {
        return threadCliente;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
