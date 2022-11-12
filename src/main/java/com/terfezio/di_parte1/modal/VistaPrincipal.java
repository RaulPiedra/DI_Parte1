package com.terfezio;

import javax.swing.*;
import java.net.URL;

public class VistaPrincipal {
    private JPanel panel1;
    private JLabel jLabel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("VistaPrincipal");

        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        frame.setContentPane(vistaPrincipal.panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //vistaPrincipal.modal();

        vistaPrincipal.optionPane();

    }

    /**
     * Método que usa JOptionPane
     */
    public void optionPane() {
        String imgLocation = "/java2.png";
        URL imgURL = VistaPrincipal.class.getResource(imgLocation);
        assert imgURL != null;
        ImageIcon icon = new ImageIcon(imgURL);

        JOptionPane.showMessageDialog(null, "Esto es una aplicacion Java", "Esto es java", JOptionPane.INFORMATION_MESSAGE, icon);
        String nombre = JOptionPane.showInputDialog("Cual es tu nombre?");

        JOptionPane.showMessageDialog(null, "Hola, " + nombre);

        Object[] opciones = {"Barca a pedales", "Pincho de tortilla", "Valeriana"};
        Object opcion = JOptionPane.showInputDialog(null, "Elije:", "Seleccione una opcion:", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        this.jLabel.setText((String) opcion);

        int cofirmacion = JOptionPane.showConfirmDialog(null, "Lo confirmas?", "Titulo", JOptionPane.YES_NO_OPTION );

        if (cofirmacion == 1) {
            //this.jLabel.setText(null);
            this.jLabel.setVisible(false);
        }
    }

    /**
     * Método que usa ventanas custom
     */
    public void modal() {
        Modal1 dialog = new Modal1();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        Modal2 dialog2 = new Modal2();
        dialog2.pack();
        dialog2.setVisible(true);
        dialog2.setResizable(false);
        dialog.setLocationRelativeTo(null);

        String name = dialog2.showDialog();
        System.out.println(name);

        Modal3 dialog3 = new Modal3();
        dialog3.jLabel.setText("Hola, " + name);
        dialog3.pack();
        dialog3.setVisible(true);
        dialog3.setResizable(false);
        dialog.setLocationRelativeTo(null);

        Modal4 dialog4 =new Modal4();
        dialog4.pack();
        dialog4.setVisible(true);
        dialog4.setResizable(false);
        dialog.setLocationRelativeTo(null);

        String selection = dialog4.showInput();

        this.jLabel.setText(selection);

        Modal5 dialog5 = new Modal5();
        dialog5.pack();
        dialog5.setVisible(true);
        dialog5.setResizable(false);
        dialog.setLocationRelativeTo(null);

        boolean seleccion = dialog5.seleccion;

        if (!seleccion) {
            this.jLabel.setText(null);
        }

    }
}
