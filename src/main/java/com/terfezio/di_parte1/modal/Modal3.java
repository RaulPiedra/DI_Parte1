package com.terfezio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modal3 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    public JLabel jLabel;

    /**
     * Constructor de la ventana Modal3 que saluda con el nombre del usuario
     */
    public Modal3() {
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

    public static void main(String[] args) {
        Modal3 dialog = new Modal3();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
