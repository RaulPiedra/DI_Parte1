package com.terfezio.di_parte1;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

public class Calculator2 extends JFrame {
    private JTextField textField1;
    private JTextField textFieldBinary;
    private JPanel jPanel;
    private JPanel jPanelButtons;
    private JRadioButton binarioRadioButton;
    private JRadioButton octalRadioButton;
    private JRadioButton hexadecimalRadioButton;
    private JRadioButton decimalRadioButton;
    private JTextField textFieldOctal;
    private JTextField textFieldDecimal;
    private JTextField textFieldHexadecimal;
    private JButton jButtonleftParenthesis;
    private JButton jButtonRightParenthesis;
    private JButton jButtonPlus;
    private JButton jButtonX;
    private JButton jButtonEq;
    private JButton jButtonClear;
    private final Calculations calculations;
    private int calculationsCursor;
    private boolean equalPressed;
    private final ArrayList<JButton> jButtons;
    private String numeralSystem;

    private final String BINARY_ENABLED_BUTTONS = "01*+-()=acupdownequals";
    private final String OCTAL_ENABLED_BUTTONS = "01234567*+-()=acupdownequals";
    private final String DECIMAL_ENABLED_BUTTONS = "0123456789*+-()=acupdownequals";
    private final String HEXADECIMAL_ENABLED_BUTTONS = "0123456789ABCDEF*+-()=acupdownequals";


    public Calculator2() {
        jButtons = new ArrayList<>();
        decimalRadioButton.setSelected(true);

        numeralSystem = "decimal";
        calculations = new Calculations();
        calculationsCursor = 0;
        equalPressed = false;

        setContentPane(this.jPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String imgLocation = "/calculator.png";
        URL imageURL = Utilidades.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);
        setIconImage(icon.getImage());

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Calculadora");
        jMenu.setMnemonic(KeyEvent.VK_C);

        JMenuItem jMenuItem = new JMenuItem("Calculadora normal");
        KeyStroke keyStrokeCalcNormal = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_DOWN_MASK);
        jMenuItem.setMnemonic(KeyEvent.VK_N);
        jMenuItem.setAccelerator(keyStrokeCalcNormal);

        JMenuItem jMenuItem1 = new JMenuItem("Calculadora de programador");
        KeyStroke keyStrokeCalcProg = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK);
        jMenuItem1.setMnemonic(KeyEvent.VK_P);
        jMenuItem1.setAccelerator(keyStrokeCalcProg);

        JMenu jMenuHelp = new JMenu("Ayuda");
        jMenuHelp.setMnemonic(KeyEvent.VK_Y);

        JMenuItem jMenuItemHelp = new JMenuItem("Ayuda");
        KeyStroke keyStrokeHelp = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        jMenuItemHelp.setMnemonic(KeyEvent.VK_Y);
        jMenuItemHelp.setAccelerator(keyStrokeHelp);

        JMenuItem jMenuItemExit = new JMenuItem("Salir");
        KeyStroke keyStrokeExit = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK);
        jMenuItemExit.setMnemonic(KeyEvent.VK_S);
        jMenuItemExit.setAccelerator(keyStrokeExit);

        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem1);
        jMenu.add(jMenuItemExit);
        jMenuHelp.add(jMenuItemHelp);
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenuHelp);
        setJMenuBar(jMenuBar);

        jMenuItem1.setEnabled(false);

        jMenuItemHelp.addActionListener(e -> {
            new Calculator2Help();
        });

        jMenuItem.addActionListener(e -> {

            new Calculator();
            dispose();
        });

        jMenuItemExit.addActionListener(e -> {
            dispose();
        });



        Component[] components = jPanelButtons.getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JButton.class)) {
                JButton jButton = (JButton) component;
                jButtons.add(jButton);
                jButton.setFocusable(false);

                KeyStroke keyStroke;
                KeyStroke keyStrokePad;

                String name = jButton.getName();

                switch (name) {
                    case "(" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK);
                        Action action = new AbstractAction(jButton.getName()) {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                addElement(jButton.getName());
                            }
                        };
                        action.putValue(Action.ACCELERATOR_KEY, keyStroke);
                        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,jButton.getName());
                        jButton.getActionMap().put(jButton.getName(), action);

                    }
                    case ")" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_9, KeyEvent.SHIFT_DOWN_MASK);
                        Action action = new AbstractAction(jButton.getName()) {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                addElement(jButton.getName());
                            }
                        };
                        action.putValue(Action.ACCELERATOR_KEY, keyStroke);
                        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,jButton.getName());
                        jButton.getActionMap().put(jButton.getName(), action);
                    }
                    case "+" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "-" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "*" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.SHIFT_DOWN_MASK);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "0" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "1" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "2" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "3" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "4" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "5" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "6" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "7" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "8" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "9" -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
                    }
                    case "c" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
                        Action actionClear = new AbstractAction("clear") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (textField1.getText().length() >= 1) {
                                    textField1.setText("" + textField1.getText().substring(0, textField1.getText().length() - 1));
                                }
                            }
                        };
                        actionClear.putValue(Action.ACCELERATOR_KEY, keyStroke);


                        jButtonClear.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, KeyEvent.VK_BACK_SPACE);
                        jButtonClear.getActionMap().put(KeyEvent.VK_BACK_SPACE, actionClear);
                    }
                    case "ac" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, KeyEvent.SHIFT_DOWN_MASK);
                        Action actionClear = new AbstractAction("allClear") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                clearTextFields();
                            }
                        };
                        actionClear.putValue(Action.ACCELERATOR_KEY, keyStroke);
                        jButtonClear.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, jButton.getName());
                        jButtonClear.getActionMap().put(jButton.getName(), actionClear);
                    }
                    case "equals" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
                        Action actionEquals = new AbstractAction("equals") {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                executeEquals();
                                equalPressed = true;
                            }
                        };
                        actionEquals.putValue(Action.ACCELERATOR_KEY, keyStroke);


                        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, KeyEvent.VK_ENTER);
                        jButton.getActionMap().put(KeyEvent.VK_ENTER, actionEquals);
                    }
                    default -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0),0);
                        Action action = new AbstractAction(jButton.getName()) {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                addElement(jButton.getName());
                            }
                        };
                        action.putValue(Action.ACCELERATOR_KEY, keyStroke);
                        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,jButton.getName());
                        jButton.getActionMap().put(jButton.getName(), action);
                    }
                }




                jButton.addActionListener(new ActionListener() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameOfButton = jButton.getName();
                        switch (nameOfButton) {
                            case "equals" -> executeEquals();
                            case "c" -> {
                                if (textField1.getText().length() >= 1) {
                                    textField1.setText("" + textField1.getText().substring(0, textField1.getText().length() - 1));
                                }
                            }
                            case "ac" -> clearTextFields();
                            case "up" -> executeUp();
                            case "down" -> executeDown();
                            default -> addElement(jButton.getName());

                        }
                    }
                });
            }
        }
        setButtons(DECIMAL_ENABLED_BUTTONS);


        ActionListener listener1 = new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String base = e.getActionCommand();
                switch (base) {
                    case "binario" -> {
                        clearTextFields();
                        setButtons(BINARY_ENABLED_BUTTONS);
                        numeralSystem = "binary";
                    }
                    case "octal" -> {
                        clearTextFields();
                        setButtons(OCTAL_ENABLED_BUTTONS);
                        numeralSystem = "octal";
                    }
                    case "decimal" -> {
                        clearTextFields();
                        setButtons(DECIMAL_ENABLED_BUTTONS);
                        numeralSystem = "decimal";
                    }
                    case "hexadecimal" -> {
                        clearTextFields();
                        setButtons(HEXADECIMAL_ENABLED_BUTTONS);
                        numeralSystem = "hexadecimal";
                    }
                }

            }
        };
        hexadecimalRadioButton.addActionListener(listener1);
        decimalRadioButton.addActionListener(listener1);
        octalRadioButton.addActionListener(listener1);
        binarioRadioButton.addActionListener(listener1);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setShortCutsWithPad(JButton jButton, KeyStroke keyStroke, KeyStroke keyStrokePad) {
        Action action = new AbstractAction(jButton.getName()) {
            @Override
            public void actionPerformed(ActionEvent e) {

                addElement(jButton.getName());
            }
        };
        action.putValue(Action.ACCELERATOR_KEY, keyStroke);
        action.putValue(Action.ACCELERATOR_KEY, keyStrokePad);


        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, jButton.getName());
        jButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokePad, jButton.getName());
        jButton.getActionMap().put(jButton.getName(), action);

    }

    private void setButtons(String enabled) {
        for (JButton jButton : jButtons) {
            jButton.setEnabled(enabled.contains(jButton.getName()));

        }
    }

    public void executeUp() {

        if (calculationsCursor < calculations.getCalculationList().size() && calculationsCursor > 0) {
            calculationsCursor--;
            Calculation calculation = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation.getExpressionString());
            setCalculations(calculation);
            setRadioButton(calculation.getNumeralSystem());
        }
        else if (calculationsCursor == 0 && calculations.getCalculationList().size() > 0) {
            Calculation calculation1 = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation1.getExpressionString());
            setCalculations(calculation1);
            setRadioButton(calculation1.getNumeralSystem());
        }
    }

    public void executeDown() {

        if (calculationsCursor < calculations.getCalculationList().size() - 1 && calculationsCursor >= 0) {
            calculationsCursor++;
            Calculation calculation = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation.getExpressionString());
            setCalculations(calculation);
            setRadioButton(calculation.getNumeralSystem());
        }
    }

    private void setRadioButton(String numeralSystem) {
        switch (numeralSystem) {
            case "binary" -> binarioRadioButton.setSelected(true);
            case "octal" -> octalRadioButton.setSelected(true);
            case "decimal" -> decimalRadioButton.setSelected(true);
            case "hexadecimal" -> hexadecimalRadioButton.setSelected(true);
        }
    }

    public void executeEquals() {

        equalPressed = true;
        String arithmeticExpression = textField1.getText();
        Calculation calculation = new Calculation(arithmeticExpression, numeralSystem);

        if (calculation.isValid()) {

            setCalculations(calculation);
            calculations.getCalculationList().add(calculation);
            calculationsCursor = calculations.getCalculationList().size() - 1;

        } else {
            textFieldBinary.setText(calculation.getErrors());
        }
    }

    private void setCalculations(Calculation calculation) {
        textFieldBinary.setText(calculation.getResult()[0]);
        textFieldOctal.setText(calculation.getResult()[1]);
        textFieldDecimal.setText(calculation.getResult()[2]);
        textFieldHexadecimal.setText(calculation.getResult()[3]);
    }

    private void addElement(String element) {
        if (equalPressed) clearTextFields();
        textField1.setCaretPosition(textField1.getDocument().getLength());
        textField1.replaceSelection(element);
        equalPressed = false;
    }

    private void clearTextFields() {
        textField1.setText(null);
        textFieldBinary.setText(null);
        textFieldOctal.setText(null);
        textFieldDecimal.setText(null);
        textFieldHexadecimal.setText(null);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new Calculator2();

    }


}
