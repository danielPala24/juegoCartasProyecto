/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author PALA
 */
public class Card {
    private ImageIcon image;
    private String name;
    private String type;
    private ArrayList<Abilities> playerAbilities;

    // Constructor
    public Card(ImageIcon image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.playerAbilities = new ArrayList<>(5);
    }

    // Getters and Setters
    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Abilities> getPlayerAbilities() {
        return playerAbilities;
    }

    public void setPlayerAbilities(ArrayList<Abilities> playerAbilities) {
        this.playerAbilities = playerAbilities;
    }

    // Method to add an ability to the card
    public void addAbility(Abilities ability) {
        this.playerAbilities.add(ability);
    }

    // Method to remove an ability from the card
    public void removeAbility(Abilities ability) {
        this.playerAbilities.remove(ability);
    }

    @Override
    public String toString() {
        return "Card{" + "name=" + name + ", type=" + type + '}';
    }
    
    
}
