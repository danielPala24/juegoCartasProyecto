/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StrategyPattern;

import Jugador.Ability;
import Jugador.Card;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author PALA
 */
public class RandomCombinationStrategy implements iAttackStrategy {
    private ArrayList<Card> playerCards;
    
    public RandomCombinationStrategy(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }
    
    @Override
    public ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility) {
        ArrayList<Integer> baseArray = new ArrayList<>(selectedAbility.getDamagePerType());
        
        // Filtrar las otras cartas (excluyendo la carta de ataque)
        ArrayList<Card> availableCards = new ArrayList<>(playerCards);
        availableCards.remove(attackingCard);
        
        // Seleccionar una carta aleatoria de las disponibles
        Random random = new Random();
        Card randomCard = availableCards.get(random.nextInt(availableCards.size()));
        
        // Seleccionar una habilidad aleatoria de la carta seleccionada
        ArrayList<Ability> randomCardAbilities = randomCard.getCardAbilities();
        Ability randomAbility = randomCardAbilities.get(random.nextInt(randomCardAbilities.size()));
        
        ArrayList<Integer> compareArray = randomAbility.getDamagePerType();
        
        // Crear el mejor arreglo comparando valores
        ArrayList<Integer> resultArray = new ArrayList<>();
        for(int i = 0; i < baseArray.size(); i++) {
            resultArray.add(Math.max(baseArray.get(i), compareArray.get(i)));
        }
        
        return resultArray;
    }
}
