/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import GameManager.GameManager;
import Jugador.Player;
import java.util.ArrayList;

/**
 *
 * @author Ian
 */
public class Partida {
    private static Partida instance;
    Servidor server;
    ArrayList<Player> players;
    Player currentTurn;
    int currentRound;
    private boolean gameInProgress;
    private int lastId = 0;
    
    private Partida(){
        players = new ArrayList<>();
        gameInProgress = false;
    }
    
    public static Partida getInstance(){
        if (instance==null)
            return instance = new Partida();
        return instance;
    }
    
    public Player addNPlayer(String nombre){
        System.out.println("Agregando jugador" + nombre);
        Player nPlayer = new Player(lastId++,nombre);
        if (players.size()==0)
            nPlayer.isOwner = true;
        players.add(nPlayer);
        return nPlayer;
    }

    public Servidor getServer() {
        return server;
    }

    public void setServer(Servidor server) {
        this.server = server;
    }

    public ArrayList<Player> getPlayers() {
        System.out.println("Enviando players" + players.toString());
        return new ArrayList<>(players);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }
     public int geCurrentRond(){
         return currentRound;
     }
     
     public void endGame() {
        gameInProgress = false;
        currentTurn = null;
        currentRound = 0;
    }
    
    // Método para reiniciar el GameManager (útil para pruebas o nuevo juego)
    public static void reset() {
        instance = new Partida();
    }
    
    // Métodos auxiliares para el estado del juego
    public boolean isPlayerTurn(Player player) {
        return gameInProgress && player.equals(player);
    }
     public boolean isGameInProgress() {
        return gameInProgress;
    }
    
    public Player getPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    public void addPlayer(Player player) {
        if (!gameInProgress) {
            players.add(player);
        }
    }
    
    
    public void startGame() {
        if (players.size() >= 2) {
            gameInProgress = true;
            currentTurn = players.get(0);
            currentRound = 1;
        } else {
            
        }
    }
    
    public void nextTurn() {
        if (!gameInProgress) {
            return;
        }
        // Encuentra el índice del jugador actual
        int currentIndex = players.indexOf(currentTurn);
        // Cambia al siguiente jugador
        currentIndex = (currentIndex + 1) % players.size();
        currentTurn = players.get(currentIndex);
        // Si volvemos al primer jugador, incrementamos el turno
        if (currentIndex == 0) {
            currentRound++;
        }
    }

    
    
    
    
    
}
