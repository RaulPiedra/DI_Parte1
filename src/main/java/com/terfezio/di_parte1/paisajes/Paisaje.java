package com.terfezio.di_parte1;

import javax.swing.*;
import java.net.URL;
public class Paisaje {
    /**
     * El array que contendrá las imágenes presentes en el directorio indicado
     * en el constructor.
     */
    private final ImageIcon[] fotosPaisajes;
    /**
     * La posición en el array ImageIcon[] fotosPaisajes que se va a devolver.
     */
    private int cursor;
    /**
     * El nombre del directorio en el que se encuentran las imágenes.
     */
    private final String nombre;

    /**
     * Este constructor toma un String nombre para leer el directorio
     * en el que tenemos las fotos.
     * @author Raul Piedra Ramon
     *
     */
    public Paisaje(String nombre) {
        this.nombre = nombre;
        fotosPaisajes = new ImageIcon[4];
        rellenaFotos();
        cursor = 0;
    }

    /**
     * Este método rellena el array fotosPaisajes con las fotos encontradas
     * en el directorio indicado.
     */
    private void rellenaFotos() {

        for (int i = 0; i < 4; i++) {
            String imgLocation = "/imagenes/" + nombre + "/" + i + ".jpg";
            URL imageURL = getClass().getResource(imgLocation);
            assert imageURL != null;
            fotosPaisajes[i] = new ImageIcon(imageURL);
        }
    }

    /**
     * Retorna un objeto ImageIcon e incrementa el valor de int cursor o lo vuelve a cero
     * si está en la última posición.
     * @return El objeto ImageIcon contenido en el array fotosPaisajes que esté en la
     * posición marcada por el cursor.
     */
    public ImageIcon devuelveImagen() {
        if (cursor == fotosPaisajes.length - 1) {
            cursor = 0;
        } else {
            cursor++;
        }
        return fotosPaisajes[cursor];
    }
}
