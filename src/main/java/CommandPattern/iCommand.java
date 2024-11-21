/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CommandPattern;

import javax.swing.JTextArea;

/**
 *
 * @author PALA
 */
public interface iCommand {
    public String getCommandName();
    public void execute(String[] args, JTextArea txtConsola);
}
