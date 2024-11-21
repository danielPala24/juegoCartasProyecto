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
public class Abilities {
    private ArrayList<Integer> damagePerType;

    // Constructor
    public Abilities() {
        this.damagePerType = new ArrayList<>(10);
    }

    // Getter and Setter
    public ArrayList<Integer> getDamagePerType() {
        return damagePerType;
    }

    public void setDamagePerType(ArrayList<Integer> damagePerType) {
        this.damagePerType = damagePerType;
    }

    // Method to add a damage value
    public void addDamage(int damageValue) {
        this.damagePerType.add(damageValue);
    }

    // Method to remove a damage value
    public void removeDamage(int damageValue) {
        this.damagePerType.remove(Integer.valueOf(damageValue));
    }
}
