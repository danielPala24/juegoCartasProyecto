/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Enum.TipoMensaje;
import Jugador.Player;
import Modelos.Mensaje;
import Modelos.MensajeInicioFin;
import Modelos.MensajeTurno;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private final int PORT = 8084;
    ServerSocket server;
    public PantallaServidor pantalla;
    ArrayList<ThreadServidor> clientesAceptados;
    ServerConnectionsThread conexionsThread;
    Partida partida;
    
    public Servidor(PantallaServidor pantalla){
        this.pantalla = pantalla;
        connect();
        clientesAceptados = new ArrayList<ThreadServidor>();
        conexionsThread = new ServerConnectionsThread(this);
        conexionsThread.start();
        partida = Partida.getInstance();
    }
    public void connect(){
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void senderNCLIENTE(Mensaje mensaje) throws IOException{
        broadcoastPublic(mensaje);
    }
    
    //----------------------------------------------------------------------------
    //-----------METODOS DE Manipulacion de Partida-------------------------------
    //----------------------------------------------------------------------------
    
    public void iniciarPartida(){
        partida.startGame();
        try {
            MensajeInicioFin msg = new MensajeInicioFin(false, "a");
            msg.jugadores = partida.getPlayers();
            broadcoastPublic(msg);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    //----------------------------------------------------------------------------
    //-----------METODOS DE BUSQUEDA DE DATOS DENTRO DE CLIENTES------------------
    //----------------------------------------------------------------------------
    private int getIndexCliente(String nombre){
        int index = 0;
        for (ThreadServidor cliente : clientesAceptados) {
            if (cliente.nombre.equals(nombre))
                return index;
            index ++;
        }
        return -1;
    }
   
    
    public void updateTurnoClientes(Player turnoActual) throws IOException{
        for (ThreadServidor cliente : clientesAceptados) {
            cliente.salida.writeObject(new MensajeTurno(turnoActual.getName(),cliente.nombre));
        }
    }
    
    
    public void imprimirMatriz(int [][] Jugador) {
        System.out.println("-------------------------------------------------Estado actual de la matriz jugador:");
        for (int i = 0; i < Jugador.length; i++) {
            for (int j = 0; j < Jugador[i].length; j++) {
                System.out.print(Jugador[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    //----------------------------------------------------------------------------
    //---------------------------------BROADCOAST---------------------------------
    //----------------------------------------------------------------------------
    
    public void broadcoastPublic(Mensaje mensaje) throws IOException{
        for (ThreadServidor cliente : clientesAceptados){
            System.out.println("ENVIADO a" + cliente.nombre + mensaje.jugadores);
            cliente.salida.writeObject(mensaje);
            cliente.salida.flush();
        }
    }
    
    public void broadcoastPrivate(Mensaje mensaje, String receiver){
        for (ThreadServidor cliente : clientesAceptados) {
            try {
                if(cliente.nombre.equals(receiver)){
                    cliente.salida.writeObject(mensaje);
                    return;
                }
            } catch (IOException ex) {
            
            }
        }
    }

    


}
