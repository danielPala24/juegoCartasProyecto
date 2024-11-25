/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Cliente.Cliente;
import CommandPattern.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PALA
 */
public class VistaJugador extends javax.swing.JFrame {
    private CommandManager commandManager; // Esto es un singleton, deberia de colocarse en el servidor o en un cliente, la verdad ahi esa logica no me la se
    private JButton[][] dmgMatrix;
    Cliente cliente;
    /**
     * Creates new form VistaJugador
     */
    public VistaJugador() {
        initComponents();
        initializerMatrix(pnlMostrarDmgHabilidades,dmgMatrix);
        cliente = new Cliente(this);
        // inicializamos el CommandManager
        commandManager = CommandManager.getInstance();
        commandManager.reigsterCommand(new CardCommand(cliente));
        commandManager.reigsterCommand(new ImageCommand());
        commandManager.reigsterCommand(new ChatCommand(cliente));
        commandManager.reigsterCommand(new PlayerCommand(cliente));
        
        btn_start.setVisible(false);
        
        // listener para detectar el enter
        txtConsola.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e){
                // detectamos el "enter"
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
                    e.consume(); // prevencion para el salto de linea
                    processCommand();
                }
            }
        });
    }
    
    // ------------ METODOS WRITE & APPEND--------------//
    public JButton [][] getDmgMatrix(){
        return dmgMatrix;
    }
    
    public JLabel[] getLblCardsAsArray(){
        JLabel[] arr = {lblCard1,lblCard2,lblCard3,lblCard4};
        return arr;
    }
    
    public JLabel[] getLblCardsHabilitiesAsArray(){
        JLabel[] arr = {lblNombreHabilidad1,lblNombreHabilidad2,lblNombreHabilidad3,lblNombreHabilidad4,lblNombreHabilidad5, lblNombreCartaSeleccionada};
        return arr;
    }
    
    public void setBtnStar(boolean state){
        btn_start.setVisible(state);
    }
    public void appentTxt_Ranking(String text){
        txtA_Ranking.append(text + "\n"); 
    } 
   
    public void appendTxt_InfoReceivedAtack(String text) {
        txtA_InfoReceivedAtack.append(text+ "\n");
    }

    public void appendTxt_Oponent(String text) {
        txtA_Oponent.append(text+ "\n");
    }

    public void appendTxt_Status(String text) {
        txtA_Status.append(text+ "\n");
    }

    public void appendTxt_InfoAtack(String text) {
        txtA_infoAtack.append(text+ "\n");
    }
    
    public void writeTxt_Ranking(String text) {
        txtA_Ranking.setText(text);
    }

    public void writeTxt_InfoReceivedAtack(String text) {
        txtA_InfoReceivedAtack.setText(text);
    }

    public void writeTxt_Oponent(String text) {
        txtA_Oponent.setText(text);
    }

    public void writeTxt_Status(String text) {
        txtA_Status.setText(text);
    }

    public void writeTxt_InfoAtack(String text) {
        txtA_infoAtack.setText(text);
    }

    
    // ---------- METODOS VARIOS ----------//
     private void processCommand(){
        String input = getLastCommand();
        if(input.isEmpty()) return;
        
        String[] tokens = CommandUtil.tokenizeArgs(input);
        
        if(tokens.length>0){
            String commandName = tokens[0];
            String[] args = new String[tokens.length -1];
            System.arraycopy(tokens, 1, args, 0, args.length);
            
            iCommand command = commandManager.getCommand(commandName);
            command.execute(args, txtConsola);
        } else {
            appendToConsole("Error: Entrada vacia.");
        }
    }
    
    private String getLastCommand(){
        String[] lines = txtConsola.getText().split("\n");
        return lines[lines.length-1].trim();
    }
    
    public void appendToConsole(String text){
        txtConsola.append("\n" + text+ "\n");
    }
    
    private void initializerMatrix(JPanel pnl,JButton[][] buttonMatrix){
        pnl.setLayout(new GridLayout(5,10,20,30));
        buttonMatrix = new JButton[5][10];
        
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                JButton btn = new JButton();
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
               
                btn.setFocusPainted(false);
                btn.setName("button" + i + "_" + j);
                btn.setText( i + ", " + j);
                buttonMatrix[i][j] = btn;
                pnl.add(btn);
            }
        }
        // Actualizar la vista del panel
        pnl.revalidate();
        pnl.repaint();
        this.dmgMatrix=buttonMatrix;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        pnlRanking = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_Ranking = new javax.swing.JTextArea();
        pnlDatosContrincante = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtA_Status = new javax.swing.JTextArea();
        pnlDatosPropios = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtA_Oponent = new javax.swing.JTextArea();
        pnlAtaqueRecibido = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtA_InfoReceivedAtack = new javax.swing.JTextArea();
        pnlAtaqueLanzado = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtA_infoAtack = new javax.swing.JTextArea();
        pnlCartas = new javax.swing.JPanel();
        btn_start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblCard4 = new javax.swing.JLabel();
        lblCard2 = new javax.swing.JLabel();
        lblCard1 = new javax.swing.JLabel();
        lblCard3 = new javax.swing.JLabel();
        lblNombreHabilidad2 = new javax.swing.JLabel();
        lblVidaCarta1 = new javax.swing.JLabel();
        lblVidaCarta2 = new javax.swing.JLabel();
        lblVidaCarta3 = new javax.swing.JLabel();
        lblVidaCarta4 = new javax.swing.JLabel();
        lblNombreCartaSeleccionada = new javax.swing.JLabel();
        lblNombreHabilidad3 = new javax.swing.JLabel();
        lblNombreHabilidad1 = new javax.swing.JLabel();
        lblNombreHabilidad5 = new javax.swing.JLabel();
        lblNombreHabilidad4 = new javax.swing.JLabel();
        pnlMostrarDmgHabilidades = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtConsola.setColumns(20);
        txtConsola.setRows(5);
        jScrollPane1.setViewportView(txtConsola);

        pnlPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 805, 1510, 126));

        pnlRanking.setBackground(new java.awt.Color(255, 204, 102));

        txtA_Ranking.setEditable(false);
        txtA_Ranking.setBackground(new java.awt.Color(255, 255, 255));
        txtA_Ranking.setColumns(20);
        txtA_Ranking.setRows(5);
        txtA_Ranking.setFocusable(false);
        jScrollPane2.setViewportView(txtA_Ranking);

        javax.swing.GroupLayout pnlRankingLayout = new javax.swing.GroupLayout(pnlRanking);
        pnlRanking.setLayout(pnlRankingLayout);
        pnlRankingLayout.setHorizontalGroup(
            pnlRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRankingLayout.setVerticalGroup(
            pnlRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlPrincipal.add(pnlRanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 20, -1, -1));

        pnlDatosContrincante.setBackground(new java.awt.Color(0, 0, 255));

        txtA_Status.setEditable(false);
        txtA_Status.setColumns(20);
        txtA_Status.setRows(5);
        txtA_Status.setFocusable(false);
        jScrollPane4.setViewportView(txtA_Status);

        javax.swing.GroupLayout pnlDatosContrincanteLayout = new javax.swing.GroupLayout(pnlDatosContrincante);
        pnlDatosContrincante.setLayout(pnlDatosContrincanteLayout);
        pnlDatosContrincanteLayout.setHorizontalGroup(
            pnlDatosContrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosContrincanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDatosContrincanteLayout.setVerticalGroup(
            pnlDatosContrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosContrincanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlPrincipal.add(pnlDatosContrincante, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 468, -1, -1));

        pnlDatosPropios.setBackground(new java.awt.Color(255, 153, 255));

        txtA_Oponent.setEditable(false);
        txtA_Oponent.setColumns(20);
        txtA_Oponent.setRows(5);
        txtA_Oponent.setFocusable(false);
        jScrollPane3.setViewportView(txtA_Oponent);

        javax.swing.GroupLayout pnlDatosPropiosLayout = new javax.swing.GroupLayout(pnlDatosPropios);
        pnlDatosPropios.setLayout(pnlDatosPropiosLayout);
        pnlDatosPropiosLayout.setHorizontalGroup(
            pnlDatosPropiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );
        pnlDatosPropiosLayout.setVerticalGroup(
            pnlDatosPropiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        pnlPrincipal.add(pnlDatosPropios, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 232, -1, -1));

        pnlAtaqueRecibido.setBackground(new java.awt.Color(255, 51, 102));

        txtA_InfoReceivedAtack.setEditable(false);
        txtA_InfoReceivedAtack.setColumns(20);
        txtA_InfoReceivedAtack.setRows(5);
        txtA_InfoReceivedAtack.setFocusable(false);
        jScrollPane5.setViewportView(txtA_InfoReceivedAtack);

        javax.swing.GroupLayout pnlAtaqueRecibidoLayout = new javax.swing.GroupLayout(pnlAtaqueRecibido);
        pnlAtaqueRecibido.setLayout(pnlAtaqueRecibidoLayout);
        pnlAtaqueRecibidoLayout.setHorizontalGroup(
            pnlAtaqueRecibidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtaqueRecibidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        pnlAtaqueRecibidoLayout.setVerticalGroup(
            pnlAtaqueRecibidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtaqueRecibidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        pnlPrincipal.add(pnlAtaqueRecibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 20, 350, 442));

        pnlAtaqueLanzado.setBackground(new java.awt.Color(0, 255, 255));

        txtA_infoAtack.setEditable(false);
        txtA_infoAtack.setColumns(20);
        txtA_infoAtack.setRows(5);
        txtA_infoAtack.setFocusable(false);
        jScrollPane6.setViewportView(txtA_infoAtack);

        javax.swing.GroupLayout pnlAtaqueLanzadoLayout = new javax.swing.GroupLayout(pnlAtaqueLanzado);
        pnlAtaqueLanzado.setLayout(pnlAtaqueLanzadoLayout);
        pnlAtaqueLanzadoLayout.setHorizontalGroup(
            pnlAtaqueLanzadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtaqueLanzadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAtaqueLanzadoLayout.setVerticalGroup(
            pnlAtaqueLanzadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtaqueLanzadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        pnlPrincipal.add(pnlAtaqueLanzado, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 468, -1, 325));

        pnlCartas.setBackground(new java.awt.Color(102, 255, 102));
        pnlCartas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_start.setText("Iniciar Partida");
        pnlCartas.add(btn_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 730, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("MY CARDS");
        pnlCartas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 27, 108, 29));

        lblCard4.setOpaque(true);
        pnlCartas.add(lblCard4, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 74, 140, 200));

        lblCard2.setOpaque(true);
        pnlCartas.add(lblCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 74, 140, 200));

        lblCard1.setOpaque(true);
        pnlCartas.add(lblCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 74, 140, 200));

        lblCard3.setOpaque(true);
        pnlCartas.add(lblCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 74, 140, 200));

        lblNombreHabilidad2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblNombreHabilidad2.setText("cardName");
        pnlCartas.add(lblNombreHabilidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 455, 108, 29));

        lblVidaCarta1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblVidaCarta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCarta1.setText("vida");
        pnlCartas.add(lblVidaCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 303, 108, 29));

        lblVidaCarta2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblVidaCarta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCarta2.setText("vida");
        pnlCartas.add(lblVidaCarta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 303, 108, 29));

        lblVidaCarta3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblVidaCarta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCarta3.setText("vida");
        pnlCartas.add(lblVidaCarta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 303, 108, 29));

        lblVidaCarta4.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblVidaCarta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCarta4.setText("vida");
        pnlCartas.add(lblVidaCarta4, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 303, 108, 29));

        lblNombreCartaSeleccionada.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblNombreCartaSeleccionada.setText("cardName");
        pnlCartas.add(lblNombreCartaSeleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 350, 108, 29));

        lblNombreHabilidad3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblNombreHabilidad3.setText("cardName");
        pnlCartas.add(lblNombreHabilidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 517, 108, 29));

        lblNombreHabilidad1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblNombreHabilidad1.setText("cardName");
        pnlCartas.add(lblNombreHabilidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 397, 108, 29));

        lblNombreHabilidad5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblNombreHabilidad5.setText("cardName");
        pnlCartas.add(lblNombreHabilidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 636, 108, 29));

        lblNombreHabilidad4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblNombreHabilidad4.setText("cardName");
        pnlCartas.add(lblNombreHabilidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 574, 108, 29));

        javax.swing.GroupLayout pnlMostrarDmgHabilidadesLayout = new javax.swing.GroupLayout(pnlMostrarDmgHabilidades);
        pnlMostrarDmgHabilidades.setLayout(pnlMostrarDmgHabilidadesLayout);
        pnlMostrarDmgHabilidadesLayout.setHorizontalGroup(
            pnlMostrarDmgHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        pnlMostrarDmgHabilidadesLayout.setVerticalGroup(
            pnlMostrarDmgHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        pnlCartas.add(pnlMostrarDmgHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 426, -1, -1));

        pnlPrincipal.add(pnlCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 20, 760, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblCard1;
    private javax.swing.JLabel lblCard2;
    private javax.swing.JLabel lblCard3;
    private javax.swing.JLabel lblCard4;
    private javax.swing.JLabel lblNombreCartaSeleccionada;
    private javax.swing.JLabel lblNombreHabilidad1;
    private javax.swing.JLabel lblNombreHabilidad2;
    private javax.swing.JLabel lblNombreHabilidad3;
    private javax.swing.JLabel lblNombreHabilidad4;
    private javax.swing.JLabel lblNombreHabilidad5;
    private javax.swing.JLabel lblVidaCarta1;
    private javax.swing.JLabel lblVidaCarta2;
    private javax.swing.JLabel lblVidaCarta3;
    private javax.swing.JLabel lblVidaCarta4;
    private javax.swing.JPanel pnlAtaqueLanzado;
    private javax.swing.JPanel pnlAtaqueRecibido;
    private javax.swing.JPanel pnlCartas;
    private javax.swing.JPanel pnlDatosContrincante;
    private javax.swing.JPanel pnlDatosPropios;
    private javax.swing.JPanel pnlMostrarDmgHabilidades;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlRanking;
    private javax.swing.JTextArea txtA_InfoReceivedAtack;
    private javax.swing.JTextArea txtA_Oponent;
    private javax.swing.JTextArea txtA_Ranking;
    private javax.swing.JTextArea txtA_Status;
    private javax.swing.JTextArea txtA_infoAtack;
    private javax.swing.JTextArea txtConsola;
    // End of variables declaration//GEN-END:variables
}
