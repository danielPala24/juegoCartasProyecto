/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Jugador.Card;
import Jugador.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Ian
 */
public class VistaJugadorManager {
    Player jugador;
    Player jugadorSeleccionado;
    VistaJugador pantalla;
    ArrayList<Player> jugadores;
    String turno;
    boolean partidaComenzada;
    boolean isOwner;
    
    public VistaJugadorManager(VistaJugador pantalla){
        this.pantalla = pantalla;
    }
    
    public void updatePantallaData(){
        updateStats(jugador);
        if (jugadorSeleccionado!=null)
            updateStats(jugadorSeleccionado);
        updateRanking();
        pantalla.setBtnStar(isOwner);
    }
    
    
    public void updateAtack(){
        
    }
    
    public Player getPlayerByName(String name){
        for (Player jugador : jugadores) {
            if(jugador.getName().equals(name)){
                return jugador;
            }
        }
        return null;
    }
    
    public void updateCards(){
        for (int i = 0; i < jugador.getPlayerCards().size(); i++) {
            if (!jugador.getPlayerCards().isEmpty() && jugador.getPlayerCards().get(i) != null)
                pantalla.getLblCardsAsArray()[i].setIcon(jugador.getPlayerCards().get(i).getImage());
        }
    }
    
    public void updateCardSelected(String card){
        Card selected = jugador.getCardByName(card);
        if (selected!=null){
            JLabel[] labels = pantalla.getLblCardsHabilitiesAsArray();
            JButton [][] btn = pantalla.getDmgMatrix();
            for (int i = 0; i < 4; i++) {
                labels[i].setText(selected.getCardAbilities().get(i).getAbilityName());
                for (int j = 0; j < 10; j++) {
                    btn[i][j].setText(selected.getCardAbilities().get(i).getDamagePerType().get(j) + "");
                }
            }
            labels[5].setText(selected.getName());
        }
        
    }
    
    public void updateStats(Player player){
        if (player!=null)
            if (player.equals(jugador))
                pantalla.writeTxt_Status("My Status:" + player.toStatusString());
            else
                pantalla.writeTxt_Oponent("Against: " + player.toStatusString());
    }
    
    public void updateRanking(){
        pantalla.writeTxt_Ranking("");
        int index = 1;
        for (Player player : getRanking(jugadores)){
            pantalla.appentTxt_Ranking( index++ + ".\t" + player.toRankingString());
        }
    }
    
    public Player[] getRanking(ArrayList<Player> players) {
    // Ordenar la lista de jugadores según su relación victorias/derrotas
    Collections.sort(players, new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            double ratio1 = (p1.losses == 0) ? p1.wins : (double) p1.wins / p1.losses;
            double ratio2 = (p2.losses == 0) ? p2.wins : (double) p2.wins / p2.losses;
            // Orden descendente: primero los jugadores con mayor ratio
            return Double.compare(ratio2, ratio1);
        }
    });
    
    // Convertir el ArrayList a un Array
    return players.toArray(new Player[0]);
}
    
    
    public void updateJugadores(ArrayList<Player> jugadores){
        this.jugadores = jugadores;
    }

    public void setJugador(Player jugador) {
        setIsOwner(jugador.isOwner);
        this.jugador = jugador;
    }
    
    public void setJugadores(ArrayList<Player> jugadores){
        this.jugadores = jugadores;
        updatePantallaData();
    }

    public void setJugadorSeleccionado(Player jugadorSeleccionado) {
        this.jugadorSeleccionado = jugadorSeleccionado;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setPartidaComenzada(boolean partidaComenzada) {
        this.partidaComenzada = partidaComenzada;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Player getJugador() {
        return jugador;
    }

    public Player getJugadorSeleccionado() {
        return jugadorSeleccionado;
    }

    public ArrayList<Player> getJugadores() {
        return jugadores;
    }

    public String getTurno() {
        return turno;
    }

    public boolean isPartidaComenzada() {
        return partidaComenzada;
    }

    public boolean isIsOwner() {
        return isOwner;
    }
    
    
    
    
    
}
