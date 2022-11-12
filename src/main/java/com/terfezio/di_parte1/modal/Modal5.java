package com.terfezio;

import javax.swing.*;
import java.awt.event.*;

public class Modal5 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public boolean seleccion;

    /**
     * Constructor de la ventana Modal5 que pregunta al usuario si mantiene la selección
     */
    public Modal5() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Método que se ejecuta con el evento on clic del botón Sí
     */
    private void onOK() {
        // add your code here
        seleccion = true;
        dispose();
    }

    /**
     * Método que se ejecuta co el evento on clic del botón No
     */
    private void onCancel() {
        // add your code here if necessary
        seleccion = false;
        dispose();
    }

    public static void main(String[] args) {
        Modal5 dialog = new Modal5();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
