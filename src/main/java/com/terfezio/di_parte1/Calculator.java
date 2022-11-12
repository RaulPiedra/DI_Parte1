package com.terfezio.di_parte1.Practica6;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Calculator extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    public JPanel jPanel;
    private JPanel jPanelButtons;
    private JButton jButtonEq;
    private JButton jButtonClear;
    private final Calculations calculations;
    private int calculationsCursor;
    private boolean equalPressed;

    public Calculator() {
        calculations = new Calculations();
        calculationsCursor = 0;
        equalPressed = false;

        setContentPane(this.jPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Calculadora");
        String imgLocation = "/calculator.png";
        URL imageURL = Utilidades.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);
        setIconImage(icon.getImage());

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Calculadora");
        jMenu.setMnemonic(KeyEvent.VK_C);

        JMenu jMenuHelp = new JMenu("Ayuda");
        jMenuHelp.setMnemonic(KeyEvent.VK_Y);

        JMenuItem jMenuItemHelp = new JMenuItem("Ayuda");
        KeyStroke keyStrokeHelp = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        jMenuItemHelp.setMnemonic(KeyEvent.VK_Y);
        jMenuItemHelp.setAccelerator(keyStrokeHelp);

        JMenuItem jMenuItem = new JMenuItem("Calculadora normal");
        KeyStroke keyStrokeCalcNormal = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_DOWN_MASK);
        jMenuItem.setMnemonic(KeyEvent.VK_N);
        jMenuItem.setAccelerator(keyStrokeCalcNormal);

        JMenuItem jMenuItem1 = new JMenuItem("Calculadora de programador");
        KeyStroke keyStrokeCalcProg = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK);
        jMenuItem1.setMnemonic(KeyEvent.VK_P);
        jMenuItem1.setAccelerator(keyStrokeCalcProg);

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

        jMenuItem.setEnabled(false);

        jMenuItemExit.addActionListener(e -> {
            dispose();
        });

        jMenuItem1.addActionListener(e -> {

            new Calculator2();
            dispose();
        });
        jMenuItemHelp.addActionListener(e -> {
            new CalculatorHelp();
        });


        Component[] components = jPanelButtons.getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JButton.class) && component.isEnabled()) {
                JButton jButton = (JButton) component;
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
                    case "." -> {
                        keyStroke = KeyStroke.getKeyStroke(jButton.getName().charAt(0), 0);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);

                    }
                    case "/" -> {
                        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.SHIFT_DOWN_MASK);
                        keyStrokePad = KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0);
                        setShortCutsWithPad(jButton, keyStroke, keyStrokePad);
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
                    case "clear" -> {
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
                    case "allClear" -> {
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


                        jButtonEq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, KeyEvent.VK_ENTER);
                        jButtonEq.getActionMap().put(KeyEvent.VK_ENTER, actionEquals);
                    }
                    /*default -> {
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
                    }*/
                }

                jButton.addMouseListener(new MouseAdapter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String nameOfButton = jButton.getName();
                        switch (nameOfButton) {
                            case "equals" -> {
                                executeEquals();
                                equalPressed = true;
                            }
                            case "clear" -> {
                                if (textField1.getText().length() >= 1) {
                                    textField1.setText("" + textField1.getText().substring(0, textField1.getText().length() - 1));
                                }
                            }
                            case "allClear" -> {
                                clearTextFields();
                            }
                            case "up" -> {
                                executeUp();
                            }
                            case "down" -> {
                                executeDown();
                            }
                            default -> {
                                if (equalPressed) clearTextFields();
                                equalPressed = false;
                                addElement(jButton.getName());
                            }

                        }
                    }
                });
            }
        }
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

    public void executeUp() {

        if (calculationsCursor < calculations.getCalculationList().size() && calculationsCursor > 0) {
            calculationsCursor--;
            Calculation calculation1 = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation1.getExpressionString());
            textField2.setText(calculation1.getResult()[2]);
        }
        else if (calculationsCursor == 0 && calculations.getCalculationList().size() > 0) {
            Calculation calculation1 = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation1.getExpressionString());
            textField2.setText(calculation1.getResult()[2]);
        }
    }

    public void executeDown() {

        if (calculationsCursor < calculations.getCalculationList().size() - 1 && calculationsCursor >= 0) {
            calculationsCursor++;
            Calculation calculation1 = calculations.getCalculationList().get(calculationsCursor);
            textField1.setText(calculation1.getExpressionString());
            textField2.setText(calculation1.getResult()[2]);
        }
    }

    public void executeEquals() {

        equalPressed = true;
        String arithmeticExpression = textField1.getText();
        Calculation calculation = new Calculation(arithmeticExpression);

            if (calculation.isValid()) {
                textField2.setText(calculation.getResult()[2]);
                calculations.getCalculationList().add(calculation);
                calculationsCursor = calculations.getCalculationList().size() - 1;
                calculation = null;
            } else {
                textField2.setText(calculation.getErrors());
            }


    }

    private void addElement(String element) {
        if (equalPressed) clearTextFields();
        textField1.setCaretPosition(textField1.getDocument().getLength());
        textField1.replaceSelection(element);
        equalPressed = false;
    }

    private void clearTextFields() {
        textField1.setText(null);
        textField2.setText(null);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        /*JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/
        new Calculator();
    }
}
