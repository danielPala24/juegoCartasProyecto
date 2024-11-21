/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import javax.swing.JTextArea;

/**
 *
 * @author PALA
 */
public class NotFoundCommand implements iCommand {

    public NotFoundCommand() {
    }

    @Override
    public String getCommandName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void execute(String[] args, JTextArea console) {
        console.append("\nError: Comando no encontrado.");
    }
    
}
