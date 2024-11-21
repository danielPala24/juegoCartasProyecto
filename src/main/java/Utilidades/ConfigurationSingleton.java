/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

/**
 *
 * @author PALA
 */
public class ConfigurationSingleton {
    private static ConfigurationSingleton singleton;
    private static String CONFIGURATION_PROP = "META-INF/Configuration.properties";
    private static final String APP_MIN_PROP = "minPercentage";
    private static final String APP_MAX_PROP = "maxPercentage";
    private double minPercentage;
    private double maxPercentage;
    
    private ConfigurationSingleton(){
        Properties prop = loadProperty(CONFIGURATION_PROP);
        this.minPercentage = parseDouble(prop.getProperty(APP_MIN_PROP));
        this.maxPercentage = parseDouble(prop.getProperty(APP_MAX_PROP));
    }
    
    private static synchronized void createInstance(){
        if(singleton==null){
            singleton = new ConfigurationSingleton();
        }
    }
    
    public static ConfigurationSingleton getInstance(){
        if(singleton==null){
            createInstance();
        }
        return singleton;
    }
    
    // ------------ GETTERS ------------- //

    public double getMinPercentage() {
        return minPercentage;
    }

    public double getMaxPercentage() {
        return maxPercentage;
    }
    
    // ------------ UTILITY ------------- //

    private static Properties loadProperty(String fileName){
        Properties properties = new Properties();
        try(InputStream input = ConfigurationSingleton.class.getClassLoader().getResourceAsStream(fileName)){
            if(input==null){
               throw new IOException("Configuration file not found: " + fileName);
            }
            properties.load(input);
        } catch(IOException ex){
            throw new RuntimeException("Error al cargar el archivo: " + fileName, ex);
        }
        return properties;
    }
    
    private static double parseDouble(String value){
        try{
            return Double.parseDouble(value);
        } catch(NumberFormatException ex){
            return 0.0;
        }
    }
}
