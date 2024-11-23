/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package StrategyPattern;

import Jugador.*;
import java.util.ArrayList;

/**
 *
 * @author PALA
 */
public interface iAttackStrategy {
    ArrayList<Integer> executeStrategy(Card attackingCard, Ability selectedAbility);
}
