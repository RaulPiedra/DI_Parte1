package com.terfezio;

import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AppForm {
    private boolean passWordVisible;
    private int edad;
    private LocalDate birthDate;
    private String name;
    private String surname;
    private JButton buttonSee;
    private JPasswordField passwordField1;
    private String phoneNumber;
    private JTextField textFieldBirth;
    private JTextField textFieldAge;
    private JCheckBox carneDeConducirCheckBox;
    private JTextArea textArea1;
    private JButton buttonSend;
    private JPanel jPanel1;
    private String dni;
    private final Component[] components;

    public AppForm() {
        passWordVisible = false;

        components = jPanel1.getComponents();

        for (Component component: components) {
            if (component.getClass().equals(JTextField.class)) {

                JTextField jTextField = (JTextField) component;
                //System.out.println(jTextField.getName());
                // añadimos los placeHolders
                setPlaceHolder(jTextField);
                jTextField.addFocusListener(new FocusAdapter() {
                    /**
                     * Invoked when a component loses the keyboard focus.
                     *
                     * @param e A low-level event which indicates that a Component has gained or
                     *          lost the input focus.
                     */
                    @Override
                    public void focusLost(FocusEvent e) {
                        super.focusLost(e);
                        String nameOfTextField = jTextField.getName();
                        switch (nameOfTextField) {
                            case  "nombre" -> validateNombre(e);
                            case "apellidos" -> validateApellidos(e);
                            case "nacimiento" -> validateNacimiento(e);
                            case "telefono" -> validateTelefono(e);
                            case "dni" -> validateDni(e);
                        }
                    }
                });
            }
        }

        buttonSend.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                send();
            }
        });
        buttonSend.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e An event which indicates that a keystroke occurred in a component.
             */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == 10) {
                    send();
                }
            }
        });
        buttonSee.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changePasswordVisibility();
            }

            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changePasswordVisibility();
            }

            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                changePasswordVisibility();
            }
        });

    }

    private void setPlaceHolder(JTextField jTextField) {
        String nameOfTextField = jTextField.getName();

        PromptSupport.setPrompt("contraseña", passwordField1);
        switch (nameOfTextField) {
            case  "nombre" -> PromptSupport.setPrompt("nombre", jTextField);
            case "apellidos" -> PromptSupport.setPrompt("apellidos", jTextField);
            case "nacimiento" -> PromptSupport.setPrompt("dd/MM/aaaa", jTextField);
            case "telefono" -> PromptSupport.setPrompt("número de teléfono", jTextField);
            case "dni" -> PromptSupport.setPrompt("DNI", jTextField);
            case "edad" -> PromptSupport.setPrompt("Se calcula", jTextField);

        }
    }

    private void validateDni(FocusEvent e) {
        JTextField jTextField = (JTextField) e.getComponent();
        final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
        dni = jTextField.getText();
        String dniNumberString;
        char userDniLetter;
        int userDniNumber;
        char calculatedDniLetter;
        boolean letterMatches = false;

        if (!dni.equals("")) {
            dniNumberString = dni.substring(0, dni.length() - 1);
            userDniLetter = dni.charAt(dni.length() - 1);
            userDniNumber = Integer.parseInt(dniNumberString);
            calculatedDniLetter = NIF_STRING_ASOCIATION.charAt(userDniNumber % 23);
            letterMatches = (calculatedDniLetter == userDniLetter);
        }

        boolean matches = dni.matches("[0-9]{8}[A-Z]");


        if (!matches && !dni.equals("")) {

            JOptionPane.showMessageDialog(null, "Debe introducir un DNI válido");
            jTextField.requestFocus();
        }
        else if (matches && !dni.equals("") && !letterMatches) {
            JOptionPane.showMessageDialog(null, "La letra no corresponde");
            jTextField.requestFocus();
        }
    }

    private void validateTelefono(FocusEvent e) {

        JTextField jTextField = (JTextField) e.getComponent();
        phoneNumber = jTextField.getText();

        //boolean matches = phoneNumber.matches("[0-9]{1,9}");
        boolean matches = phoneNumber.matches("([679])[0-9]{8}");
        if (!matches && !phoneNumber.equals("")) {
            JOptionPane.showMessageDialog(null, "Introduzca un número válido: máximo 9 cifras");
            jTextField.requestFocus();
        }
    }

    private void validateNacimiento(FocusEvent e) {
        JTextField jTextField = (JTextField) e.getComponent();

        String birth = jTextField.getText();
        boolean matches = birth.matches("^([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/([0-9][0-9])?[0-9][0-9]$");
        if (!matches && !birth.equals("")) {

            JOptionPane.showMessageDialog(null, "Introduzca una fecha válida: dd/mm/yyyy");
            jTextField.requestFocus();
        }
        else if (matches) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
            birthDate = LocalDate.parse(birth, dateTimeFormatter);
            LocalDate today = LocalDate.now();

            edad = Period.between(birthDate, today).getYears();
            textFieldAge.setText(String.valueOf(edad));

        }
    }

    private void validateApellidos(FocusEvent e) {
        JTextField jTextField = (JTextField) e.getComponent();
        surname = jTextField.getText();
    }

    private void validateNombre(FocusEvent e) {
        JTextField jTextField = (JTextField) e.getComponent();
        name = jTextField.getText();
        //boolean matches = name.matches("[0-9A-Za-z]{3,8}");
        boolean matches = name.matches("\\w{3,8}");
        if (!matches && !name.equals("")) {

            JOptionPane.showMessageDialog(null, "Debe introducir un mínimo de 3 caracteres y 8 como máximo");
            jTextField.setText(null);
            jTextField.requestFocus();
        }
    }

    /**
     * Changes password visibility in passwordField
     */
    private void changePasswordVisibility() {
        if (passWordVisible) {
            URL imageURL = AppForm.class.getResource("/hidden.png");
            assert imageURL != null;
            ImageIcon icon = new ImageIcon(imageURL);
            buttonSee.setIcon(icon);
            passwordField1.setEchoChar('*');
            passWordVisible = false;
        }
        else {
            URL imageURL = AppForm.class.getResource("/eye.png");
            assert imageURL != null;
            ImageIcon icon = new ImageIcon(imageURL);
            buttonSee.setIcon(icon);
            passwordField1.setEchoChar((char) 0);
            passWordVisible = true;
        }
    }

    /**
     * Send personal data to textArea
     */
    private void send() {
        if (edad == 0) {
            JOptionPane.showMessageDialog(null, "Introduce la fecha de nacimiento", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            textFieldBirth.requestFocus();
        }
        else if (edad < 18) {
            JOptionPane.showMessageDialog(null, "El usuario es menor de edad", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            textFieldBirth.requestFocus();
        }
        else {
            boolean drivingLicense = carneDeConducirCheckBox.isSelected();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
            String password = String.valueOf(passwordField1.getPassword());

            String text = "Usuario: " + name + " " + surname + "\n";
            text += "Contraseña: " + password + "\n";
            text += "Número de teléfono: " + phoneNumber + "\n";
            text += "Nació el " + birthDate.format(dateTimeFormatter) + ", tiene " + edad + " años.\n";
            text += "Su número de DNI es: " + dni;
            if (drivingLicense) {
                text += " y tiene el carné de conducir.";
            }
            else {
                text += " y no tiene el carné de conducir.";
            }
            textArea1.setText(text);
            clearAll();
        }
    }
    public void clearAll() {
        for (Component component: components) {
            if (component.getClass().equals(JTextField.class)) {
                JTextField jTextField = (JTextField) component;
                jTextField.setText(null);
            }

        }
        passwordField1.setText(null);
        carneDeConducirCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("App");
        frame.setContentPane(new AppForm().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
