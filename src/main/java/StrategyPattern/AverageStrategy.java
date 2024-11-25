/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StrategyPattern;

import Jugador.Ability;
import Jugador.Card;
import Jugador.Player;
import java.util.ArrayList;

/**
 *
 * @author Ian
 */
public class AverageStrategy implements iAttackStrategy{
    ArrayList<Player> jugadores;

    public AverageStrategy(ArrayList<Player> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility) {
        int numTipos = 10; 
        int[] sumaDanios = new int[numTipos];
        int[] conteoDanios = new int[numTipos];
        for (Player jugador : jugadores) {
            for (Card card : jugador.getPlayerCards()) {
                for (Ability cardAbility : card.getCardAbilities()) {
                    for (int tipo = 0; tipo < cardAbility.getDamagePerType().size(); tipo++) {
                        int damage = cardAbility.getDamagePerType().get(tipo);
                        sumaDanios[tipo] += damage;
                        conteoDanios[tipo]++;
                    }
                }
            }
        }
        ArrayList<Integer> promedios = new ArrayList<>();
        for (int i = 0; i < numTipos; i++) {
            if (conteoDanios[i] > 0) {
                promedios.add(sumaDanios[i] / conteoDanios[i]);
            } else {
                promedios.add(0); 
            }
        }
        return promedios;
    }


    
}
