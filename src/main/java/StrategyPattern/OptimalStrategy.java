/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StrategyPattern;

import Jugador.Ability;
import Jugador.Card;
import Jugador.Player;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ian
 */
public class OptimalStrategy implements iAttackStrategy{
    ArrayList<Player> jugadores;

    public OptimalStrategy(ArrayList<Player> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility) {
        int numTipos = 10; // Número de tipos de daño
        ArrayList<Integer> optimal = new ArrayList<>(Collections.nCopies(numTipos, Integer.MIN_VALUE)); 
        for (int indexHabilidad = 0; indexHabilidad < numTipos; indexHabilidad++) {
            int max = Integer.MIN_VALUE;
            for (Player jugador : jugadores) {
                for (Card playerCard : jugador.getPlayerCards()) {
                    for (Ability cardAbility : playerCard.getCardAbilities()) {
                        int damage = cardAbility.getDamagePerType().get(indexHabilidad);
                        if (damage > max) {
                            max = damage;
                        }
                    }
                }
            }
            optimal.set(indexHabilidad, max);
        }

        return optimal;
    }

    
    
}
