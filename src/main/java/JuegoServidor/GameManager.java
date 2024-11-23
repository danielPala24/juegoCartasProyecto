/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JuegoServidor;

import Jugador.Player;
import java.util.ArrayList;

/**
 *
 * @author PALA
 */
public class GameManager {
    private static GameManager instance;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int currentTurn;
    private boolean gameInProgress;
    
    // Constructor privado (Singleton)
    private GameManager() {
        players = new ArrayList<>();
        currentTurn = 0;
        gameInProgress = false;
    }
    
    // Método para obtener la instancia única
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    // Métodos para gestionar jugadores
    public void addPlayer(Player player) {
        if (!gameInProgress) {
            players.add(player);
        }
    }
    
    public Player getPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    // Métodos para gestionar el flujo del juego
    public void startGame() {
        if (players.size() >= 2) {  // Asumiendo que necesitas al menos 2 jugadores
            gameInProgress = true;
            currentPlayer = players.get(0);
            currentTurn = 1;
        } else {
            throw new IllegalStateException("No hay suficientes jugadores para iniciar el juego");
        }
    }
    
    public void nextTurn() {
        if (!gameInProgress) {
            throw new IllegalStateException("El juego no ha iniciado");
        }
        
        // Encuentra el índice del jugador actual
        int currentIndex = players.indexOf(currentPlayer);
        
        // Cambia al siguiente jugador
        currentIndex = (currentIndex + 1) % players.size();
        currentPlayer = players.get(currentIndex);
        
        // Si volvemos al primer jugador, incrementamos el turno
        if (currentIndex == 0) {
            currentTurn++;
        }
    }
    
    // Métodos para verificar el estado del juego
    public boolean isGameInProgress() {
        return gameInProgress;
    }
    
    public int getCurrentTurn() {
        return currentTurn;
    }
    
    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);  // Retorna una copia para evitar modificaciones externas
    }
    
    // Método para terminar el juego
    public void endGame() {
        gameInProgress = false;
        currentPlayer = null;
        currentTurn = 0;
    }
    
    // Método para reiniciar el GameManager (útil para pruebas o nuevo juego)
    public static void reset() {
        instance = new GameManager();
    }
    
    // Métodos auxiliares para el estado del juego
    public boolean isPlayerTurn(Player player) {
        return gameInProgress && currentPlayer.equals(player);
    }
    
    // Método para verificar si el juego ha terminado
    
    /*
    public boolean checkGameEnd() {
        // WIP
        // Por ejemplo, verificar si solo queda un jugador con vida
        int playersAlive = (int) players.stream()
                .filter(player -> player.getHealth() > 0)
                .count();
        
        if (playersAlive <= 1) {
            endGame();
            return true;
        }
        return false;
    }
    
    // Método para obtener el ganador
    public Player getWinner() {
        if (gameInProgress) {
            return null;
        }
        
        return players.stream()
                .filter(player -> player.getHealth() > 0) // aqui lo tiro por vida del jugador, se puede hacer que la vida sea la cantidad de cartas vivas, al quedarse con 0 cartas se pierde
                .findFirst()
                .orElse(null);
    }
    
    */
}
