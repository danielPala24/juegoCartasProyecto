/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PALA
 */
public class Player implements Serializable{
    private int id;
    private String name;
    private ArrayList<Card> playerCards;
    public boolean isPlaying = true;
    public boolean isOwner = false;
    public int wins = 0;
    public  int losses = 0;
    public int atacks = 0;
    public int success = 0;
    public int failed = 0;
    public int giveUp = 0;

    // Constructor
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.playerCards = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }


    public void addCard(Card card) {
        this.playerCards.add(card);
    }


    public void removeCard(Card card) {
        this.playerCards.remove(card);
    }
    
    public Card getCardByName(String cardName){
        for (Card card : playerCards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return card;
            }
        }
        return null;
    }
    
    public String toStatusString(){
        String str = name + " #" + id 
                + "\nWins: " + wins
                + "\nLosses: " + losses
                + "\nAtacks: " + atacks
                + "\nSuccess: " + success
                + "\nFailed: " + failed
                + "\nGiveUp: " + giveUp;
        return str;
    }
    public String toRankingString(){
        return name + "[" + wins + "/" + losses + "]";
    }
}
