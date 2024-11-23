/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StrategyPattern;

import java.util.ArrayList;
import java.util.Random;
import Jugador.*;

/**
 *
 * @author PALA
 */
public class RandomDuplexStrategy implements iAttackStrategy {
    @Override
    public ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility) {
        // Obtenemos el arreglo de daño de la habilidad seleccionada
        ArrayList<Integer> resultDamage = new ArrayList<>(selectedAbility.getDamagePerType());
        Random random = new Random();
        int randomIndex = random.nextInt(resultDamage.size());
        
        // Duplicar el valor en la posición aleatoria
        resultDamage.set(randomIndex, resultDamage.get(randomIndex) * 2);
        
        return resultDamage;
    }
}
