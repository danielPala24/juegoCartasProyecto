/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import java.util.ArrayList;

/**
 *
 * @author PALA
 */
public class Player {
    private int id;
    private String name;
    private ArrayList<Card> playerCards;

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

    // Method to add a card to player's cards
    public void addCard(Card card) {
        this.playerCards.add(card);
    }

    // Method to remove a card from player's cards
    public void removeCard(Card card) {
        this.playerCards.remove(card);
    }
}
