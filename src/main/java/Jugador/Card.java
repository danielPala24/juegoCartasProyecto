/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import StrategyPattern.iAttackStrategy;
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
    private ArrayList<Ability> cardAbilities;
    private ArrayList<Ability> cardUsedAbilities;
    private iAttackStrategy attackStrategy;
    

    // Constructor
    public Card(ImageIcon image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.cardAbilities = new ArrayList<>(5);
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

    public ArrayList<Ability> getCardAbilities() {
        return cardAbilities;
    }

    public void setCardAbilities(ArrayList<Ability> cardAbilities) {
        this.cardAbilities = cardAbilities;
    }

    public ArrayList<Ability> getCardUsedAbilities() {
        return cardUsedAbilities;
    }

    public void setCardUsedAbilities(ArrayList<Ability> cardUsedAbilities) {
        this.cardUsedAbilities = cardUsedAbilities;
    }

    public iAttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(iAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    
    public void addAbility(Ability ability) {
        this.cardAbilities.add(ability);
    }

    public void removeAbility(Ability ability) {
        this.cardAbilities.remove(ability);
    }

    @Override
    public String toString() {
        return "Card{" + "name=" + name + ", type=" + type + '}';
    }
    
    public ArrayList<Integer> executeAttack(Ability selectedAbility) {
        // Si no hay estrategia, retornar el daño base de la habilidad
        if (attackStrategy == null) {
            return new ArrayList<>(selectedAbility.getDamagePerType());
        }
        
        // Si hay estrategia, ejecutar el ataque con la estrategia
        return attackStrategy.executeStrategy(this, selectedAbility);
    }
}
