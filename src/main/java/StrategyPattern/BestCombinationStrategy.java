/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StrategyPattern;

import Jugador.Ability;
import Jugador.Card;
import java.util.ArrayList;

/**
 *
 * @author PALA
 */
public class BestCombinationStrategy implements iAttackStrategy{
    private ArrayList<Card> playerCards;
    
    public BestCombinationStrategy(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }
    
    @Override
    public ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility) {
        ArrayList<Integer> resultArray = new ArrayList<>(selectedAbility.getDamagePerType());
        
        // Inicializar con los valores de la habilidad seleccionada
        for(int i = 0; i < resultArray.size(); i++) {
            resultArray.set(i, resultArray.get(i));
        }
        
        // Comparar con todas las cartas y todas sus habilidades
        for(Card card : playerCards) {
            for(Ability ability : card.getCardAbilities()) {
                ArrayList<Integer> currentArray = ability.getDamagePerType();
                for(int i = 0; i < currentArray.size(); i++) {
                    resultArray.set(i, Math.max(resultArray.get(i), currentArray.get(i)));
                }
            }
        }
        
        return resultArray;
    }
}
