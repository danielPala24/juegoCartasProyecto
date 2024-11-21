/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.juegocartasproyecto;

import Utilidades.ConfigurationSingleton;

/**
 *
 * @author PALA
 */
public class JuegoCartasProyecto {

    public static void main(String[] args) {
        ConfigurationSingleton config = ConfigurationSingleton.getInstance();

        // Access configuration values
        System.out.println("Min Percentage: " + config.getMinPercentage());
        System.out.println("Max Percentage: " + config.getMaxPercentage());
    }
}
