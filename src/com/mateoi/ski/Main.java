package com.mateoi.ski;

public class Main {

    public static void main(String[] args) {
        SkiGame game = new SkiGame(10, 2.0, 0.03, 1, 0.05, 275, 420);
        Player player = new HumanPlayer();
        SkiFXApp.setGame(game);
        SkiFXApp.setPlayer(player);
        SkiFXApp.launch(SkiFXApp.class);
    }
}
