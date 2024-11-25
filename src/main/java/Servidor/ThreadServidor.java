/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;


import Enum.TipoChat;
import Modelos.Mensaje;
import Enum.TipoMensaje;
import static Enum.TipoMensaje.NCLIENTE;
import Jugador.Player;
import Modelos.MensajeChat;
import Modelos.MensajeInicioFin;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ian
 */
public class ThreadServidor extends Thread{
    public Socket socket;
    private Servidor server;
    ObjectInputStream entrada;
    DataInputStream entradaDatos;
    ObjectOutputStream salida;
    String nombre;
    Player jugador;
    Player jugadorSeleccionado;
    
    
    private boolean isRunning = true;

    public ThreadServidor(Socket socket, Servidor server) {
        this.socket = socket;
        this.server = server;
        try {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            
        }
    }

    @Override
    public void run() {
        Mensaje mensaje;
        try {
            mensaje = (Mensaje) entrada.readObject();
            nombre = mensaje.getSender();
            jugador = server.partida.addNPlayer(nombre);
            
            server.pantalla.write("Recibido nombre: " + nombre);
            Mensaje msg = new Mensaje(TipoMensaje.NCLIENTE,nombre);
            msg.jugadores = server.partida.getPlayers();
            server.broadcoastPublic(msg);

        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (isRunning){
            try {
                mensaje = (Mensaje) entrada.readObject();
                manejarSwitch(mensaje);
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
            case INICIO:
                MensajeInicioFin msg = (MensajeInicioFin) mensaje;
                manejarInicio(msg);
                break;
            case TURNO:
                //server.updateTurno
                break;
            case FIN:
                manejarFin();
                break;
            case CHAT:
                manejarMensajeChat(mensaje);
                break;
            case ATACAR:
                manejarAtacar(mensaje);
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
            case UPDATEJUGADORES:
                manejarUpdate(mensaje);
                break;
            default:
                break;
        }
    }
    
    private void manejarUpdate(Mensaje mensaje){
        server.partida.players = mensaje.jugadores;
    }
    
    private void manejarFin() throws IOException{
        if (jugador.isOwner && server.partida.isGameInProgress())
            server.broadcoastPublic(new Mensaje(TipoMensaje.FIN,nombre));
    }
    
    private void manejarInicio(MensajeInicioFin mensaje){
        if (jugador.isOwner && !server.partida.isGameInProgress()){
            server.iniciarPartida();
        }
    }
    
    private void manejarAtacar(Mensaje mensaje){
        
    }
    
    
    private void manejarNCliente(Mensaje mensaje) throws IOException{
        server.senderNCLIENTE(mensaje);
    }
    
    
    private void manejarMensajeChat(Mensaje mensaje) throws IOException{
        if (((MensajeChat)mensaje).getTipoChat() == TipoChat.PRIVADO){
            server.broadcoastPrivate(mensaje, ((MensajeChat) mensaje).getReceiver());
            salida.writeObject(mensaje);
            server.pantalla.write(mensaje.toString());
        }
        else{
            server.broadcoastPublic(mensaje);
            server.pantalla.write(mensaje.toString());
        }
    }
}
