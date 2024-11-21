/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import Jugador.Card;
import static java.awt.Color.BLACK;

/**
 *
 * @author PALA
 */
public class CardCreator extends JFrame {
    private ArrayList<File> imageFiles;
    private int currentImageIndex = 0;
    
    private JLabel imageLabel;
    private JButton nextButton;
    private JButton previousButton;
    private JTextField nameTextField;
    private JComboBox<String> typeComboBox;
    private JButton confirmButton;

    public CardCreator() {
        // Set up the frame
        setTitle("Card Creation");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Load images from a specific folder
        loadImagesFromFolder("/C://Users//PALA//Documents//GitHubRepos//juegoCartasProyecto//src//main//resources//Images/");

        // Image display panel
        JPanel imagePanel = createImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        // Details panel
        //JPanel detailsPanel = createDetailsPanel();
       // add(detailsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void loadImagesFromFolder(String folderPath) {
        imageFiles = new ArrayList<>();
        File folder = new File(folderPath);
        
        // Filter for image files
        File[] files = folder.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".png") || 
            name.toLowerCase().endsWith(".jpg") || 
            name.toLowerCase().endsWith(".jpeg") || 
            name.toLowerCase().endsWith(".gif")
        );

        if (files != null) {
            for (File file : files) {
                imageFiles.add(file);
            }
        }

        if (imageFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No images found in the specified folder!");
        }
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        
        // Image Label
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(500, 600));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBackground(BLACK);
        imageLabel.setOpaque(true);
        
        // Navigation Buttons
        nextButton = new JButton("Next Image");
        previousButton = new JButton("Previous Image");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(navigationPanel, BorderLayout.SOUTH);

        // Initial image load
        updateImageDisplay();

        return imagePanel;
    }

    private void showNextImage() {
        if (!imageFiles.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % imageFiles.size();
            updateImageDisplay();
        }
    }

    private void showPreviousImage() {
        if (!imageFiles.isEmpty()) {
            currentImageIndex = (currentImageIndex - 1 + imageFiles.size()) % imageFiles.size();
            updateImageDisplay();
        }
    }

    private void updateImageDisplay() {
        if (!imageFiles.isEmpty()) {
            File currentImageFile = imageFiles.get(currentImageIndex);
            ImageIcon icon = new ImageIcon(currentImageFile.getPath());
            
            // Scale image to fit the label
            Image scaledImage = icon.getImage().getScaledInstance(
                400, 600, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
    
    /*
    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout());

        // Name TextField
        nameTextField = new JTextField(20);
        nameTextField.setBorder(BorderFactory.createTitledBorder("Card Name"));

        // Type ComboBox
        String[] cardTypes = {"Attack", "Defense", "Special", "Support"};
        typeComboBox = new JComboBox<>(cardTypes);
        typeComboBox.setBorder(BorderFactory.createTitledBorder("Card Type"));

        // Confirm Button
        confirmButton = new JButton("Create Card");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCard();
            }
        });

        detailsPanel.add(nameTextField);
        detailsPanel.add(typeComboBox);
        detailsPanel.add(confirmButton);

        return detailsPanel;
    }
    */

    private void createCard() {
        String cardName = nameTextField.getText();
        String cardType = (String) typeComboBox.getSelectedItem();
        
        if (cardName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a card name!");
            return;
        }

        File selectedImageFile = imageFiles.get(currentImageIndex);
        ImageIcon cardImage = new ImageIcon(selectedImageFile.getPath());

        // Here you would typically create a new Card object
        Card newCard = new Card(cardImage, cardName, cardType);
        
        // Optional: Clear fields after card creation
        nameTextField.setText("");
        JOptionPane.showMessageDialog(this, "Card Created: " + cardName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CardCreator().setVisible(true);
        });
    }
}
