package com.mateoi.ski;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class HumanPlayer implements Player {
    private ArrayList<KeyCode> input = new ArrayList<>();

    public HumanPlayer(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (!input.contains(e.getCode())) {
                input.add(e.getCode());
            }
        });
        scene.setOnKeyReleased(e -> input.remove(e.getCode()));
    }

    @Override
    public int move(Game state) {
        int move = 0;
        if (input.contains(KeyCode.RIGHT)) {
            move++;
        }
        if (input.contains(KeyCode.LEFT)) {
            move--;
        }
        return move;
    }

}
