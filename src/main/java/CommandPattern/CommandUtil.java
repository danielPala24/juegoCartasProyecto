/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PALA
 */
public class CommandUtil {
    // Ayuda a manejar y procesar las entradas en el txtConsola
    public static String[] tokenizeArgs(String input) {
        List<String> tokens = new ArrayList<>();
        char[] chars = input.toCharArray();
        StringBuilder currentToken = new StringBuilder();
        boolean insideQuotes = false;
        
        for (char c : chars) {
            if (c == ' ' && !insideQuotes) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
            } else if (c == '"') {
                insideQuotes = !insideQuotes;
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens.toArray(new String[0]);
    }
}
