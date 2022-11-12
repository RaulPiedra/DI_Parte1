package com.terfezio.di_parte1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cube {
    private final String[][][] sides;


    public Cube(boolean randomColorOnSides) {
        sides = new String[3][3][3];
        if (randomColorOnSides) {
            this.setSidesRandom();
        }
        else {
            this.setSides();
        }

    }

    private void setSides() {
        sides[1][1][0] = "green";
        sides[0][1][1] = "blue";
        sides[2][1][1] = "orange";
        sides[1][1][2] = "white";
        sides[1][0][1] = "red";
        sides[1][2][1] = "yellow";

    }
    private void setSidesRandom() {
        String[] colors = {"green", "blue", "orange", "white", "red", "yellow"};
        List<String> list = Arrays.asList(colors);
        Collections.shuffle(list);
        list.toArray(colors);
        sides[1][1][0] = colors[0]; // cara de atrás
        sides[0][1][1] = colors[1]; // cara izquierda
        sides[2][1][1] = colors[2]; // cara derecha
        sides[1][1][2] = colors[3]; // cara de adelante
        sides[1][0][1] = colors[4]; // cara de arriba
        sides[1][2][1] = colors[5]; // cara de abajo

    }


    public void rotateRight() {
        /*String[] aux = {sides[0][1][1], sides[1][0][1], sides[2][1][1], sides[1][2][1]};

        sides[0][1][1] = aux[3];
        sides[1][0][1] = aux[0];
        sides[2][1][1] = aux[1];
        sides[1][2][1] = aux[2];*/

        String aux2 = sides[0][1][1];
        sides[0][1][1] = sides[1][2][1];
        sides[1][2][1] = sides[2][1][1];
        sides[2][1][1] = sides[1][0][1];
        sides[1][0][1] = aux2;

    }

    public void rotateLeft() {
        String[] aux = {sides[0][1][1], sides[1][0][1], sides[2][1][1], sides[1][2][1]};

        sides[0][1][1] = aux[1];
        sides[1][0][1] = aux[2];
        sides[2][1][1] = aux[3];
        sides[1][2][1] = aux[0];
    }

    public void rotateUp() {
        String[] aux = {sides[1][1][0], sides[1][0][1], sides[1][1][2], sides[1][2][1]};

        sides[1][1][0] = aux[3];
        sides[1][0][1] = aux[0];
        sides[1][1][2] = aux[1];
        sides[1][2][1] = aux[2];
    }

    public void rotateDown() {
        String[] aux = {sides[1][1][0], sides[1][0][1], sides[1][1][2], sides[1][2][1]};

        sides[1][1][0] = aux[1];
        sides[1][0][1] = aux[2];
        sides[1][1][2] = aux[3];
        sides[1][2][1] = aux[0];
    }

    public String returnTopColor() {
        return sides[1][0][1];
    }


}
