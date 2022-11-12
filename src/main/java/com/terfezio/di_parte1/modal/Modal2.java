package com.terfezio.di_parte1.modal;

import javax.swing.*;


public class Modal2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    public JTextField textField1;


    /**
     * Constructor de la ventana Modal2 que pregunta por su nombre al usuario
     */
    public Modal2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());


    }

    /**
     * Devuelve el texto que ha introducido el usuario
     * @return El texto que el usuario ha introducido
     */
    public String showDialog() {
        return textField1.getText();
    }

    /**
     * Método que ejecuta el On click listener del botón Ok
     */
    public void onOK() {
        // add your code here
        dispose();
        }

    public static void main(String[] args) {
        Modal2 dialog = new Modal2();
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }
}
