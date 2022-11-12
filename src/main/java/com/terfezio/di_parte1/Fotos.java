package com.terfezio.di_parte1.Practica6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Fotos extends JFrame implements Runnable {
    private JPanel panel1;
    private JLabel jLabel;
    private final File[] files;
    double seconds;

    public Fotos(File[] files, double seconds) throws InterruptedException {
        this.files = files;
        this.seconds = seconds;
        setContentPane(this.panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String imgLocation = "/photo.png";
        URL imageURL = Utilidades.class.getResource(imgLocation);
        assert imageURL != null;
        ImageIcon icon = new ImageIcon(imageURL);
        setIconImage(icon.getImage());
        setTitle("Fotos");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuArchivo = new JMenu("Archivo");
        jMenuArchivo.setMnemonic(KeyEvent.VK_A);



        JMenuItem jMenuItemExit= new JMenuItem("Salir");
        KeyStroke keyStrokeExit = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_DOWN_MASK);
        jMenuItemExit.setMnemonic(KeyEvent.VK_L);
        jMenuItemExit.setAccelerator(keyStrokeExit);

        jMenuArchivo.add(jMenuItemExit);

        jMenuBar.add(jMenuArchivo);


        setJMenuBar(jMenuBar);


        jMenuItemExit.addActionListener(e -> dispose());



        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void showPictures() throws InterruptedException {
        for (File file: files) {
            try {
                Image image = ImageIO.read(file);
                image =  image.getScaledInstance(640, 480, 0);
                ImageIcon imageIcon = new ImageIcon(image);
                jLabel.setIcon(imageIcon);
            } catch (IOException ex) {
                // handle exception...
            }
            Thread.sleep((long) (seconds * 1000));

        }
    }

    public static void main(String[] args) throws InterruptedException {
        File file = new File("data/");
        File[] files = file.listFiles();
        new Fotos(files, 2);
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            showPictures();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
