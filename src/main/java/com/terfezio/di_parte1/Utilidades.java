package com.terfezio.di_parte1.Practica6;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utilidades extends JFrame {
    private JPanel panel1;
    private Configuration configuration;
    private final JMenuItem jMenuItemFotos;
    private File[] files;
    private Calculator calculator;

    public Utilidades() throws IOException {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        String imgLocation = "/tools-ico.png";
        URL imageURL = Utilidades.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);
        setIconImage(icon.getImage());
        setContentPane(this.panel1);
        setTitle("Utilidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuArchivo = new JMenu("Archivo");
        jMenuArchivo.setMnemonic(KeyEvent.VK_A);

        JMenu jMenuConf = new JMenu("Configuración");
        jMenuConf.setMnemonic(KeyEvent.VK_C);

        JMenuItem jMenuItemConf = new JMenuItem("Configuración");
        KeyStroke keyStrokeConf = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.ALT_DOWN_MASK);
        jMenuItemConf.setMnemonic(KeyEvent.VK_G);
        jMenuItemConf.setAccelerator(keyStrokeConf);

        JMenuItem jMenuItemCalc = new JMenuItem("Calculadora");
        KeyStroke keyStrokeCalc = KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.ALT_DOWN_MASK);
        jMenuItemCalc.setMnemonic(KeyEvent.VK_U);
        jMenuItemCalc.setAccelerator(keyStrokeCalc);

        jMenuItemFotos = new JMenuItem("Fotos");
        KeyStroke keyStrokeFotos= KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.ALT_DOWN_MASK);
        jMenuItemFotos.setMnemonic(KeyEvent.VK_F);
        jMenuItemFotos.setAccelerator(keyStrokeFotos);

        JMenuItem jMenuItemExit = new JMenuItem("Salir");
        KeyStroke keyStrokeExit = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK);
        jMenuItemExit.setMnemonic(KeyEvent.VK_S);
        jMenuItemExit.setAccelerator(keyStrokeExit);

        jMenuArchivo.add(jMenuItemCalc);
        jMenuArchivo.add(jMenuItemFotos);
        jMenuArchivo.add(jMenuItemExit);

        jMenuConf.add(jMenuItemConf);
        jMenuBar.add(jMenuArchivo);

        jMenuBar.add(jMenuConf);
        setJMenuBar(jMenuBar);



        jMenuItemExit.addActionListener(e -> {
            System.exit(0);

        });

        jMenuItemCalc.addActionListener(e -> {
            if (calculator == null) {

                calculator = new Calculator();
            }

        });

        loadConfiguration();
        jMenuItemConf.addActionListener(e -> {
            Config dialog = new Config(configuration);
            dialog.setLocationRelativeTo(null);
            dialog.pack();
            dialog.setVisible(true);

            try {
                loadConfiguration();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        jMenuItemFotos.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread fotos = new Thread(new Fotos(files, configuration.getSeconds()));
                    fotos.start();

                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });

        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }
    private void loadConfiguration() throws IOException {

        if (new File(System.getProperty("user.home") + "/config.json").exists()) {
            String json = Files.readString(Path.of(System.getProperty("user.home")+ "/config.json"));
            Gson gson = new Gson();
            configuration = gson.fromJson(json, Configuration.class);
            FileFilter fileFilter = getFileFilter(configuration.getPng(), configuration.getJpg(), configuration.getGif());
            files = new File(configuration.getPath()).listFiles(fileFilter);
            assert files != null;
            jMenuItemFotos.setEnabled(files.length != 0);

        }


    }
    private FileFilter gettFileFilter(boolean png, boolean jpg, boolean gif) {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".jpg")) {
                    return jpg;
                }
                else if (pathname.getName().endsWith(".png")) {
                    return png;
                }
                else if (pathname.getName().endsWith(".gif")) {
                    return gif;
                }

                return false;
            }
        };
    }
    private FileFilter getFileFilter(boolean png, boolean jpg, boolean gif) {
        return pathname -> {
            if (pathname.getName().endsWith(".png")) {
                return png;
            }
            else if (pathname.getName().endsWith(".jpg")) {
                return  jpg;
            }
            else if (pathname.getName().endsWith(".gif")) {
                return gif;
            }
            return false;
        };
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        new Utilidades();
    }
}
