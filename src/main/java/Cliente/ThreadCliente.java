/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;


import static Enum.TipoMensaje.ATACAR;
import static Enum.TipoMensaje.CHAT;
import static Enum.TipoMensaje.FIN;
import static Enum.TipoMensaje.INICIO;
import static Enum.TipoMensaje.NCLIENTE;
import static Enum.TipoMensaje.PASARTURNO;
import static Enum.TipoMensaje.RECARGAARMAS;
import static Enum.TipoMensaje.RENDIRSE;
import static Enum.TipoMensaje.SALIDAMUTUA;
import static Enum.TipoMensaje.SELECCIONARJUGADOR;
import static Enum.TipoMensaje.TURNO;
import static Enum.TipoMensaje.USARCOMODIN;
import Jugador.Player;
import Modelos.Mensaje;
import Modelos.MensajeChat;
import Modelos.MensajeInicioFin;
import Modelos.MensajeTurno;
import Servidor.Servidor;
import Servidor.ThreadServidor;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ian
 */
public class ThreadCliente extends Thread{
    boolean isrunnig = true;
    private Socket socket;
    private Cliente cliente;
    private ObjectInputStream entrada;
    private boolean isRunning = true;
    

    public ThreadCliente(Socket socket, Cliente cliente) {
        try {
            this.socket = socket;
            this.cliente = cliente;
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            //Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        Mensaje mensaje;
        while (isRunning){
            try {
                System.out.println("Esperando Mensaje");
                mensaje = (Mensaje) entrada.readObject();
                System.out.println("Recibido Mensaje -- " + mensaje.getTipo().toString() + " --- de " + mensaje.getSender());
                System.out.println(mensaje.jugadores);
                manejarSwitch(mensaje);
                mensaje = null;
            } catch (IOException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void manejarSwitch(Mensaje mensaje) throws IOException{
        switch(mensaje.getTipo()){
            case NCLIENTE:
                manejarNCliente(mensaje);
                break;
            case TURNO:
                manejarTurno(mensaje);
                break;
            case INICIO:
                manejarInicio();
                break;
            case FIN:
                break;
            case CHAT:
                manejarChat(mensaje);
                break;
            case ATACAR:
                break;
            case RENDIRSE:
                break;
            case SALIDAMUTUA:
                break;
            case RECARGAARMAS:
                break;
            case USARCOMODIN:
                break;
            case SELECCIONARJUGADOR:
                break;
            case PASARTURNO:
                break;
            default:
                break;
        }
    }
    
    private void manejarChat(Mensaje mensaje){
        cliente.pantalla.appendToConsole(mensaje.toString());
    }
    
    private void manejarInicio(){
        cliente.manager.setPartidaComenzada(true);
    }
    
    
    
    private void manejarNCliente(Mensaje mensaje){
        for (Player jugador1 : mensaje.jugadores) {
            if (jugador1.getName().equals(cliente.nombre)){
                cliente.manager.setJugador(jugador1);
            }
        }
        cliente.manager.setJugadores(mensaje.jugadores);
    }
    
    private void manejarTurno(Mensaje mensaje){
        MensajeTurno msg = (MensajeTurno) mensaje;
        //managerPantalla.updateTurno(msg);
    }
    
}
