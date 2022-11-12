package com.terfezio.di_parte1;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class CalculatorHelp extends JFrame {
    private JPanel panel1;
    private JLabel jLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculatorHelp");
        frame.setContentPane(new CalculatorHelp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CalculatorHelp() {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String imgLocation = "/info.png";
        URL imageURL = Utilidades.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);
        setIconImage(icon.getImage());
        setTitle("Ayuda");


        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("mouse clicked");
                dispose();
            }
        });
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
