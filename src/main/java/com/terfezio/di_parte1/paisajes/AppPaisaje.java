package com.terfezio.di_parte1;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class AppPaisaje extends JFrame{
    public AppPaisaje() {
        rios = new Paisaje("rios");
        montanas = new Paisaje("montanas");
        glaciares = new Paisaje("glaciares");
        desiertos = new Paisaje("desiertos");

        labelRios.setIcon(rios.devuelveImagen());
        labelMontanas.setIcon(montanas.devuelveImagen());
        labelGlaciares.setIcon(glaciares.devuelveImagen());
        labelDesiertos.setIcon(desiertos.devuelveImagen());


        labelRios.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                labelRios.setIcon(rios.devuelveImagen());

            }
        });
        labelMontanas.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                labelMontanas.setIcon(montanas.devuelveImagen());
            }
        });
        labelDesiertos.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                labelDesiertos.setIcon(desiertos.devuelveImagen());

            }
        });
        labelGlaciares.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                labelGlaciares.setIcon(glaciares.devuelveImagen());
            }
        });
    }
    public static void main(String[] args) {
        String imgLocation = "/imagenes/icono/icon.png";
        URL imageURL = AppPaisaje.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);

        JFrame frame = new JFrame("Swing P1.3 PiedraRamonRaul");
        frame.setIconImage(icon.getImage());
        frame.setContentPane(new AppPaisaje().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  // centra la ventana
    }
    private final Paisaje rios;
    private final Paisaje desiertos;
    private final Paisaje montanas;
    private final Paisaje glaciares;
    private JPanel panel1;
    private JLabel labelRios;
    private JLabel labelMontanas;
    private JLabel labelDesiertos;
    private JLabel labelGlaciares;
}
