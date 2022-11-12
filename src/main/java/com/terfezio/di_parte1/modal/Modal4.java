package com.terfezio.di_parte1.modal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modal4 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;

    /**
     * Constructor de la ventana Modal4 que permite elegir con un combo-box tres opciones
     */
    public Modal4() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    /**
     * Método que se ejecuta con el evento on clic del botón Ok
     */
    private void onOK() {
        // add your code here
        dispose();
    }

    /**
     * Metodo que devuelve la selección del usuario
     * @return Objeto seleccionado por el usuario (toString)
     */
    public String showInput() {
        return comboBox1.getSelectedItem().toString();
    }

    public static void main(String[] args) {
        Modal4 dialog = new Modal4();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
