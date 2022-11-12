package com.terfezio.di_parte1;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JButton elegirRutaButton;
    private JCheckBox jpegCheckBox;
    private JCheckBox pngCheckBox;
    private JCheckBox gifCheckBox;
    private JSpinner spinner1;

    public Config(Configuration configuration) {
        setContentPane(contentPane);
        setModal(true);
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(0.5, 0.5, 20.0, 0.5);
        spinner1.setModel(spinnerNumberModel);
        getRootPane().setDefaultButton(buttonOK);
        if (configuration != null) {
            textField1.setText(configuration.getPath());
            pngCheckBox.setSelected(configuration.getPng());
            jpegCheckBox.setSelected(configuration.getJpg());
            gifCheckBox.setSelected(configuration.getGif());
            spinner1.setValue(configuration.getSeconds());
        }


        buttonOK.addActionListener(e -> {
            try {
                onOK();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

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
        elegirRutaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = jFileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    textField1.setText(selectedFile.getAbsolutePath());
                }
            }
        });

    }

    private void onOK() throws IOException {
        String path = textField1.getText();
        boolean png = pngCheckBox.isSelected();
        boolean jpg = jpegCheckBox.isSelected();
        boolean gif = gifCheckBox.isSelected();
        double seconds = (double) spinner1.getValue();

        Configuration configuration = new Configuration(path);
        configuration.setGif(gif);
        configuration.setJpg(jpg);
        configuration.setPng(png);
        configuration.setSeconds(seconds);
        Gson gson = new Gson();
        String json = gson.toJson(configuration);
        //Files.writeString(Paths.get("data/config.json"), json, StandardCharsets.UTF_8);
        Files.writeString(Paths.get(System.getProperty("user.home")+"/config.json"), json, StandardCharsets.UTF_8);
        dispose();

    }

    private void onCancel() {

        dispose();
    }

    public static void main(String[] args) {
        Configuration conf = new Configuration("/");
        Config dialog = new Config(conf);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
