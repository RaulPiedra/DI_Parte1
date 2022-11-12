package com.terfezio.cuborubik;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class RubikController {

    private Cube cube;
    @FXML private GridPane gridPane;
    @FXML private Button buttonRight;
    private ObservableList<?> allButtonList;
    private ArrayList<Button> cubeButtonList;


    @FXML private void initialize() {

        allButtonList = gridPane.getChildren();
        cubeButtonList = clearAllButtonList();

        cube = new Cube(false);
        paintButtons(cube.returnTopColor());
    }
    private void paintButtons(String color) {
        for (Button button: cubeButtonList) {
            button.setStyle("-fx-background-color: " + color);
        }
    }

    private ArrayList<Button> clearAllButtonList() {
        ArrayList<Button> buttonArray = new ArrayList<>();
        for (Object o : allButtonList) {
            Button button = (Button) o;
            if (button.getAccessibleText().equals("cube")) {
                buttonArray.add(button);
            }

        }
        return buttonArray;
    }

    public void rotateDown() {
        cube.rotateDown();
        paintButtons(cube.returnTopColor());

    }

    public void rotateLeft() {
        cube.rotateLeft();
        paintButtons(cube.returnTopColor());
    }

    public void rotateUp() {
        cube.rotateUp();
        paintButtons(cube.returnTopColor());
    }

    public void rotateRight() {
        cube.rotateRight();
        paintButtons(cube.returnTopColor());
    }
}
